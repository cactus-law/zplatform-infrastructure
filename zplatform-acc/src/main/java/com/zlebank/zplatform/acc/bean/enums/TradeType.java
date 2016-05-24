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
    /**银行卡支付**/
    BANKCARD_PAY("10000001"),
    /**消费**/
    ACCOUNT_PAY("10000002"),
    /**消费**/
    BUY("10000001"),
    /**充值**/
    RECHARGE("20000001"),
    /**提现**/
    WITHDRAW("30000001"),
    /**退款至银行卡**/
    REFUND_BANKCARD("40000001"),
    /**退款至账户**/
    REFUND_ACCOUNT("40000002"),
    /**转账**/
    TRANSFER("50000001"),
    /**保证金**/
    BAIL("60000001"),
    /**代付*/
    INSTEADPAY("70000001"),
    /**手工提现*/
    RECHANGE_MANUAL ("90000001"),
    /** 实名认证*/
    REAL_NAME_AUTH("80000001"),
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
