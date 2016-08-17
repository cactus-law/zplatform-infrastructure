/* 
 * RealNameBean.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;


/**
 * 实名认证信息
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午4:57:49
 * @since 
 */
public class RealNameBean implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8172954291929782442L;
    /**会员号**/
    private String memberId;
    /**真实姓名**/
    private String realname;
    /**证件类型**/
    private String identiType;
    /**证件号**/
    private String identiNum;
    /**状态，00-有效，01-无效**/
    private String status;
    /**录入人**/
    private Long inuser;
    /**更新人**/
    private Long upuser;

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getIdentiType() {
        return identiType;
    }
    public void setIdentiType(String identiType) {
        this.identiType = identiType;
    }
    public String getIdentiNum() {
        return identiNum;
    }
    public void setIdentiNum(String identiNum) {
        this.identiNum = identiNum;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    
}
