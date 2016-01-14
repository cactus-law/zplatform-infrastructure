package com.zlebank.zplatform.member.bean.enums;

public enum TerminalAccessType {
    WIRELESS(1),INVPORTAL(2),MERPORTAL(3),WAP(4),OPENAPI(5),UNKNOW(-1);
    private int code;
    
    private TerminalAccessType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return code;
    }
    
    public static TerminalAccessType fromValue(int code){
        for(TerminalAccessType terminalAccessType:TerminalAccessType.values()){
            if(terminalAccessType.getCode()==code){
                return terminalAccessType;
            }
        }
        return UNKNOW;
    }
}
