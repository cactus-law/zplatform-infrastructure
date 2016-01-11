/* 
 * SelectMerchSynch.java  
 * 
 * version TODO
 *
 * 2015年9月15日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * 子商户开通结果查询
 *
 * @author yangpeng
 * @version
 * @date 2015年9月15日 下午8:17:37
 * @since
 */
@Service
public class QueryMerchant extends RawAbstractProcessor {

    private static final String SUCCESS_CODE = "00";
    private static final String SUCCESS_DES = "二级商户查询成功";
    private static final String FAIL_CODE = "30";
    //private static final String FAIL_DES = "查询失败：";
    @Autowired
    private MemberService memberService;
    @Autowired
    private MerchService merchService;
    @Autowired
    private MemberEntranceLinkDAO memberLink;

    /**
     *
     * @param request
     * @return
     */
    @Override
    public Response realProcess(Message request) {
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId"));
        String rtnMemberId = "";
        String errorMsg = "";

        // 查询用商户会员号
        String minorMerchNo = "";
        // 从 会员所属关联表 查出 相应的记录
        List<PojoMemberEntranceLink> pojoMemberEntranceLinkList = memberLink.getByEntranceMemberId(request.getString("entranceMemId"));
        if (pojoMemberEntranceLinkList == null ||  pojoMemberEntranceLinkList.size() != 1) {
            // 返回报文【应答码】
               res.setResCode(FAIL_CODE);
               // 返回报文【应答码描述】
               String msg=null;
               if (pojoMemberEntranceLinkList == null) {
                   msg="根据对方的内部会员号没有查到关联记录";
               } else {
                   msg = "根据对方的内部会员号查到多条记录，请联系管理员。";
               }
               res.setResDes("商户会员查询失败：" + msg);
               return res;
           }
        PojoMemberEntranceLink pojoMemberEntranceLink = 
                pojoMemberEntranceLinkList != null  && pojoMemberEntranceLinkList.size() > 0 ?  pojoMemberEntranceLinkList.get(0) : new  PojoMemberEntranceLink();

        // 如果不存在或者与报文里的会员号不一致，则定义为会员不存在。
        if (StringUtil.isNotEmpty(pojoMemberEntranceLink.getMemberId())) {
            if (StringUtil.isEmpty(request.getString("minorMerchNo")) || pojoMemberEntranceLink.getMemberId().equals(request.getString("minorMerchNo"))) {
                minorMerchNo = pojoMemberEntranceLink.getMemberId();
            }
        }

        // 确定该商户的上级商户号是否是merchno
        PojoMerchDeta merch = merchService.getMerchBymemberId(minorMerchNo);

        if (merch == null
                || !request.getString("merchNo").equals(merch.getParent())) {
            res.setResCode(FAIL_CODE);
            errorMsg += "子商户不存在或二级商户与一级商户不匹配。";

        }

        // 业务检查异常
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes(errorMsg);
            return res;
        } else {

            rtnMemberId = merch.getMemberid();

            // 返回报文【应答码】
            res.setResCode(SUCCESS_CODE);
            // 返回报文【应答码描述】
            res.setResDes(SUCCESS_DES);
            // 返回报文【二级商户号】
            res.setObject("minorMerchNo", rtnMemberId);
            res.setObject("status", merch.getMemberstat().getCode());
        }
        return res;
    }

}
