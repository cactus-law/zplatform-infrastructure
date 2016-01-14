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
 * 
 * 开户类型
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月13日 下午5:07:47
 * @since
 */
public enum OpenAccountType {
    /** 个人 */
    INDIVIDUAL("01"),
    /** 企业 */
    ENTERPRISE("02"),
	/** 合作机构 */
    COOPINSTI("03"),

	UNKNOW("99");
	private String code; 

    public static OpenAccountType fromValue(String value) {
        for (OpenAccountType status : values()) {
            if (status.code.equals(value)) {
                return status;
            }
        }
        return UNKNOW;
    } 
    public String getCode() {
        return code;
    }
	
	private OpenAccountType(String code){
		this.code = code;
	}
}
