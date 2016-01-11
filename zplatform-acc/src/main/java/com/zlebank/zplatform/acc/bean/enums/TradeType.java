/* 
 * TradeType.java  
 * 
 * version v1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean.enums;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 下午4:48:31
 * @since 
 */
public enum TradeType {
    /**消费**/
    BUY("10000001"),
    /**充值**/
    RECHARGE("20000001"),
    /**提现**/
    WITHDRAW("30000001"),
    /**退款**/
    REFUND("40000001"),
    /**转账**/
    TRANSFER("50000001"),
    /**保证金**/
    BAIL("60000001"),
    /**未知类型**/
    UNKNOW("999");

    private String code; 

    public static TradeType fromValue(String value) {
        for (TradeType status : values()) {
            if (status.code.equals(value)) {
                return status;
            }
        }
        return UNKNOW;
    } 
    public String getCode() {
        return code;
    }
    private TradeType(String code){
        this.code = code;
    }
}
