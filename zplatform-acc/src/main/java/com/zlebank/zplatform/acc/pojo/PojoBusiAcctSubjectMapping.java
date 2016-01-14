/* 
 * PojoAccSubjectSelector.java  
 * 
 * version 1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.commons.dao.pojo.Pojo;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月25日 上午11:40:09
 * @since
 */
@Entity
@Table(name = "T_ACC_SUBJECT_MAPPING")
public class PojoBusiAcctSubjectMapping extends Pojo {
    //会员类型,01-个人,02-商户,03-企业
    private BusinessActorType businessActorType;
   //会员编号
    private String businessActorId;
   //主要产品号
    private String mainProductNo;
   //次要产品号
    private String minorProductNo;
    //用途
    private Usage usage; 
    //科目代码
    private PojoSubject subject;

    @Id
    @Column(name = "ID")
    @Override
    public long getId() {
        return id;
    }

    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.BusinessActorTypeSqlType")
    @Column(name = "BUSINESS_ACTOR_TYPE")
    public BusinessActorType getBusinessActorType() {
        return businessActorType;
    }
    public void setBusinessActorType(BusinessActorType businessActorType) {
        this.businessActorType = businessActorType;
    }

    @Column(name = "BUSINESS_ACTOR_ID",nullable=true)
    public String getBusinessActorId() {
        return businessActorId;
    }
    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }

    @Column(name = "MAIN_PRODUCT_NO")
    public String getMainProductNo() {
        return mainProductNo;
    }
    public void setMainProductNo(String mainProductNo) {
        this.mainProductNo = mainProductNo;
    }

    @Column(name = "MINOR_PRODUCT_NO")
    public String getMinorProductNo() {
        return minorProductNo;
    }
    public void setMinorProductNo(String minorProductNo) {
        this.minorProductNo = minorProductNo;
    }

    @Column(name = "USAGE")
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.UsageSqlType")
    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    @ManyToOne(targetEntity = PojoSubject.class)
    @JoinColumn(name = "ACC_ACCT_ID")
    public PojoSubject getSubject() {
        return subject;
    }

    public void setSubject(PojoSubject subject) {
        this.subject = subject;
    }
}
