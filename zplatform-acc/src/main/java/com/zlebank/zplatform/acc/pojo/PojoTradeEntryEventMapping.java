package com.zlebank.zplatform.acc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

@Entity
@Table(name = "T_TRADE_ENTRYEVENT_MAPPING")
public class PojoTradeEntryEventMapping {

    private long tid;
    private TradeType tradeType;
    private String implClassName;
    private boolean isInitStatus;
    private EntryEvent entryEvent;

    @Id
    public long getTid() {
        return tid;
    }
    public void setTid(long tid) {
        this.tid = tid;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.TradeTypeSqlType")
    @Column(name = "BUSI_CODE")
    public TradeType getTradeType() {
        return tradeType;
    }
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }
    @Column(name = "IMPL_CLASS_NAME")
    public String getImplClassName() {
        return implClassName;
    }

    public void setImplClassName(String implClassName) {
        this.implClassName = implClassName;
    }
    @Column(name = "IS_INIT_STATUS")
    public boolean isInitStatus() {
        return isInitStatus;
    }
    public void setInitStatus(boolean isInitStatus) {
        this.isInitStatus = isInitStatus;
    }
    @Column(name = "ENTRY_EVENT_CODE")
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.EntryEventSqlType")
    public EntryEvent getEntryEvent() {
        return entryEvent;
    }
    public void setEntryEvent(EntryEvent entryEvent) {
        this.entryEvent = entryEvent;
    }
}
