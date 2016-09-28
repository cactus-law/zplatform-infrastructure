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
 * Usage
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 上午11:25:22
 * @since
 */
public enum Usage {
    /**现金账户*/
    BASICPAY("basicpay","101"),
    /**银行存款*/
    BANKDEPOSIT("bankdeposit","102"),
    /**应收银行*/
    RECEIVABLEBANK("receivablebank","103"),
    /**证联收转存款*/
    ZLREVTRANDEPOSIT("zlrevtrandeposit","104"),
    /**应付银行*/
    BANKPAYABLE("bankpayable","105"), 
    /**通道手续费支出*/
    CHANNELFEECOST("channelfeecost","106"),
    /**企业待结算*/
    WAITSETTLE("waitsettle","107"),
    /**应付待分润*/
    PROFITSPAYABLE("profitspayable","108"),
    /**保证金*/
    BAIL("bail","109"),
    /**手续费收入*/
    FEEINCOME("feeincome","110"),
    /**授信金额*/
    GRANTCREDIT("grantcredit","111"), 
    
    /**未知*/
    UNKNOW("unkonw","FFF");
    private String code;
    private String name;
    private Usage(String name,String code) {
        this.name = name;
        this.code = code;
    }
    public static Usage fromValue(String value) {
        for (Usage usage : values()) {
            if (usage.code.equals(value)) {
                return usage;
            }
        }
        return UNKNOW;
    }
    
    public String getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
}
