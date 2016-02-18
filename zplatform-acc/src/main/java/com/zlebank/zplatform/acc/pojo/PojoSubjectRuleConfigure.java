/* 
 * PojoSubjectRule.java  
 * 
 * version 
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.commons.dao.pojo.Pojo;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月25日 下午9:04:57
 * @since
 */
@Entity
@Table(name = "T_ACC_ENTRY_RULE")
public class PojoSubjectRuleConfigure extends Pojo {

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "ACC_ENTRY_RULE_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo")})
    @Id
    @GeneratedValue(generator = "id_gen")
    @Override
    public long getId() {
        return id;
    }
    // 状态
    public RuleStatusType status;
    // 交易类型
    private String txntype;
    // 科目标记 (付款人资金，付款人产品，收款人资金，收款人产品，父级资金，父级产品)
    private String flag;
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
    // 录入时间
    private Date inTime;
    // 录入人
    private Long inUser;
    // 修改时间
    private Date upTime;
    // 修改人
    private Long upUser;
    // 备注
    private String notes;
    // 备注
    private String remarks;
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.RuleStatusSqlType")
    @Column(name = "STATUS")
    public RuleStatusType getStatus() {
        return status;
    }
    @Column(name = "TXNTYPE")
    public String getTxntype() {
        return txntype;
    }
    @Column(name = "ACCT_CODE")
    public String getFlag() {
        return flag;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.CRDRSqlType")
    @Column(name = "CRDR")
    public CRDRType getCrdr() {
        return crdr;
    }
    @Column(name = "PRDTCODE")
    public String getProdductCode() {
        return prodductCode;
    }
    @Column(name = "CHNLCODE")
    public String getChannelCode() {
        return channelCode;
    }
    @Column(name = "IS_SYNC")
    public String getSyncFlag() {
        return syncFlag;
    }
    @Column(name = "ORDFORM")
    public String getEntryAlgorithm() {
        return entryAlgorithm;
    }
    @Column(name = "IS_SHOW")
    public String getIsShow() {
        return isShow;
    }
    @Column(name = "ORDERS")
    public Long getRuleOder() {
        return ruleOder;
    }
    @Column(name = "INTIME")
    public Date getInTime() {
        return inTime;
    }
    @Column(name = "INUSER")
    public Long getInUser() {
        return inUser;
    }
    @Column(name = "UPTIME")
    public Date getUpTime() {
        return upTime;
    }
    @Column(name = "UPUSER")
    public Long getUpUser() {
        return upUser;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setCrdr(CRDRType crdr) {
        this.crdr = crdr;
    }
    public void setProdductCode(String prodductCode) {
        this.prodductCode = prodductCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag;
    }
    public void setEntryAlgorithm(String entryAlgorithm) {
        this.entryAlgorithm = entryAlgorithm;
    }
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
    public void setRuleOder(Long ruleOder) {
        this.ruleOder = ruleOder;
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
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }
    public void setStatus(RuleStatusType status) {
        this.status = status;
    }

}
