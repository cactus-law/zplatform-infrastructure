/* 
 * Person.java  
 * 
 * version TODO
 *
 * 2015年11月6日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月6日 下午5:13:35
 * @since 
 */
public class PersonManager implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5050281551367925110L;
    /**会员ID**/
    private String pMemberid;
    /**支付密码**/
    private String payPwd;
    /**登录密码**/
    private String loginPwd;
    /**性别**/
    private String sex;
    /**QQ号**/
    private String qq;
    /**省**/
    private Long province;
    /**城市**/
    private Long city;
    /**街道**/
    private Long street;
    /**详细地址**/
    private String adress;
    /**邮编**/
    private String postcode;
    /**生日**/
    private Date birthday;
    /**证件类型**/
    private String cardtype;
    /**证件号码**/
    private String cardno;
    /**固定电话**/
    private String telno;
    /**传真号码**/
    private String faxno;
    /**微信**/
    private String wechat;
    /**微博**/
    private String microblog;
    /**最后登录**/
    private Date lastLoginTime;
    /**最后登出**/
    private Date lastLogoutTime;
    /**最后登录ip**/
    private String lastRemoteHost;
    /**最后登录地址**/
    private String lastRemoteAddress;
    /**最后登录代理**/
    private String lastRemoteAgent;
    /**安全问题**/
    private String securityQues;
    /**安全问题答案**/
    private String securityAnswer;
    /**问候**/
    private String greeting;
    /**等级Id**/
    private Long gradeId;
    /**状态99停用，**/
    private String status;
    /**绑定电话**/
    private String bindPhone;
    /**绑定邮箱**/
    private String bindEmail;
    /**写入人**/
    private Long pInuser;
    /**写入时间**/
    private Date pIntime;
    /**更新人**/
    private Long upuser;
    /**更新时间**/
    private Date uptime;
    /**是否vip**/
    private Long vipflag;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    public String getpMemberid() {
        return pMemberid;
    }
    public void setpMemberid(String pMemberid) {
        this.pMemberid = pMemberid;
    }
    public String getPayPwd() {
        return payPwd;
    }
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
    public String getLoginPwd() {
        return loginPwd;
    }
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public Long getProvince() {
        return province;
    }
    public void setProvince(Long province) {
        this.province = province;
    }
    public Long getCity() {
        return city;
    }
    public void setCity(Long city) {
        this.city = city;
    }
    public Long getStreet() {
        return street;
    }
    public void setStreet(Long street) {
        this.street = street;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    public String getCardno() {
        return cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    public String getTelno() {
        return telno;
    }
    public void setTelno(String telno) {
        this.telno = telno;
    }
    public String getFaxno() {
        return faxno;
    }
    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getMicroblog() {
        return microblog;
    }
    public void setMicroblog(String microblog) {
        this.microblog = microblog;
    }
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }
    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }
    public String getLastRemoteHost() {
        return lastRemoteHost;
    }
    public void setLastRemoteHost(String lastRemoteHost) {
        this.lastRemoteHost = lastRemoteHost;
    }
    public String getLastRemoteAddress() {
        return lastRemoteAddress;
    }
    public void setLastRemoteAddress(String lastRemoteAddress) {
        this.lastRemoteAddress = lastRemoteAddress;
    }
    public String getLastRemoteAgent() {
        return lastRemoteAgent;
    }
    public void setLastRemoteAgent(String lastRemoteAgent) {
        this.lastRemoteAgent = lastRemoteAgent;
    }
    public String getSecurityQues() {
        return securityQues;
    }
    public void setSecurityQues(String securityQues) {
        this.securityQues = securityQues;
    }
    public String getSecurityAnswer() {
        return securityAnswer;
    }
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    public String getGreeting() {
        return greeting;
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    public Long getGradeId() {
        return gradeId;
    }
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBindPhone() {
        return bindPhone;
    }
    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }
    public String getBindEmail() {
        return bindEmail;
    }
    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }
    public Long getpInuser() {
        return pInuser;
    }
    public void setpInuser(Long pInuser) {
        this.pInuser = pInuser;
    }
    public Date getpIntime() {
        return pIntime;
    }
    public void setpIntime(Date pIntime) {
        this.pIntime = pIntime;
    }
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    public Long getVipflag() {
        return vipflag;
    }
    public void setVipflag(Long vipflag) {
        this.vipflag = vipflag;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
 
    
    
}
