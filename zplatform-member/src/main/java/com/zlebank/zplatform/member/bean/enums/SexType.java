/* 
 * SexType.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean.enums;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月9日 下午9:35:27
 * @since 
 */
public enum SexType {
    /**女**/
	FEMALE(0),
    /**男**/
    MALE(1),
    /**未知状态**/
    UNKNOW(9);

 private Integer code;
    
    private SexType(Integer code){
        this.code = code;
    }
    
    public static SexType fromValue(Integer value) {
        for(SexType status:values()){
            if(status.code == value){
                return status;
            }
        }
        return UNKNOW;
    }
    
    public Integer getCode(){
        return code;
    }
}
