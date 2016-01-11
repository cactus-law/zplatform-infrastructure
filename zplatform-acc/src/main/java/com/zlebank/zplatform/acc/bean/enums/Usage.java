/* 
 * MinorUsage.java  
 * 
 * version 1.0
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
 * @author yangying
 * @version
 * @date 2015年8月31日 上午11:25:22
 * @since
 */
public enum Usage {
    /**普通资金账户*/
    BASICPAY(PrimaryUsage.FUND, PrimaryUsage.FUND.getCode()+"01"),
    /**保证金账户*/
    BAIL(PrimaryUsage.FUND, PrimaryUsage.FUND.getCode()+"02"), 
    /**未知账户*/
    UNKNOW(null, "FFF");
    private PrimaryUsage primaryUsage;
    private String code;
    private Usage(PrimaryUsage primaryUsage, String code) {
        this.primaryUsage = primaryUsage;
        this.code = code;
    }
    public static Usage fromValue(String value) {
        for (Usage minorUsage : values()) {
            if (minorUsage.code.equals(value)) {
                return minorUsage;
            }
        }
        return UNKNOW;
    }

    public PrimaryUsage getPrimaryUsage() {
        return primaryUsage;
    }
    public String getCode() {
        return code;
    }
}
