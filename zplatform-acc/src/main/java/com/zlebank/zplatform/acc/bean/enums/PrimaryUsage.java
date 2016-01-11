/* 
 * MainUsage.java  
 * 
 * version 1.0
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
 * @author yangying
 * @version
 * @date 2015年8月31日 上午11:16:44
 * @since 
 */
public enum PrimaryUsage {
   /**资金账户*/
    FUND("1"),
    /**产品账户*/
    PRODUCT("2"),
    /**未知账户*/
    UNKNOW("0");
    
    private String code;
    private PrimaryUsage(String code){
        this.code = code;
    }
    public static PrimaryUsage fromValue(String value) {
        for (PrimaryUsage usage : values()) {
            if (usage.code.equals(value)) {
                return usage;
            }
        }
        return UNKNOW;
    } 
    /**
     * Return the code represent Usage.It values from 1 to F,that means the max number of category  is 16
     * @return 1-F
     */
    public String getCode() {
        return code;
    }
}
