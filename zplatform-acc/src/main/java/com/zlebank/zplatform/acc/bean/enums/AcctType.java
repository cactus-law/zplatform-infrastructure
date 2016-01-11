/* 
 * AcctType.java  
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
 * @date 2015年8月24日 下午3:49:53
 * @since 
 */
public enum AcctType {
    /**叶子节点**/
    ACCOUNT("00"),
    /**中间科目**/
    SUBJECT("01"),
    /**未知科目**/
    UNKNOW("99");

 private String code;
    
    private AcctType(String code){
        this.code = code;
    }
    
    public static AcctType fromValue(String value) {
        for(AcctType status:values()){
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
