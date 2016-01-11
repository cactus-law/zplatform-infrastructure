/* 
 * BusiType.java  
 * 
 * version TODO
 *
 * 2015年9月10日 
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
 * @date 2015年9月10日 下午9:00:59
 * @since 
 */
public enum BusiType {
    /**消费**/
    CONSUMER("1000"),
    /**充值**/
    REPAIDP("2000"),
    /**提现类**/
    CASH("3000"),
    /**退款类**/
    REFUND("4000"),
    /**转账类**/
    TRANSFER("5000"),
    /**保障金**/
    SECURITY("6000"),
    /**未知**/
    UNKNOW("9999");

 private String code;
    
    private BusiType(String code){
        this.code = code;
    }
    
    public static BusiType fromValue(String value) {
        for(BusiType status:values()){
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
