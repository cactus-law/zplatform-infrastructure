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
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午12:02:26
 * @since 
 */
public class MerchBusi extends Merchant  {


    private String memberId;
    
    private String merchName;

    /**
     *
     * @return
     */
    @Override
    public String getMemberId() {
       
        return memberId;
    }


    public String getMerchName() {
        return merchName;
    }


    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    /**
     *
     * @return
     */
    @Override
    public String getMerchNo() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
