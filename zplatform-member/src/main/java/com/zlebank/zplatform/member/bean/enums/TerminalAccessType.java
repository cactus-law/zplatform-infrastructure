package com.zlebank.zplatform.member.bean.enums;

/**
 * 
 * 接入类型
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月14日 下午6:15:21
 * @since
 */
public enum TerminalAccessType {
    /** 无线 */
    WIRELESS(1),
    /** 个人门户 */
    INVPORTAL(2),
    /** 商户门户 */
    MERPORTAL(3),
    /** WAP */
    WAP(4),
    /** 开放接口  */
    OPENAPI(5),
    /** 未知 */
    UNKNOW(-1);
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
