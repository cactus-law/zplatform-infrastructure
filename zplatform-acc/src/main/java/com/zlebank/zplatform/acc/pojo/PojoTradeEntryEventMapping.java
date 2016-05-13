package com.zlebank.zplatform.acc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.dao.pojo.Pojo;

@Entity
@Table(name = "T_TRADE_ENTRYEVENT_MAPPING")
public class PojoTradeEntryEventMapping extends Pojo{

    private long tid;
    private TradeType tradeType;
    private String implClassName;
    private EntryEvent entryEvent;

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "T_TRADE_ENTRY_EVENT_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo")})
    @Id
    @Column(name = "tid")
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return tid;
    }
    public void setId(long tid) {
        this.tid = tid;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.TradeSqlType")
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
    @Column(name = "ENTRY_EVENT")
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.EntryEventSqlType")
    public EntryEvent getEntryEvent() {
        return entryEvent;
    }
    public void setEntryEvent(EntryEvent entryEvent) {
        this.entryEvent = entryEvent;
    }
}
