/* 
 * PojoAccFrozenTask.java  
 * 
 * version TODO
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.FrozenStatusType;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.commons.dao.pojo.Pojo;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午5:13:30
 * @since 
 */
@Entity
@Table(name = "T_ACC_FROZEN_TASK")
public class PojoAccFrozenTask extends Pojo {

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "ACC_FROZEN_TASK_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }
    
    /** T_ACC_ACCT.ID **/
    private PojoAbstractSubject accId;
    /** 账户号(科目代码4-2-2-*) **/
    private String acctCode;
    /** 交易流水号 **/
    private String txnseqno;
    /** 本次交易的冻结余额 **/
    private Money frozenBalance;
    /** 冻结开始时间 **/
    private Date frozenSTime;
    /** 冻结时间(单位:min) **/
    private Long frozenTime;
    /** 插入的时候计算=FROZEN_S_TIME+FROZEN_TIME(时间任务拿这个来判断) **/
    private Date unfrozenTime;
    /** 状态0:已解冻1:冻结中 **/
    private FrozenStatusType status;
    /** 数据创建时间 **/
    private Date intime;
    /** 数据创建人 **/
    private Long inuser;
    /** 数据更新时间 **/
    private Date uptime;
    /** 数据更新人 **/
    private Long upuser;
    /** 备注 **/
    private String notes;
    /** 备注 **/
    private String remarks;
    /** 0:记录未被锁定1:记录被锁定 **/
    private LockStatusType isLock;

    @ManyToOne(optional=true)
    @JoinColumn(name = "ACC_ID",nullable=true)
    public PojoAbstractSubject getAccId() {
        return accId;
    }
    public void setAccId(PojoAbstractSubject accId) {
        this.accId = accId;
    }
    @Column(name = "ACCT_CODE")
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    @Column(name = "TXNSEQNO")
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="FROZEN_BALANCE"))})
    @Column(name = "FROZEN_BALANCE")
    public Money getFrozenBalance() {
        return frozenBalance;
    }
    public void setFrozenBalance(Money frozenBalance) {
        this.frozenBalance = frozenBalance;
    }
    @Column(name = "FROZEN_S_TIME")
    public Date getFrozenSTime() {
        return frozenSTime;
    }
    public void setFrozenSTime(Date frozenSTime) {
        this.frozenSTime = frozenSTime;
    }
    @Column(name = "FROZEN_TIME")
    public Long getFrozenTime() {
        return frozenTime;
    }
    public void setFrozenTime(Long frozenTime) {
        this.frozenTime = frozenTime;
    }
    @Column(name = "UNFROZEN_TIME")
    public Date getUnfrozenTime() {
        return unfrozenTime;
    }
    public void setUnfrozenTime(Date unfrozenTime) {
        this.unfrozenTime = unfrozenTime;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.FrozenStatusSqlType")
    @Column(name = "STATUS")
    public FrozenStatusType getStatus() {
        return status;
    }
    public void setStatus(FrozenStatusType status) {
        this.status = status;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "UPTIME")
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    @Column(name = "UPUSER")
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.LockStatusSqlType")
    @Column(name = "IS_LOCK")
    public LockStatusType getIsLock() {
        return isLock;
    }
    public void setIsLock(LockStatusType isLock) {
        this.isLock = isLock;
    }


}
