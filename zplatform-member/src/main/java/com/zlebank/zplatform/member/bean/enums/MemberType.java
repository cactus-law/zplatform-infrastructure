/* 
 * MemberType.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean.enums;

/**
 * 会员类型
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午2:55:56
 * @since 
 */
public enum MemberType {
    /** 01 个人会员**/
    INDIVIDUAL("01"),
    /** 02 企业会员**/
    ENTERPRISE("02"),
    /**未知状态**/
    UNKNOW("99");

 private String code;
    
    private MemberType(String code){
        this.code = code;
    }
    
    public static MemberType fromValue(String value) {
        for(MemberType status:values()){
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
