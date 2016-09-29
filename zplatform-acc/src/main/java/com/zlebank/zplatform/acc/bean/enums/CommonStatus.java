/* 
 * CommonStatus.java  
 * 
 * version TODO
 *
 * 2016年9月29日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean.enums;

/**
 * 公用状态类型
 *
 * @author houyong
 * @version
 * @date 2016年9月29日 下午12:06:26
 * @since 
 */
public enum CommonStatus {
    /**正常**/
    NORMAL("00"),
    /**失效**/
    DISABLED("01"),
    /**未知**/
    UNKNOW("99");
    private String code;
    
    private CommonStatus(String code){
        this.code = code;
    }
    public static CommonStatus fromValue(String value) {
        for(CommonStatus status:values()){
            if(status.code.equals(value)){
                return status;
            }
        }
        return UNKNOW;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    
    
}
