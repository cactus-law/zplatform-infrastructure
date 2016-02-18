/* 
 * personBusi.java  
 * 
 * version TODO
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;


/**
 * 个人会员
 *
 * @author yangpeng
 * @version
 * @date 2015年9月10日 下午4:46:08
 * @since 
 */
public class PersonBusi extends Individual{
    
    private String businessActorId;
    
    public String getBusinessActorId() {
        return businessActorId;
    }
    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }
}
