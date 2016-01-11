/* 
 * AcctStatusType.java  
 * 
 * version v1.0
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
 * @date 2015年8月24日 下午4:29:10
 * @since 
 */
public enum AcctStatusType {
    /**正常**/
    NORMAL("00"),
    /**冻结**/
    FREEZE("11"),
    /**止入**/
    STOP_IN("10"),
    /**止出**/
    STOP_OUT("01"),
    /**注销**/
    LOGOUT("99"),
    /**未知代码**/
    UNKNOW("88");

 private String code;
    
    private AcctStatusType(String code){
        this.code = code;
    }
    
    public static AcctStatusType fromValue(String value) {
        for(AcctStatusType status:values()){
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
