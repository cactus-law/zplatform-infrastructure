/* 
 * AccountAmount.java  
 * 
 * version TODO
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import java.util.Date;

import com.zlebank.zplatform.acc.bean.enums.FrozenStatusType;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午7:14:25
 * @since 
 */
public class AccountAmount implements Bean {
   
    /**主键**/
    private Long id;
    /**T_ACC_ACCT.ID**/
    private Long accId;
    /**交易流水号**/
    private String txnseqno; 
    /**本次交易的冻结余额**/
    private Money frozenBalance;
    /**冻结开始时间**/
    private Date frozenSTime;
    /**冻结时间(单位:min)**/
    private Long frozenTime;
    /**插入的时候计算=FROZEN_S_TIME+FROZEN_TIME(时间任务拿这个来判断)**/
    private Date unfrozenTime;
    /**备忘**/
    private String notes;
    /** 数据创建人 **/
    private Long inuser;
    /** 数据更新人 **/
    private Long upuser;
    

    /** 账户号(科目代码4-2-2-*) **/
    private String acctCode;
    /** 状态0:已解冻1:冻结中 **/
    private FrozenStatusType status;
    
    
    public FrozenStatusType getStatus() {
        return status;
    }
    public void setStatus(FrozenStatusType status) {
        this.status = status;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAccId() {
        return accId;
    }
    public void setAccId(Long accId) {
        this.accId = accId;
    }
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public Money getFrozenBalance() {
        return frozenBalance;
    }
    public void setFrozenBalance(Money frozenBalance) {
        this.frozenBalance = frozenBalance;
    }
    public Date getFrozenSTime() {
        return frozenSTime;
    }
    public void setFrozenSTime(Date frozenSTime) {
        this.frozenSTime = frozenSTime;
    }
    public Long getFrozenTime() {
        return frozenTime;
    }
    public void setFrozenTime(Long frozenTime) {
        this.frozenTime = frozenTime;
    }
    public Date getUnfrozenTime() {
        return unfrozenTime;
    }
    public void setUnfrozenTime(Date unfrozenTime) {
        this.unfrozenTime = unfrozenTime;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    
}
