/* 
 * MerchBusi.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;


/**
 * 企业会员
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午12:02:26
 * @since 
 */
public class EnterpriseBusi extends Enterprise  {


    private String businessActorId;
    
    private String businessActorName;

    /**
     *
     * @return
     */
    @Override
    public String getBusinessActorNo() {
        return null;
    }

    public String getBusinessActorId() {
        return businessActorId;
    }
    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }
    public String getBusinessActorName() {
        return businessActorName;
    }
    public void setBusinessActorName(String businessActorName) {
        this.businessActorName = businessActorName;
    }
}
