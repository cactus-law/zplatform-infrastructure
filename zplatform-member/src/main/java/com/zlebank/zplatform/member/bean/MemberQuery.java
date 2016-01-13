/* 
 * Member.java  
 * 
 * version TODO
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.util.Date;

import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;

/**
 * Class Description
 *  会员查询 自身分录流水条件
 * @author yangpeng
 * @version
 * @date 2015年9月10日 上午11:15:08
 * @since 
 */
public class MemberQuery {
    /**支付订单号**/
    private String payordno;
    /**开始记账时间**/
    private Date startTime;
    /**记账结束时间**/
    private Date endTime;
    /**交易类型代码**/
    private String busiCode;
    /**账户号（业务账户号）**/
    private String acctCode;
    /**状态:00-已记账，01-未记账 02-待记账**/
    private AccEntryStatus status;
    //页面输入记账状态
    private String type;
    /**交易流水号**/
    private String txnseqno;
    
    
    
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public AccEntryStatus getStatus() {
        return status;
    }
    public void setStatus(AccEntryStatus status) {
        this.status = status;
    }
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
  

}
