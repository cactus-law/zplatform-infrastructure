/* 
 * SubjectQuery.java  
 * 
 * version 
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import java.util.Date;

import com.zlebank.zplatform.acc.bean.enums.AcctElementType;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.pojo.Money;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月31日 下午4:43:37
 * @since 
 */
public class SubjectQuery {
    
    private long id;

    private String acctCode;

    private String acctCodeName;

    private CRDRType crdr;

    private AcctElementType acctElement;
    
    private Money balance;

    private Money frozenBalance;

    private Money creditBalance;

    private Money debitBalance;

    private Money totalBanance;

    private AcctStatusType status;

    private String dac;

    private Date inTime;

    private Long inUser;

    private Date upTime;

    private Long upUser;

    private String notes;

    private String remarks;
    
    private Long parentId;

    public long getId() {
        return id;
    }

    public String getAcctCode() {
        return acctCode;
    }

    public String getAcctCodeName() {
        return acctCodeName;
    }

    public CRDRType getCrdr() {
        return crdr;
    }

    public AcctElementType getAcctElement() {
        return acctElement;
    }

    public Money getBalance() {
        return balance;
    }

    public Money getFrozenBalance() {
        return frozenBalance;
    }

    public Money getCreditBalance() {
        return creditBalance;
    }

    public Money getDebitBalance() {
        return debitBalance;
    }

    public Money getTotalBanance() {
        return totalBanance;
    }

    public AcctStatusType getStatus() {
        return status;
    }

    public String getDac() {
        return dac;
    }

    public Date getInTime() {
        return inTime;
    }

    public Long getInUser() {
        return inUser;
    }

    public Date getUpTime() {
        return upTime;
    }

    public Long getUpUser() {
        return upUser;
    }

    public String getNotes() {
        return notes;
    }

    public String getRemarks() {
        return remarks;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    public void setAcctCodeName(String acctCodeName) {
        this.acctCodeName = acctCodeName;
    }

    public void setCrdr(CRDRType crdr) {
        this.crdr = crdr;
    }

    public void setAcctElement(AcctElementType acctElement) {
        this.acctElement = acctElement;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public void setFrozenBalance(Money frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public void setCreditBalance(Money creditBalance) {
        this.creditBalance = creditBalance;
    }

    public void setDebitBalance(Money debitBalance) {
        this.debitBalance = debitBalance;
    }

    public void setTotalBanance(Money totalBanance) {
        this.totalBanance = totalBanance;
    }

    public void setStatus(AcctStatusType status) {
        this.status = status;
    }

    public void setDac(String dac) {
        this.dac = dac;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public void setInUser(Long inUser) {
        this.inUser = inUser;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public void setUpUser(Long upUser) {
        this.upUser = upUser;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    

}
