package com.zlebank.zplatform.acc.bean.enums;

public enum AccCodeType {
    /**
     * 占位符
     */
    PLACEHODER("01"),
    /**
     * 父级科目
     */
    PARENTSUBJECT("02"),
    /**
     * 账户
     */
    ACCOUNT("03"),
    /**
     * 未知
     */
    UNKNOW("FF");
    ;
    private String code;
    
    private AccCodeType(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    public static AccCodeType fromValue(String value) {
        for(AccCodeType placehoderType:values()){
            if(placehoderType.code.equals(value)){
                return placehoderType;
            }
        }
        return UNKNOW;
    }
}
