/* 
 * RawAbstractProcessor.java  
 * 
 * version 1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.member.bean.MerchMK;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.member.service.MerchMKService;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.SpecificationProcessor;
import com.zlebank.zplatform.specification.bean.enums.EntranceStatus;
import com.zlebank.zplatform.specification.dao.EntranceAPILogDAO;
import com.zlebank.zplatform.specification.dao.MemberEntranceInfoDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Request;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.parser.SpecificationParser;
import com.zlebank.zplatform.specification.pojo.PojoEntranceAPILog;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceInfo;
import com.zlebank.zplatform.specification.utils.RSAUtils;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 上午9:11:59
 * @since
 */
@Service
public abstract class RawAbstractProcessor implements SpecificationProcessor {

    private final static Log log = LogFactory
            .getLog(RawAbstractProcessor.class);

    protected final static String FIELD_MERCH_NO = "merchNo";
    protected final static String FIELD_SESSIONID = "sessionId";
    protected final static String RESPONSE_SUCCESS_CODE = "00";
    protected final static String RESPONSE_MERCH_NULL = "31";
    protected final static String RESPONSE_SESSION_NOT_UNIQUE = "32";
    protected final static String RESPONSE_MAC_FAIL = "33";
    protected final static String RESPONSE_UNSIGED = "34";

    @Autowired
    protected MerchService merchServiceImpl;

    @Autowired
    private EntranceAPILogDAO entranceAPILogDAOImpl;

    @Autowired
    protected MerchMKService merchMKServiceImpl;
    @Autowired
    protected MemberEntranceInfoDAO memberEntranceInfoDAOImpl;

    private final static int MAX_INFO_PARA_NUMBER = 5;

    @Autowired
    private SpecificationParser specificationParser;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Response process(Message request) {
        if (!Request.class.isAssignableFrom(request.getClass())) {
            throw new RuntimeException("Must be a Request type");
        }
        Request _request = (Request) request;
        Response response = basicProcess(_request);

        if (response != null
                && !response.getResCode().equals(RESPONSE_SUCCESS_CODE)) {
            return response;
        }

        // 生成mac
        response = realProcess(_request);
        response.setObject("mac", "");
        JSONObject resJson = specificationParser.parse(response);

        String mac = generted(response.getString(FIELD_MERCH_NO),
                resJson.toString());
        response.setObject("mac", mac);
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Response basicProcess(Request request) {
        boolean isValidate = true;
        String rescode = RESPONSE_SUCCESS_CODE;
        Object params[] = new Object[MAX_INFO_PARA_NUMBER];
        // validate merchant query by merchNo is exist
        String memberId = request.getString(FIELD_MERCH_NO);
        if (!validateMermberId(memberId)) {
            rescode = RESPONSE_MERCH_NULL;
            params[0] = memberId;
            isValidate = false;
        }

        // validate if the sessionId is unique
        String sessionId = request.getString(FIELD_SESSIONID);

        if (isValidate && !validateSessionId(memberId, sessionId)) {
            rescode = RESPONSE_SESSION_NOT_UNIQUE;
            params[0] = memberId;
            params[1] = sessionId;
            isValidate = false;
        }

        // validate mac
        JSONObject requestJson = request.getOrginJson();
        String mac = requestJson.getJSONObject("content").getString("mac");
        requestJson.getJSONObject("content").put("mac", "");
        String originStrWithoutMac = requestJson.toString();

        if (isValidate && !validateMac(memberId, mac, originStrWithoutMac)) {
            rescode = RESPONSE_MAC_FAIL;
            params[1] = sessionId;
            isValidate = false;
        }

        // validate merchant has signed before
        if (isValidate && request.getRequestType() != RequestType.SIGN) {
            if (!hasSigned(memberId)) {
                rescode = RESPONSE_UNSIGED;
                isValidate = false;
            }
        }
        Response response = new Response();
        response.setResCode(rescode);
        response.setResDes(params);
        return response;
    }

    private boolean validateMermberId(String memberId) {
        PojoMerchDeta merchant = merchServiceImpl.getMerchBymemberId(memberId);
        if (merchant == null) {
            return false;
        }
        return true;
    }

    private boolean validateSessionId(String memberId, String sessionId) {
        PojoEntranceAPILog apiLog = entranceAPILogDAOImpl
                .getByMemberIdSessionId(memberId, sessionId);
        if (apiLog != null) {
            return false;
        }
        return true;
    }

    private boolean validateMac(String memberId, String mac, String originSrc) {
        MerchMK merchMK = merchMKServiceImpl.get(memberId);
        if (merchMK == null) {
            log.info("校验mac错误,商户秘钥对不存在");
            // TODO
            return false;
        }

        if (log.isDebugEnabled()) {
            log.info("mac origin str=" + originSrc + ", validate mac=" + mac);
        }
        boolean ismac = false;

        try {
            ismac = RSAUtils.verify(originSrc.getBytes(), merchMK
                    .getMemberPubKey().trim(), mac);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (!ismac) {
            return false;
        }
        return true;
    }

    private boolean hasSigned(String memberId) {
        PojoMemberEntranceInfo MemberEntranceInfo = memberEntranceInfoDAOImpl
                .getByMemberId(memberId);
        if (MemberEntranceInfo == null
                || MemberEntranceInfo.getEntranceStatus().equals(
                        EntranceStatus.SIGNED)) {
            return false;
        }
        return true;
    }

    private String generted(String memberId, String orignSrc) {
        MerchMK merchMK = merchMKServiceImpl.get(memberId);
        if (log.isDebugEnabled()) {
            log.info("generted orignsrc = " + orignSrc);
        }
        try {
            return RSAUtils.sign(orignSrc.getBytes(), merchMK.getLocalPriKey());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 
     * @param request
     * @return
     */
    protected abstract Response realProcess(Message request);

}
