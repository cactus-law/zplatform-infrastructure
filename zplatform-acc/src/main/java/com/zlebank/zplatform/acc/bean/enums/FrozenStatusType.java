/* 
 * FrozenStatusType.java  
 * 
 * version TODO
 *
 * 2015年8月26日 
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
 * @date 2015年8月26日 下午1:48:42
 * @since 
 */
public enum FrozenStatusType {
    /**已解冻**/
    NORMAL("00"),
    /**冻结**/
    FREEZE("01"),
    /**未知代码**/
    UNKNOW("99");

 private String code;
    
    private FrozenStatusType(String code){
        this.code = code;
    }
    
    public static FrozenStatusType fromValue(String value) {
        for(FrozenStatusType status:values()){
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
