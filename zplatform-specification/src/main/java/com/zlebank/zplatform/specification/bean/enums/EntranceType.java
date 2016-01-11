/* 
 * EntranceType.java  
 * 
 * version 1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.bean.enums;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 上午10:38:47
 * @since
 */
public enum EntranceType {
    /**接口方式接入**/
    API("api"), 
    /**应用程序接入**/
    APP("app"),
    /**管理系统接入**/
    MS("ms"), 
    /**未知接入**/
    UNKNOW("");
    private String code;
    private EntranceType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EntranceType fromValue(String code) {
        for (EntranceType status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return UNKNOW;
    }
}
