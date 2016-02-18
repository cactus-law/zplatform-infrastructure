/* 
 * PojoTxnsSplitAccount.java  
 * 
 * version v1.0
 *
 * 2015年11月3日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 分账信息
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年11月3日 下午4:55:54
 * @since 
 */
@Entity
@Table(name = "T_TXNS_SPLIT_ACCOUNT")
public class PojoTxnsSplitAccount {

    /**标示**/
    private Long tid;
    /**会员号**/
    private String memberid;
    /**分账金额**/
    private BigDecimal amount;
    /**佣金**/
    private BigDecimal commamt;
    /**交易序列号**/
    private String txnseqno;
    /**备注**/
    private String remarks;
    /**备注**/
    private String notes;

    @Column(name = "TID")
    @Id
    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    @Column(name = "MEMBERID")
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    @Column(name = "AMOUNT")
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Column(name = "COMMAMT")
    public BigDecimal getCommamt() {
        return commamt;
    }
    public void setCommamt(BigDecimal commamt) {
        this.commamt = commamt;
    }
    @Column(name = "TXNSEQNO")
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

}
