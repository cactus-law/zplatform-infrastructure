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

import com.zlebank.zplatform.member.bean.enums.LoginType;
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
    /**主键标识**/
    private long memId;
    /**会员Id**/
    private String memberid;
    /**会员姓名**/
    private String membername;
    /**手机**/
    private String phone;
    /**邮箱**/
    private String email;
    /**会员类型,01-个人,02-商户,03-企业**/
    private MemberType membertype;
    /**01未激活，00正常，02系统锁定（冻结）**/
    private MemberStatusType memberstat;
    /**1线上手机，2线下手机，3线上邮箱**/
    private LoginType logintype;
    /**是否验证邮箱**/
    private String emailpass;
    /**是否验证手机**/
    private String phonepass;
    /**商户号(暂定=memberid)**/
    private String merchno;
    /**个人会员登录用户名**/
    private String loginame;
    /**锁定状态**/
    private String lockstat;
    /**锁定时间**/
    private Date lockdate;
    /**创建人**/
    private Long inuser;
    /**创建时间**/
    private Date intime;
    /**修改人**/
    private Long upuser;
    /**修改时间**/
    private Date uptime;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    
    @SequenceGenerator(name="gen",sequenceName="SEQ_T_MERCH_DETA_MERCHID",initialValue=1,allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen")
    @Column(name="MEMID" ,nullable=false,unique=true)
    public long getMemId() {
        return memId;
    }

    public void setMemId(long memId) {
        this.memId = memId;
    }

    @Column(name = "MEMBERID")
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    @Column(name = "MEMBERNAME")
    public String getMembername() {
        return membername;
    }
    public void setMembername(String membername) {
        this.membername = membername;
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
    @Column(name = "MEMBERTYPE")
    public MemberType getMembertype() {
        return membertype;
    }
    public void setMembertype(MemberType membertype) {
        this.membertype = membertype;
    }
    @Column(name = "MEMBERSTAT")
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MemberStatusSqlType")
    public MemberStatusType getMemberstat() {
        return memberstat;
    }
    public void setMemberstat(MemberStatusType memberstat) {
        this.memberstat = memberstat;
    }
    @Column(name = "LOGINTYPE")
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.LoginSqlType")
    public LoginType getLogintype() {
        return logintype;
    }
    public void setLogintype(LoginType logintype) {
        this.logintype = logintype;
    }
    @Column(name = "EMAILPASS")
    public String getEmailpass() {
        return emailpass;
    }
    public void setEmailpass(String emailpass) {
        this.emailpass = emailpass;
    }
    @Column(name = "PHONEPASS")
    public String getPhonepass() {
        return phonepass;
    }
    public void setPhonepass(String phonepass) {
        this.phonepass = phonepass;
    }
    @Column(name = "MERCHNO")
    public String getMerchno() {
        return merchno;
    }
    public void setMerchno(String merchno) {
        this.merchno = merchno;
    }
    @Column(name = "LOGINAME")
    public String getLoginame() {
        return loginame;
    }
    public void setLoginame(String loginame) {
        this.loginame = loginame;
    }
    @Column(name = "LOCKSTAT")
    public String getLockstat() {
        return lockstat;
    }
    public void setLockstat(String lockstat) {
        this.lockstat = lockstat;
    }
    @Column(name = "LOCKDATE")
    public Date getLockdate() {
        return lockdate;
    }
    public void setLockdate(Date lockdate) {
        this.lockdate = lockdate;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
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
}
