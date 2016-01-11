/* 
 * MemberStatusType.java  
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
 * @date 2015年9月9日 下午12:20:09
 * @since 
 */
public enum MemberStatusType {
    /**正常**/
    NORMAL("00"),
    /**未激活**/
    NOT_ACTIVATED("01"),
    /**系统锁定（冻结）**/
    SYSTEM_LOCK("02"),
    /**停用**/
    Stop("99"),
    /**未知状态**/
    UNKNOW("88");

 private String code;
    
    private MemberStatusType(String code){
        this.code = code;
    }
    
    public static MemberStatusType fromValue(String value) {
        for(MemberStatusType status:values()){
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
