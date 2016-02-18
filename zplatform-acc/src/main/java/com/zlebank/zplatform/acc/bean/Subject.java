/* 
 * Subject.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import com.zlebank.zplatform.acc.bean.enums.AcctElementType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Subject
 *
 * @author yangying
 * @version
 * @date 2015年8月20日 下午1:55:45
 * @since
 */
public class Subject implements Bean {
    private long id;

    private String acctCode;

    private String acctCodeName;

    private CRDRType crdr;

    private AcctElementType acctElement;

    private String dac;

    private String notes;

    private String remarks;

    private Subject parentSubject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAcctCode() {
        return acctCode;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    public String getAcctCodeName() {
        return acctCodeName;
    }

    public void setAcctCodeName(String acctCodeName) {
        this.acctCodeName = acctCodeName;
    }

    public CRDRType getCrdr() {
        return crdr;
    }

    public void setCrdr(CRDRType crdr) {
        this.crdr = crdr;
    }

    public AcctElementType getAcctElement() {
        return acctElement;
    }

    public void setAcctElement(AcctElementType acctElement) {
        this.acctElement = acctElement;
    }

    public String getDac() {
        return dac;
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

    public void setDac(String dac) {
        this.dac = dac;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Subject getParentSubject() {
        return parentSubject;
    }

    public void setParentSubject(Subject parentSubject) {
        this.parentSubject = parentSubject;
    }

 
    
}
