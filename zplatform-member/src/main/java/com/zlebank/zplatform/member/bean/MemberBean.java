/* 
 * MemberBean.java  
 * 
 * version v1.2
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;


/**
 * 会员共通bean
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 上午10:44:22
 * @since 
 */
public class MemberBean implements Serializable{
    /**"主键，标示**/
    private String memid;
    /**会员ID**/
    private String memberId;
    /** 合作机构 */
    private long instiId;
    /**"会员名称**/
    private String memberName;
    /**"登陆会员名称**/
    private String loginName;
    /**登录密码**/
    private String pwd;
    /**支付密码**/
    private String paypwd;
    /**实名等级，01-未实名，02-姓名+身份证,03-银行卡校验,04-证件审核**/
    private String realnameLv;
    /**手机**/
    private String phone;
    /**邮箱**/
    private String email;
    /**会员类型，01-个人，02-企业**/
    private String memberType;
    /**会员状态，00-正常，02-锁定，99-注销**/
    private String memberStatus;
    /**注册认证，01-手机认证，02-邮箱认证，03-Both**/
    private String registerIdent;
    /** 操作人ID */
    private long operUserId;

    public String getMemid() {
        return memid;
    }
    public void setMemid(String memid) {
        this.memid = memid;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getPaypwd() {
        return paypwd;
    }
    public void setPaypwd(String paypwd) {
        this.paypwd = paypwd;
    }
    public String getRealnameLv() {
        return realnameLv;
    }
    public void setRealnameLv(String realnameLv) {
        this.realnameLv = realnameLv;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMemberType() {
        return memberType;
    }
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
    public String getMemberStatus() {
        return memberStatus;
    }
    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
    public String getRegisterIdent() {
        return registerIdent;
    }
    public void setRegisterIdent(String registerIdent) {
        this.registerIdent = registerIdent;
    }
    public long getOperUserId() {
        return operUserId;
    }
    public void setOperUserId(long operUserId) {
        this.operUserId = operUserId;
    }
    public long getInstiId() {
        return instiId;
    }
    public void setInstiId(long instiId) {
        this.instiId = instiId;
    }

    
}
