/* 
 * SubjectRuleVo.java  
 * 
 * version TODO
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import com.zlebank.zplatform.acc.bean.enums.AccCodeType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月25日 下午8:08:26
 * @since
 */

public class SubjectRule implements Bean {
    // id
    public long id;
    // 交易类型
    private String txntype;
    // 科目标记 （付款方资产科目/付款方产品/收款方资产/收款方产品/父级或者顶级商户/普通科目）
    private String acctCode;
    // 业务代码占位符类型
    private AccCodeType acctCodeType;
    // 余额方向
    private CRDRType crdr;
    // 产品代码
    private String prodductCode;
    // 渠道代码
    private String channelCode;
    // 是否同步记账
    private String syncFlag;
    // 分录金额计算 ：A本金 ：B佣金 ：C手续费 计算符号+-*/
    private String entryAlgorithm;
    // 是否显示
    private String isShow;
    // 分录顺序
    private Long ruleOder;
    // 状态
    public RuleStatusType status;

    // 备注
    private String notes;
    // 备注
    private String remarks;
    // 借贷方向(页面显示)
    private String crdrType;
    // 状态 (页面显示)
    private String actionStatus;
    // 页面其他科目种类
    private String flags;
    private EntryEvent entryEvent;

    public String getFlags() {
        return flags;
    }
    public void setFlags(String flags) {
        this.flags = flags;
    }
    public String getActionStatus() {
        return actionStatus;
    }
    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }
    public String getCrdrType() {
        return crdrType;
    }
    public void setCrdrType(String crdrType) {
        this.crdrType = crdrType;
    }
    public RuleStatusType getStatus() {
        return status;
    }
    public void setStatus(RuleStatusType status) {
        this.status = status;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTxntype() {
        return txntype;
    }
    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public CRDRType getCrdr() {
        return crdr;
    }
    public void setCrdr(CRDRType crdr) {
        this.crdr = crdr;
    }
    public String getProdductCode() {
        return prodductCode;
    }
    public void setProdductCode(String prodductCode) {
        this.prodductCode = prodductCode;
    }
    public String getChannelCode() {
        return channelCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public String getSyncFlag() {
        return syncFlag;
    }
    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag;
    }
    public String getEntryAlgorithm() {
        return entryAlgorithm;
    }
    public void setEntryAlgorithm(String entryAlgorithm) {
        this.entryAlgorithm = entryAlgorithm;
    }
    public String getIsShow() {
        return isShow;
    }
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    public Long getRuleOder() {
        return ruleOder;
    }
    public void setRuleOder(Long ruleOder) {
        this.ruleOder = ruleOder;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public AccCodeType getAcctCodeType() {
        return acctCodeType;
    }
    public void setAcctCodeType(AccCodeType acctCodeType) {
        this.acctCodeType = acctCodeType;
    }
    public EntryEvent getEntryEvent() {
        return entryEvent;
    }
    public void setEntryEvent(EntryEvent entryEvent) {
        this.entryEvent = entryEvent;
    }
}
