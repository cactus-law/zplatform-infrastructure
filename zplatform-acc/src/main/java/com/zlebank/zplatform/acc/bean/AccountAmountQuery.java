/* 
 * AccountAmountQuery.java  
 * 
 * version TODO
 *
 * 2015年10月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;


import com.zlebank.zplatform.commons.bean.Bean;

/**
 * 冻结查询条件
 *
 * @author yangpeng
 * @version
 * @date 2015年10月20日 下午5:48:40
 * @since 
 */
public class AccountAmountQuery implements Bean {
    
    /**会计账户号**/
    private String accCode;
    /** 交易流水号 **/
    private String txnseqno;
    /** 冻结开始时间 **/
    private String endFrozenSTime;
    /** 插入的时候计算=FROZEN_S_TIME+FROZEN_TIME(时间任务拿这个来判断) **/
    private String startUnfrozenTime;
    
    private String startFrozenSTime;
    private String endUnfrozenTime;
    private String status;
    
    
    
    public String getStartUnfrozenTime() {
        return startUnfrozenTime;
    }
    public void setStartUnfrozenTime(String startUnfrozenTime) {
        this.startUnfrozenTime = startUnfrozenTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAccCode() {
        return accCode;
    }
    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public String getEndFrozenSTime() {
        return endFrozenSTime;
    }
    public void setEndFrozenSTime(String endFrozenSTime) {
        this.endFrozenSTime = endFrozenSTime;
    }

    public String getStartFrozenSTime() {
        return startFrozenSTime;
    }
    public void setStartFrozenSTime(String startFrozenSTime) {
        this.startFrozenSTime = startFrozenSTime;
    }
    public String getEndUnfrozenTime() {
        return endUnfrozenTime;
    }
    public void setEndUnfrozenTime(String endUnfrozenTime) {
        this.endUnfrozenTime = endUnfrozenTime;
    }
  
    
    
    
}
