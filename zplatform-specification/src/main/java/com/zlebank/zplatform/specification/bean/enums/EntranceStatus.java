/* 
 * MemberEntranceStatus.java  
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
 * @date 2015年9月16日 上午10:28:02
 * @since 
 */
public enum EntranceStatus {
    /**初始化**/
    INIT("01"),
    /**已签到**/
    SIGNED("00"),
    /**未知**/
    UNKNOW("99");
    private String code;
    private EntranceStatus(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    public static EntranceStatus fromValue(String code) {
        for(EntranceStatus status:values()){
            if(status.code == code){
                return status;
            }
        }
        return UNKNOW;
    }
}
