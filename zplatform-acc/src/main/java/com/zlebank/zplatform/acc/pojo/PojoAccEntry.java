/* 
 * PojoAccEntry.java  
 * 
 * version TODO
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 下午5:03:01
 * @since 
 */
@Entity
@Table(name = "T_ACC_ENTRY")
public class PojoAccEntry {
    /**凭证号**/
    private Long voucherCode;
    /**账号/科目号**/
    private String acctCode;
    /**借贷标志1-借2-贷**/
    private CRDRType crdr;
    /**金额(分)**/
    private Money amount;
    /**1:渠道手续费**/
    private String amtOrFee;
    /**摘要(最好必填，可来自分录规则表)**/
    private String text;
    /**交易流水号**/
    private String txnseqno;
    /**分录事件*/
    private EntryEvent entryEvent;
    /**支付订单号**/
    private String payordno;
    /**状态:00-已记账，01-未记账 02-待记账**/
    private AccEntryStatus status;
    /**1-支付类；2-存货类；3-手工**/
    private Long entryType;
    /**分录时间**/
    private Date inTime;
    /**交易类型代码**/
    private String busiCode;
    /**交易渠道代码**/
    private String chnCode;
    /**交易产品代码**/
    private String procCode;
    /** 0:记录未被锁定1:记录被锁定 **/
    private LockStatusType isLock;
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "ACC_VOUCHER_CODE"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    @Column(name = "VOUCHER_CODE")
    public Long getVoucherCode() {
        return voucherCode;
    }
    public void setVoucherCode(Long voucherCode) {
        this.voucherCode = voucherCode;
    }
    @Column(name = "ACCT_CODE")
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.CRDRSqlType")
    @Column(name = "CRDR")
    public CRDRType getCrdr() {
        return crdr;
    }
    public void setCrdr(CRDRType crdr) {
        this.crdr = crdr;
    }
    @Column(name = "AMOUNT")
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    @Column(name = "AMT_OR_FEE")
    public String getAmtOrFee() {
        return amtOrFee;
    }
    public void setAmtOrFee(String amtOrFee) {
        this.amtOrFee = amtOrFee;
    }
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Column(name = "TXNSEQNO")
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    @Column(name = "PAYORDNO")
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.AccEntrySqlStatus")
    @Column(name = "STATUS")
    public AccEntryStatus getStatus() {
        return status;
    }
    public void setStatus(AccEntryStatus status) {
        this.status = status;
    }
    @Column(name = "ENTRY_TYPE")
    public Long getEntryType() {
        return entryType;
    }
    public void setEntryType(Long entryType) {
        this.entryType = entryType;
    }
    @Column(name = "IN_TIME")
    public Date getInTime() {
        return inTime;
    }
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    @Column(name = "BUSI_CODE")
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    @Column(name = "CHN_CODE")
    public String getChnCode() {
        return chnCode;
    }
    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }
    @Column(name = "PROC_CODE")
    public String getProcCode() {
        return procCode;
    }
    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.LockStatusSqlType")
    @Column(name = "IS_LOCK")
    public LockStatusType getIsLock() {
        return isLock;
    }
    public void setIsLock(LockStatusType isLock) {
        this.isLock = isLock;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.EntryEventSqlType")
    @Column(name = "ENTRY_EVENT")
    public EntryEvent getEntryEvent() {
        return entryEvent;
    }
    public void setEntryEvent(EntryEvent entryEvent) {
        this.entryEvent = entryEvent;
    }
}
