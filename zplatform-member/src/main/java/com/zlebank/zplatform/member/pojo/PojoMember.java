/* 
 * PojoMember.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.member.bean.enums.MemberStatusType;
import com.zlebank.zplatform.member.bean.enums.MemberType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月7日 下午4:15:13
 * @since 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="T_MEMBER")
    
public  class PojoMember {
    /**"主键，标示**/
    private long memid;
    /**会员ID**/
    private String memberId;
    /**合作机构**/
    private String instiCode;
    /**会员昵称**/
    private String memberName;
    /**登录名**/
    private String loginName;
    /**登录密码**/
    private String pwd;
    /**支付密码**/
    private String payPwd;
    /**实名等级，01-未实名，02-姓名+身份证,03-银行卡校验,04-证件审核**/
    private String realnameLv;
    /**手机**/
    private String phone;
    /**邮箱**/
    private String email;
    /**会员类型，01-个人，02-企业**/
    private MemberType memberType;
    /**会员状态，00-正常，02-冻结，99-注销**/
    private MemberStatusType status;
    /**注册认证，01-手机认证，02-邮箱认证，03-Both**/
    private String registerIdent;
    /**锁定时间**/
    private Date lockTime;
    /**会员注册时间**/
    private Date intime;
    /**修改时间**/
    private Date uptime;
    @SequenceGenerator(name="gen",sequenceName="SEQ_T_MERCH_DETA_MERCHID",initialValue=1,allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen")
    @Column(name="MEMID" ,nullable=false,unique=true)
    public long getMemid() {
        return memid;
    }
    public void setMemid(long memid) {
        this.memid = memid;
    }
    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name = "INSTI_CODE")
    public String getInstiCode() {
        return instiCode;
    }
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    @Column(name = "MEMBER_NAME")
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    @Column(name = "PWD")
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @Column(name = "PAY_PWD")
    public String getPayPwd() {
        return payPwd;
    }
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
    @Column(name = "REALNAME_LV")
    public String getRealnameLv() {
        return realnameLv;
    }
    public void setRealnameLv(String realnameLv) {
        this.realnameLv = realnameLv;
    }
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MemberSqlType")
    @Column(name = "MEMBER_TYPE")
    public MemberType getMemberType() {
        return memberType;
    }
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MemberStatusSqlType")
    @Column(name = "STATUS")
    public MemberStatusType getStatus() {
        return status;
    }
    public void setStatus(MemberStatusType status) {
        this.status = status;
    }
    @Column(name = "REGISTER_IDENT")
    public String getRegisterIdent() {
        return registerIdent;
    }
    public void setRegisterIdent(String registerIdent) {
        this.registerIdent = registerIdent;
    }
    @Column(name = "LOCK_TIME")
    public Date getLockTime() {
        return lockTime;
    }
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "UPTIME")
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

}
