package com.zlebank.zplatform.member.bean.enums;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月7日 下午4:15:13
 * @since 
 */
public enum LoginType {
    /**线上手机**/
    ONLINE_PHONE("1"),
    /**线下手机**/
    OFFLINE_PHONE("2"),
    /**线上邮箱**/
    ONLINE_EMAIL("3"),
    /**未知状态**/
    UNKNOW("0");

 private String code;
    
    private LoginType(String code){
        this.code = code;
    }
    
    public static LoginType fromValue(String value) {
        for(LoginType status:values()){
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
