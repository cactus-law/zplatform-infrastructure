/* 
 * AccEntryStatus.java  
 * 
 * version TODO
 *
 * 2015年8月31日 
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
 * @date 2015年8月31日 下午5:44:28
 * @since 
 */
public enum BusinessEntryStatus {
    /**在用**/
    NORMAL("00"),
    /**停用**/
    STOP("01"),
    /**未知状态**/
    UNKNOW("99");

 private String code;
    
    private BusinessEntryStatus(String code){
        this.code = code;
    }
    
    public static BusinessEntryStatus fromValue(String value) {
        for(BusinessEntryStatus status:values()){
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
