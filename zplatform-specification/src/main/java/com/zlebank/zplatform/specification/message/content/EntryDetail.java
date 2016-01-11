/* 
 * EntryDetail.java  
 * 
 * version TODO
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message.content;

/**
 * 收支明细【报文返回结果用】
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月19日 下午3:43:29
 * @since 
 */
public class EntryDetail {
    /**流水号**/
    private String seqNo;
    /**账户号（业务账户号）**/
    private String acctCode;
    /**收支标记**/
    private String flag;
    /**金额**/
    private String amount;
    /**货币  https://en.wikipedia.org/wiki/ISO_4217**/
    private String currency;

    public String getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    /**
     *
     * @return
     */
//    @Override
//    public String toString() {
//        return "{\"seqNo\":\"" + seqNo + "\",\"acctCode\":\"" + acctCode
//                + "\",\"flag\":\"" + flag + "\",\"amount\":\"" + amount
//                + "\",\"currency\":\"" + currency + "\"} ";
//    }
}
