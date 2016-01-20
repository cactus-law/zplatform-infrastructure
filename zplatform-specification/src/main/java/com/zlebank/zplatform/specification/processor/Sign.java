/* 
 * Sign.java  
 * 
 * version v1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.utils.RegExpValidatorUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.specification.bean.enums.EntranceStatus;
import com.zlebank.zplatform.specification.dao.EntranceAPILogDAO;
import com.zlebank.zplatform.specification.dao.MemberEntranceInfoDAO;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceInfo;

/**
 * 商户签到
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:59:40
 * @since 
 */
@Service
public class Sign extends RawAbstractProcessor {
    private final static Log log = LogFactory.getLog(Sign.class);
    private static final String SUCCESS_CODE="00";
    private static final String SUCCESS_DES="签到成功";
    private static final String FAIL_CODE="30";
    private static final String FAIL_MEMBER_NOTFOUND_DES="会员不存在";
    private static final String FAIL_UNKNOWN_DES="未知异常：";

    @Autowired
    EntranceAPILogDAO entranceAPILogDAO;
    @Autowired
    MemberEntranceInfoDAO memberEntranceInfoDAO;
    @Autowired
    MemberEntranceLinkDAO memberEntranceLinkDAO;
    @Autowired
    MemberDAO memberDAO;
    
    /**
     * 商户签到
     * @param request
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Response realProcess(Message request) {
        
        
        String merchNo = request.getString("merchNo");
        String sessionId = request.getString("sessionId");
        String notifyUrl = request.getString("notifyUrl"); 
        String errorMsg = "";
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", merchNo);
        res.setObject("sessionId", sessionId);
        if(!RegExpValidatorUtil.IsUrl(notifyUrl)){
            errorMsg+="url格式不正确";
            
        }
        // 业务检查异常
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes("业务检查异常：" + errorMsg);
            return res;
        }

        
        try {
            // 会员接入信息查询
            if (StringUtil.isEmpty(merchNo)) {
                // 如果会员不存在
                res.setResCode(FAIL_CODE);
                res.setResDes(FAIL_MEMBER_NOTFOUND_DES);
                return res;
            }
            PojoMember member = memberDAO.getMemberByMemberId(merchNo, null);
            // 如果会员不存在
            if (member == null) {
                res.setResCode(FAIL_CODE);
                res.setResDes(FAIL_MEMBER_NOTFOUND_DES);
                return res;
            }
    
            PojoMemberEntranceInfo entInfo = memberEntranceInfoDAO.getByMemberId(member.getMemberId());
            // 如果已经签到则更新信息，如果没有签到则插入一条记录。
            if (entInfo == null) {
              PojoMemberEntranceInfo memberEntInfo = new PojoMemberEntranceInfo();
              memberEntInfo.setMemberId(member.getMemberId());// 会员号
              memberEntInfo.setEntranceStatus(EntranceStatus.SIGNED);// 接入状态
              memberEntInfo.setLastSignTime(new Date());// 最后签到时间
              memberEntInfo.setSignCount(1L);// 签到次数
              memberEntInfo.setNotifyUrl(notifyUrl);// 回调地址
              memberEntranceInfoDAO.merge(memberEntInfo);
            } else {
                entInfo.setSignCount(entInfo.getSignCount()+1);
                entInfo.setNotifyUrl(notifyUrl);
                entInfo.setLastSignTime(new Date());
                entInfo.setEntranceStatus(EntranceStatus.SIGNED);
                memberEntranceInfoDAO.merge(entInfo);
            }
            res.setResCode(SUCCESS_CODE);
            res.setResDes(SUCCESS_DES);
        } catch (Exception e) {
            log.error(e.getMessage(),e); 
            // 未知异常
            res.setResCode(FAIL_CODE);
            res.setResDes(FAIL_UNKNOWN_DES+e.getMessage());
        }
        return res;
    }
    
}
