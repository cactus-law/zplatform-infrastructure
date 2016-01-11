/* 
 * Account.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Account
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午1:53:37
 * @since
 */
public class Account implements Bean {
    private long id;
    private String acctCode;
    private AcctType AcctType;
    private Subject parentSubject;
    private Money balance;
    private Money fronzenBalance;
    private Money totalBalance;
    private String acctCodeName;
    //后来加上页面需要的参数
    private String upUserId;
    //页面参数状态
    private String status;
    
    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpUserId() {
        return upUserId;
    }

    public void setUpUserId(String upUserId) {
        this.upUserId = upUserId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAcctCode() {
        return acctCode;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    public AcctType getAcctType() {
        return AcctType;
    }

    public void setAcctType(AcctType acctType) {
        AcctType = acctType;
    }

    public Subject getParentSubject() {
        return parentSubject;
    }

    public void setParentSubject(Subject parentSubject) {
        this.parentSubject = parentSubject;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Money getFronzenBalance() {
        return fronzenBalance;
    }

    public void setFronzenBalance(Money fronzenBalance) {
        this.fronzenBalance = fronzenBalance;
    }

    public Money getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Money totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getAcctCodeName() {
        return acctCodeName;
    }

    public void setAcctCodeName(String acctCodeName) {
        this.acctCodeName = acctCodeName;
    }


}
