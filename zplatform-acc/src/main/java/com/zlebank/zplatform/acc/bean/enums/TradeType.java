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
    /**行业消费*/
    INDUSTRY_PAY("10000005"),
    /**授信消费*/
    GRANTCREDIT_PAY("10000006"),
    /**消费**/
    BUY("10000001"),
    /**充值**/
    RECHARGE("20000001"),
    /**保证金线上充值**/
    BAIL("20000002"),
    /**保证金线下充值**/
    BAIL_OFFLINE("20000003"),
    /**授信账户充值*/
    GRANTCREDIT_RECHANGE("20000004"),
    /**行业充值*/
    INDUSTRY_RECHANGE("20000005"),
    /**提现**/
    WITHDRAW("30000001"),
    /**退款至银行卡**/
    REFUND_BANKCARD("40000001"),
    /**退款至账户**/
    REFUND_ACCOUNT("40000002"),
    /**授信账户退款*/
    GRANTCREDIT_REFUND("40000004"),
    /**转账**/
    TRANSFER("50000001"),
    /**保证金提取-转账**/
    BAIL_DRAW("50000002"),
    /**行业-转账**/
    INDUSTRY_TRANSFER("50000003"),
    /**行业-提取**/
    INDUSTRY_DRAW("50000004"),
    /**代付*/
    INSTEADPAY("70000001"),
    /**手工充值*/
    RECHANGE_MANUAL ("90000001"),
    /** 实名认证*/
    REAL_NAME_AUTH("80000001"),
    /**
     * 余额购买产品
     */
    PRODUCT_PURCHASE_BACLANCE("11000002"),
    /**
     * 购买产品-网关、快捷支付
     */
    PRODUCT_PURCHASE_CHARGE("11000001"),
    /**
     * 产品-充值
     */
    PRODUCT_CHARGE_ONLINE("12000001"),
    /**
     * 产品-线下充值
     */
    PRODUCT_CHARGE("12000002"),
    
    /**
     * 产品-提现
     */
    PRODUCT_WITHDRAW("13000001"),
    /**
     * 产品资金赎回
     */
    PRODUCT_CAPITAL_RANSOM("14000001"),
    /**
     * 产品资金退款
     */
    PRODUCT_CAPITAL_REFUND("14000002"),
    /**
     * 日终结算
     */
    PRODUCT_ENDDAY_SETTEL("15000001"),
    /**
     * 融资人还款
     */
    PRODUCT_FINANCIERS_REFUND("16000001"),
    /**
     * 产品资金划转
     */
    PRODUCT_CAPITAL_TRANSFER("17000001"),
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
