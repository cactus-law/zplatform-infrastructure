/* 
 * SynchPerson.java  
 * 
 * version v1.0
 *
 * 2015年9月15日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.utils.Md5;
import com.zlebank.zplatform.commons.utils.RegExpValidatorUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.MerchMK;
import com.zlebank.zplatform.member.bean.Person;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.MerchMKService;
import com.zlebank.zplatform.member.service.PersonService;
import com.zlebank.zplatform.specification.bean.enums.EntranceType;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;
import com.zlebank.zplatform.specification.utils.Base64Utils;
import com.zlebank.zplatform.specification.utils.RSAUtils;

/**
 * 个人会员开户
 *
 * @author yangpeng
 * @version
 * @date 2015年9月15日 下午8:20:14
 * @since 
 */
@Service
public class OpenPerson   extends RawAbstractProcessor{
    private static final String SUCCESS_CODE="00";
    private static final String SUCCESS_DES="个人会员开户成功"; 
    private static final String FAIL_INSIDE_CODE="40";
    private static final String FAIL_FIELD_CODE="30";
    private static final String FAIL_DES="个人会员开户失败：";
    @Autowired
    private MemberEntranceLinkDAO memberELD;
    @Autowired
    private PersonService person;
    @Autowired
    private MerchMKService mkService;
    @Autowired
    private MemberDAO memberDAOImpl;
    /**
     *
     * @param request
     * @return
     * @throws MemberBussinessException
     * @throws AbstractBusiAcctException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Response realProcess(Message request) {
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId"));
        String rtnMemberId="";
        String errorMsg="";
        
        Person p = new Person();
        String merchNo = request.getString("merchNo"); 
        String eMemId = request.getString("entranceMemId");
        String loginName = request.getString("loginName");
        String gendel = request.getString("gender").toUpperCase();
        String email = request.getString("email");
        String phone = request.getLong("phone") + "";
        String name = request.getString("name");
        String loginPwd = request.getString("loginPwd");
        
        if (!"M".equals(gendel.toUpperCase())
                && !"F".equals(gendel.toUpperCase())) {
            errorMsg+="性别不合法|";
        }
        
        // 验证邮箱格式
        if (StringUtil.isNotEmpty(email)&&!RegExpValidatorUtil.isEmail(email)) {
            errorMsg+="邮箱"+email+"格式不合法|";
        }
        // 验证手机格式contactsTelNo
        if (!RegExpValidatorUtil.IsHandset(phone)
                && !RegExpValidatorUtil.IsTelephone(phone)) {
            errorMsg+="手机"+phone+"格式不合法|";
        }
        // 验证手机号是否重复
        if (memberDAOImpl.getMemberByphone(phone) != null ) {
            errorMsg += "手机号"+phone+"重复|";
        }
        // 验证登陆名是否重复
        if (memberDAOImpl.getMemberByLoginName(loginName) != null ) {
            errorMsg += "登陆名"+loginName+"重复|";
        }
        // 验证电子邮件是否重复
        if (memberDAOImpl.getMemberByEmail(email) != null ) {
            errorMsg += "电子邮件"+email+"重复|";
        }
        // 业务检查异常
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            res.setResCode(FAIL_FIELD_CODE);
            // 返回报文【应答码描述】
            res.setResDes(errorMsg);
            return res;
        }

        p.setEmail(email);
        p.setLoginame(loginName);
        p.setPhone(phone);
        p.setMembername(name);
        // 得到密钥信息
        MerchMK mk = mkService.get(merchNo);
        try {
            // 平台私钥解密
            String merchPwd = new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(loginPwd), mk.getLocalPriKey()));
            // MD5保存
            p.setLoginPwd(Md5.getInstance().md5s(merchPwd));
        } catch (Exception e1) {
            e1.printStackTrace();
            // 返回报文【应答码】
            res.setResCode(FAIL_FIELD_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES + "登陆密码商户解密/本地加密出现错误");
            return res;
        }
        if ("M".equals(gendel))
            p.setSex("1");
        if ("F".equals(gendel))
            p.setSex("0");
        try {
            // 开通会员
            rtnMemberId = person.save(p, 0L);
            // 将会员信息存到表里面
            PojoMemberEntranceLink pmel = new PojoMemberEntranceLink();
            pmel.setMemberId(rtnMemberId);
            pmel.setBelongMemberId(merchNo);
            pmel.setEntranceType(EntranceType.API);
            pmel.setEntranceMemberId(eMemId);
            memberELD.saveA(pmel);
        } catch (MemberBussinessException e) {
            e.printStackTrace();
            errorMsg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
            errorMsg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            res.setResCode(FAIL_INSIDE_CODE);
            errorMsg = e.getMessage();
        }
        // 业务处理异常
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            if (StringUtil.isEmpty(res.getResCode()))
                res.setResCode(FAIL_FIELD_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES + errorMsg);
            return res;
        }
        // 返回报文【应答码】
        res.setResCode(SUCCESS_CODE);
        // 返回报文【应答码描述】
        res.setResDes(SUCCESS_DES);
        // 返回报文【二级商户号】
        res.setObject("memberId", rtnMemberId);
        return res;
    }
}
