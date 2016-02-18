/* 
 * PojoBusiness.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
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

import com.zlebank.zplatform.acc.bean.enums.BusinessEntryStatus;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午2:08:01
 * @since 
 */
@Entity
@Table(name = "T_BUSINESS")
public class PojoBusiness {
    private Long busiId;
    
    private String busiCode;
    
    private String busiName;
    
    private String busiType;
    
    private Character riskFlag;
    
    private BusinessEntryStatus status;
    
    private String notes;
    
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "BUSSINESS_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    @Column(name = "BUSIID")
    public Long getBusiId() {
        return busiId;
    }
    @Column(name="BUSICODE")
    public String getBusiCode() {
        return busiCode;
    }
    @Column(name="BUSINAME")
    public String getBusiName() {
        return busiName;
    }
    @Column(name="BUSITYPE")
    public String getBusiType() {
        return busiType;
    }
    @Column(name="RISKFLAG")
    public Character getRiskFlag() {
        return riskFlag;
    }
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.BusinessEntrySqlStatus")
    @Column(name="STATUS")
    public BusinessEntryStatus getStatus() {
        return status;
    }
    @Column(name="NOTES")
    public String getNotes() {
        return notes;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public void setRiskFlag(Character riskFlag) {
        this.riskFlag = riskFlag;
    }

    public void setStatus(BusinessEntryStatus status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
    
    
    
    
    
    

}
