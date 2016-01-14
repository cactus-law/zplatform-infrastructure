/* 
 * BusinessAcct.java  
 * 
 * version 1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.commons.dao.pojo.Pojo;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 上午11:45:44
 * @since
 */
@Entity
@Table(name="T_ACC_BUSIACCT")
public class PojoBusiAcct extends Pojo {

    private Usage usage;
    private String businessActorId;
    private long accountId;
    private String busiAcctCode;
    private String busiAcctName;

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "ACC_BUSIACCT_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo")})
    @Id
    @GeneratedValue(generator = "id_gen")
    @Override
    public long getId() {
        return id;
    }

    @Column(name = "usage",nullable=false)
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.UsageSqlType")
    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    @Column(name = "BUSINESS_ACTOR_ID", precision = 11)
    public String getBusinessActorId() {
        return businessActorId;
    }
    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }
 
    @Column(name = "ACCT_ID") 
    public long getAccountId() {
        return accountId;
    }
    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    @Column(name = "BUSIACCT_CODE")
    public String getBusiAcctCode() {
        return busiAcctCode;
    }
    public void setBusiAcctCode(String busiAcctCode) {
        this.busiAcctCode = busiAcctCode;
    }
    @Column(name="BUSIACCT_NAME")
    public String getBusiAcctName() {
        return busiAcctName;
    }

    public void setBusiAcctName(String busiAcctName) {
        this.busiAcctName = busiAcctName;
    }
    
}
