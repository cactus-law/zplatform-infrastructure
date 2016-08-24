/* 
 * FinanceProductQueryBean.java  
 * 
 * version TODO
 *
 * 2016年8月24日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zlebank.zplatform.acc.bean.enums.Usage;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月24日 下午4:14:55
 * @since 
 */
public class FinanceProductAccountBean implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6911376745999126552L;
    /** 业务号 */
    private String busiCode;
    /** 余额 **/
    private BigDecimal balance;
    /** 冻结余额 **/
    private BigDecimal frozenBalance;
    /** 总余额 **/
    private BigDecimal totalBalance;
    /** 状态 */
    private String status;
    /** 用途 */
    private Usage usage;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
    
    
}
