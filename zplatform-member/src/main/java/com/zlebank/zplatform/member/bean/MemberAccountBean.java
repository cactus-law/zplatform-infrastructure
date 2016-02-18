/* 
 * MemberBalanceBean.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.math.BigDecimal;

import com.zlebank.zplatform.acc.bean.enums.Usage;

/**
 * 会员余额
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 上午11:58:46
 * @since 
 */
public class MemberAccountBean {
    /** 业务号 */
    private String busiCode;
    /** 余额 **/
    private BigDecimal balance;
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
    
}
