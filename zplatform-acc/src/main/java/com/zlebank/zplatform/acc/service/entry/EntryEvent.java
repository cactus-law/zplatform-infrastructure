package com.zlebank.zplatform.acc.service.entry;
/**
 * 
 * EntryEvent
 *
 * @author yangying
 * @version
 * @date 2016年5月9日 下午5:24:19
 * @since
 */
public enum EntryEvent {
    /**
     * 初始状态
     */
    INIT("0100"), 
    /**
     * 交易成功
     */
    TRADE_SUCCESS("0200"), 
    /**
     * 交易失败
     */
    TRADE_FAIL("0201"), 
    /**
     * 申请
     */
    AUDIT_APPLY("0301"), 
    /**
     * 审核通过
     */
    AUDIT_PASS("0300"), 
    /**
     * 审核拒绝
     */
    AUDIT_REJECT("0302"), 
    /**
     * 对账成功
     */
    RECON_SUCCESS("0400"), 
    /**
     * 已结算
     */
    SETTED("0500"),
    /**
     * 未知
     */
    UNKNOW("FFFF");
    private String code;

    private EntryEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public static EntryEvent fromValue(String code){
        for(EntryEvent entryEvent:EntryEvent.values()){
            if(entryEvent.getCode().equals(code)){
                return entryEvent;
            }
        }
        return UNKNOW;
    }
}
