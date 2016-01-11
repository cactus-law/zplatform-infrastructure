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
public enum AccEntryStatus {
    /**已记账**/
    ACCOUNTED("00"),
    /**未记账**/
    NOT_ACCOUNTED("01"),
    /**待记账**/
    WAIT_ACCOUNTED("02"),
    /**未知状态**/
    UNKNOW("99");

 private String code;
    
    private AccEntryStatus(String code){
        this.code = code;
    }
    
    public static AccEntryStatus fromValue(String value) {
        for(AccEntryStatus status:values()){
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
