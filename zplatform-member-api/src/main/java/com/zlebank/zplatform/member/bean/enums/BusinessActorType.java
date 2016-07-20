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
 * 业务参与者（个人/企业 会员、合作机构）类型
 *
 * @author yangying
 * @version
 * @date 2015年8月21日 下午5:46:08
 * @since 
 */
public enum BusinessActorType {
    /** 个人 */
    INDIVIDUAL("01"),
    /** 企业 */
    ENTERPRISE("02"),
    /** 合作机构 */
    COOPINSTI("03"),
    /**通道*/
    CHANNEL("04"),
    /**产品*/
    PRODUCT("05"),
    /**未知*/
	UNKNOW("99");
	private String code; 

    public static BusinessActorType fromValue(String value) {
        for (BusinessActorType status : values()) {
            if (status.code.equals(value)) {
                return status;
            }
        }
        return UNKNOW;
    } 
    public String getCode() {
        return code;
    }
	
	private BusinessActorType(String code){
		this.code = code;
	}
}
