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

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月9日 下午5:49:47
 * @since 
 */

public class Person  {
    /**会员姓名**/
    private String membername;
    /**手机**/
    private String phone;
    /**邮箱**/
    private String email;
    /**个人会员登录用户名**/
    private String loginame;
    /**登录密码**/
    private String loginPwd;
    /**性别**/
    private String sex;
   
    public String getMembername() {
        return membername;
    }
    public void setMembername(String membername) {
        this.membername = membername;
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
    public String getLoginame() {
        return loginame;
    }
    public void setLoginame(String loginame) {
        this.loginame = loginame;
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
}
