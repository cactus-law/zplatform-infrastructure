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
    private String parentMemberId;
    /**性别**/
    private SexType sex;
    /**固定电话**/
    private String telno;
    /**传真号码**/
    private String faxno;
    /**等级Id**/
    private String gradeId;
    /**微信**/
    private String wechat;
    /**QQ号**/
    private String qq;
    /**微博**/
    private String weibo;
    /**省**/
    private String province;
    /**城市**/
    private String city;
    /**街道**/
    private String street;
    /**详细地址**/
    private String address;
    /**生日**/
    private String birthday;
    /**安全问题1**/
    private String question1;
    /**安全问题答案1**/
    private String answer1;
    /**安全问题2**/
    private String question2;
    /**安全问题答案2**/
    private String answer2;
    /**安全问题3**/
    private String question3;
    /**安全问题答案3**/
    private String answer3;
    /**写入时间**/
    private Date intime;
    /**更新时间**/
    private Date uptime;
   
    @Column(name = "MEMBER_ID")
    public String getParentMemberId() {
        return parentMemberId;
    }
    public void setParentMemberId(String parentMemberId) {
        this.parentMemberId = parentMemberId;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.SexSqlType")
    @Column(name = "SEX")
    public SexType getSex() {
        return sex;
    }
    public void setSex(SexType sex) {
        this.sex = sex;
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
    @Column(name = "GRADE_ID")
    public String getGradeId() {
        return gradeId;
    }
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }
    @Column(name = "WECHAT")
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    @Column(name = "QQ")
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    @Column(name = "WEIBO")
    public String getWeibo() {
        return weibo;
    }
    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }
    @Column(name = "PROVINCE")
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "BIRTHDAY")
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    @Column(name = "QUESTION1")
    public String getQuestion1() {
        return question1;
    }
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }
    @Column(name = "ANSWER1")
    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    @Column(name = "QUESTION2")
    public String getQuestion2() {
        return question2;
    }
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }
    @Column(name = "ANSWER2")
    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    @Column(name = "QUESTION3")
    public String getQuestion3() {
        return question3;
    }
    public void setQuestion3(String question3) {
        this.question3 = question3;
    }
    @Column(name = "ANSWER3")
    public String getAnswer3() {
        return answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
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
