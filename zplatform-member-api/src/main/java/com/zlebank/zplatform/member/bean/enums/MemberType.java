/* 
 * MemberType.java  
 * 
 * version 1.0
 *
 * 2015年8月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean.enums;

/**
 * Member type
 *
 * @author yangying
 * @version
 * @date 2015年8月21日 下午5:46:08
 * @since 
 */
public enum MemberType {
    /**
     *  商户
     */
	MERCHANT("02"),
	/**
	 * 个人
	 */
	Individual("01"),
	/**
	 * 企业
	 */
	BUSINESS("03"),UNKNOW("99");
	private String code; 

    public static MemberType fromValue(String value) {
        for (MemberType status : values()) {
            if (status.code.equals(value)) {
                return status;
            }
        }
        return UNKNOW;
    } 
    public String getCode() {
        return code;
    }
	
	private MemberType(String code){
		this.code = code;
	}
}
