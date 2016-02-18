/* 
 * RealNameLvType.java  
 * 
 * version TODO
 *
 * 2016年1月20日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean.enums;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月20日 上午10:25:47
 * @since 
 */
public enum RealNameLvType {
    /**未实名认证**/
    LV1("01"),
    /**姓名+身份证**/
    LV2("02"),
    /**银行卡校验**/
    LV3("03"),
    /**证件审核**/
    LV4("04"),
    /**未知状态**/
    UNKNOW("99");

    private String code;
    
    private RealNameLvType(String code){
        this.code = code;
    }
    
    public static RealNameLvType fromValue(String value) {
        for(RealNameLvType status:values()){
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
