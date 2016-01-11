/* 
 * RuleStatusType.java  
 * 
 * version TODO
 *
 * 2015年8月29日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean.enums;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月29日 下午8:44:06
 * @since 
 */
public enum RuleStatusType {
    
        /**正常**/
        NORMAL("00"),
        /**注销**/
        LOGOUT("99"),
        /**停用**/
        STOP("01"),
        /**未知代码**/
        UNKNOW("88");

     private String code;
        
        private RuleStatusType(String code){
            this.code = code;
        }
        
        public static RuleStatusType fromValue(String value) {
            for(RuleStatusType status:values()){
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
