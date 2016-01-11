/* 
 * Account.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message.content;


/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 下午2:52:12
 * @since
 */
public class Account {
   
    private String acctCode;
   
    private String acctName;
    /**可用余额**/
    private String balance;
    /**冻结余额**/
    private String frozenBalance;
    /**总余额**/
    private String  totalBalance;
    /**状态**/
    private String status;
   /**货币**/
    private String currency;
    /**账户用途**/
    private String usage;
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getAcctName() {
        return acctName;
    }
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getFrozenBalance() {
        return frozenBalance;
    }
    public void setFrozenBalance(String frozenBalance) {
        this.frozenBalance = frozenBalance;
    }
    public String getTotalBalance() {
        return totalBalance;
    }
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    
    
    
 
}
