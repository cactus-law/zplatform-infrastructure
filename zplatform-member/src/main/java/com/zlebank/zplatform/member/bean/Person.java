/* 
 * Person.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.util.Date;

import com.zlebank.zplatform.member.bean.enums.SexType;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月9日 下午5:49:47
 * @since 
 */

public class Person  extends MemberBean{

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

    public String getParentMemberId() {
        return parentMemberId;
    }
    public void setParentMemberId(String parentMemberId) {
        this.parentMemberId = parentMemberId;
    }
    public SexType getSex() {
        return sex;
    }
    public void setSex(SexType sex) {
        this.sex = sex;
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
    public String getGradeId() {
        return gradeId;
    }
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWeibo() {
        return weibo;
    }
    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getQuestion1() {
        return question1;
    }
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }
    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getQuestion2() {
        return question2;
    }
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }
    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    public String getQuestion3() {
        return question3;
    }
    public void setQuestion3(String question3) {
        this.question3 = question3;
    }
    public String getAnswer3() {
        return answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}
