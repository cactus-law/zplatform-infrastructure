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
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月10日 下午4:46:08
 * @since 
 */
public class personBusi extends Individual{
    
    private String memberId;
    
    /**
     *
     * @return
     */
    @Override
    public String getMemberId() {
       
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    
  
}
