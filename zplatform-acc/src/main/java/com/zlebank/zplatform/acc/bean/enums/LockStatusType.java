/* 
 * LockStatusType.java  
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
 * @date 2015年8月26日 下午1:52:47
 * @since 
 */
public enum LockStatusType {
    /**记录未被锁定**/
    UNLOCK("0"),
    /**记录被锁定状态**/
    LOCK("1"),
    /**未知代码**/
    UNKNOW("9");

 private String code;
    
    private LockStatusType(String code){
        this.code = code;
    }
    
    public static LockStatusType fromValue(String value) {
        for(LockStatusType status:values()){
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
