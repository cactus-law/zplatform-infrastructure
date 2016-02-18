/* 
 * Type.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 下午5:56:21
 * @since
 */
public enum Type {
    A("a"), N("n"), AN("an"), ANS("ans"), DATE("date"), TIMESTAMP("timestamp"), MONEY(
            "money"),UNKNOW("");
    private String code;
    private Type(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Type formatValue(String code) {
        for (Type type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return UNKNOW;
    }
}
