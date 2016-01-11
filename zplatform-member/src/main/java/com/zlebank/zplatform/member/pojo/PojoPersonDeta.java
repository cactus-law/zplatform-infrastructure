/* 
 * PojoPersonDeta.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.member.bean.enums.MemberStatusType;
import com.zlebank.zplatform.member.bean.enums.SexType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月9日 下午9:05:35
 * @since 
 */
@Entity
@PrimaryKeyJoinColumn(name="PERSONID") 
@Table(name="T_PERSON_DETA")
public class PojoPersonDeta extends PojoMember{
    /**会员ID**/
    private String pMemberid;
    /**支付密码**/
    private String payPwd;
    /**登录密码**/
    private String loginPwd;
    /**性别**/
    private SexType sex;
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
    private MemberStatusType status;
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
 
   

    @Column(name = "PAY_PWD")
    public String getPayPwd() {
        return payPwd;
    }
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
    @Column(name = "LOGIN_PWD")
    public String getLoginPwd() {
        return loginPwd;
    }
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.SexSqlType")
    @Column(name = "SEX")
    public SexType getSex() {
        return sex;
    }
    public void setSex(SexType sex) {
        this.sex = sex;
    }
    @Column(name = "QQ")
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    @Column(name = "PROVINCE")
    public Long getProvince() {
        return province;
    }
    public void setProvince(Long province) {
        this.province = province;
    }
    @Column(name = "CITY")
    public Long getCity() {
        return city;
    }
    public void setCity(Long city) {
        this.city = city;
    }
    @Column(name = "STREET")
    public Long getStreet() {
        return street;
    }
    public void setStreet(Long street) {
        this.street = street;
    }
    @Column(name = "ADRESS")
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    @Column(name = "POSTCODE")
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Column(name = "CARDTYPE")
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    @Column(name = "CARDNO")
    public String getCardno() {
        return cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    @Column(name = "TELNO")
    public String getTelno() {
        return telno;
    }
    public void setTelno(String telno) {
        this.telno = telno;
    }
    @Column(name = "FAXNO")
    public String getFaxno() {
        return faxno;
    }
    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }
    @Column(name = "WECHAT")
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    @Column(name = "MICROBLOG")
    public String getMicroblog() {
        return microblog;
    }
    public void setMicroblog(String microblog) {
        this.microblog = microblog;
    }
    @Column(name = "LAST_LOGIN_TIME")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    @Column(name = "LAST_LOGOUT_TIME")
    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }
    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }
    @Column(name = "LAST_REMOTE_HOST")
    public String getLastRemoteHost() {
        return lastRemoteHost;
    }
    public void setLastRemoteHost(String lastRemoteHost) {
        this.lastRemoteHost = lastRemoteHost;
    }
    @Column(name = "LAST_REMOTE_ADDRESS")
    public String getLastRemoteAddress() {
        return lastRemoteAddress;
    }
    public void setLastRemoteAddress(String lastRemoteAddress) {
        this.lastRemoteAddress = lastRemoteAddress;
    }
    @Column(name = "LAST_REMOTE_AGENT")
    public String getLastRemoteAgent() {
        return lastRemoteAgent;
    }
    public void setLastRemoteAgent(String lastRemoteAgent) {
        this.lastRemoteAgent = lastRemoteAgent;
    }
    @Column(name = "SECURITY_QUES")
    public String getSecurityQues() {
        return securityQues;
    }
    public void setSecurityQues(String securityQues) {
        this.securityQues = securityQues;
    }
    @Column(name = "SECURITY_ANSWER")
    public String getSecurityAnswer() {
        return securityAnswer;
    }
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    @Column(name = "GREETING")
    public String getGreeting() {
        return greeting;
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    @Column(name = "GRADE_ID")
    public Long getGradeId() {
        return gradeId;
    }
    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }
    @Column(name = "STATUS")
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MemberStatusSqlType")
    public MemberStatusType getStatus() {
        return status;
    }
    public void setStatus(MemberStatusType status) {
        this.status = status;
    }
    @Column(name = "BIND_PHONE")
    public String getBindPhone() {
        return bindPhone;
    }
    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone;
    }
    @Column(name = "BIND_EMAIL")
    public String getBindEmail() {
        return bindEmail;
    }
    public void setBindEmail(String bindEmail) {
        this.bindEmail = bindEmail;
    }
    @Column(name = "INUSER")
    public Long getPInuser() {
        return pInuser;
    }
    public void setPInuser(Long inuser) {
        this.pInuser = inuser;
    }
    @Column(name = "INTIME")
    public Date getPIntime() {
        return pIntime;
    }
    public void setPIntime(Date intime) {
        this.pIntime = intime;
    }
    @Column(name = "UPUSER")
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    @Column(name = "UPTIME")
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    @Column(name = "VIPFLAG")
    public Long getVipflag() {
        return vipflag;
    }
    public void setVipflag(Long vipflag) {
        this.vipflag = vipflag;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name = "MEMBERID")
    public String getpMemberid() {
        return pMemberid;
    }
    public void setpMemberid(String pMemberid) {
        this.pMemberid = pMemberid;
    }
 

}
