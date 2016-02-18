/* 
 * AccentyrQuery.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import java.util.Date;

import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月7日 下午8:23:25
 * @since 
 */
public class AccEntryQuery implements Bean{
    /**支付订单号**/
    private String payordno;
    /**交易流水号**/
    private String txnseqno;
    /**账户号（会计账户号）**/
    private String acctCode;
    /**状态:00-已记账，01-未记账 02-待记账**/
    private AccEntryStatus status;
    /**开始记账时间**/
    private Date startTime;
    /**记账结束时间**/
    private Date endTime;
    /**凭证号**/
    private Long voucherCode;
    /**交易类型代码**/
    private String busiCode;
    /**是否分页**/
    private Boolean isPage;
    
    public Long getVoucherCode() {
        return voucherCode;
    }
    public void setVoucherCode(Long voucherCode) {
        this.voucherCode = voucherCode;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public AccEntryStatus getStatus() {
        return status;
    }
    public void setStatus(AccEntryStatus status) {
        this.status = status;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    public Boolean getIsPage() {
        return isPage;
    }
    public void setIsPage(Boolean isPage) {
        this.isPage = isPage;
    }
}
