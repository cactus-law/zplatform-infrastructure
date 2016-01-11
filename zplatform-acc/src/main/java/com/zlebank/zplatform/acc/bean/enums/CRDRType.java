/* 
 * CRDR.java  
 * 
 * version TODO
 *
 * 2015年8月24日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean.enums;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月24日 下午4:01:55
 * @since 
 */
public enum CRDRType {
    /**贷记**/
    CR("C"),
    /**借记**/
    DR("D"),
    /**未知科目**/
    UNKNOW("9");

 private String code;
    
    private CRDRType(String code){
        this.code = code;
    }
    
    public static CRDRType fromValue(String value) {
        for(CRDRType status:values()){
            if(status.code.equals(value)){
                return status;
            }
        }
        return UNKNOW;
    }
    
    public String getCode(){
        return code;
    }
}
