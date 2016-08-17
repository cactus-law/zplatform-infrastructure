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

import java.io.Serializable;


/**
 * 个人会员
 *
 * @author yangpeng
 * @version
 * @date 2015年9月10日 下午4:46:08
 * @since 
 */
public class PersonBusi extends Individual implements Serializable{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5031634707252309312L;
    private String businessActorId;
    
    public String getBusinessActorId() {
        return businessActorId;
    }
    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }
}
