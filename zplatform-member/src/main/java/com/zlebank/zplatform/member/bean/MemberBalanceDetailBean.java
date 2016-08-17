/* 
 * MemberBalanceDetailBean.java  
 * 
 * version v1.2
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午12:02:58
 * @since 
 */
public class MemberBalanceDetailBean implements Bean, Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 904576437009401473L;
    /**收支类型  00：收入； 01：支出 **/
    private String budgetType;
    /** 金额 **/
    private BigDecimal txnAmt ;
    /** 时间**/
    private String txnTime;

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }
    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getBudgetType() {
        return budgetType;
    }
    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }
    public String getTxnTime() {
        return txnTime;
    }
    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

}
