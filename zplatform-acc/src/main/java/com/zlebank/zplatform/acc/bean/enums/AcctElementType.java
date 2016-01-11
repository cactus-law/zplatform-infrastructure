/* 
 * AcctElementType.java  
 * 
 * version V1.0
 *
 * 2015年8月24日 
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
 * @date 2015年8月24日 下午4:05:34
 * @since
 */
public enum AcctElementType {
    /** 资产类 **/
    ASSETS("1"),
    /** 负债类 **/
    LIABILITY("2"),
    /** 共同类 **/
    COMMON("3"),
    /** 所有权益类 **/
    OWNERS_EQUITY("4"),
    /** 成本类 **/
    COST("5"),
    /** 损益类 **/
    PROFIT_AND_LOSS("6"),
    /** 未知科目 **/
    UNKNOW("99");

    private String code;

    private AcctElementType(String code) {
        this.code = code;
    }

    public static AcctElementType fromValue(String value) {
        for (AcctElementType status : values()) {
            if (status.code.equals(value)) {
                return status;
            }
        }
        return UNKNOW;
    }
    /** 会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益） **/
    public String getCode() {
        return code;
    }
}
