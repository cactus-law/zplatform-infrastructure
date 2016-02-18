/* 
 * PojoMemberBase.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月7日 下午4:36:02
 * @since 
 */
@Entity
@Table(name="T_MEMBER_BASE")
public class PojoMemberBase {
    /**会员号**/
    private String memberid;
    /**会员名称**/
    private String merchname;
    /**会员类型**/
    private BusinessActorType merchtype;
    /**清算周期**/
    private Long setlcycle;
    /**产品版本**/
    private String prdtver;
    /**扣率版本**/
    private String feever;
    /**分润版本**/
    private String spiltver;
    /**风控版本**/
    private String riskver;
    /**路由版本**/
    private String routver;
    /**收银台版本**/
    private String cashver;
    /**上级商户**/
    private String parent;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    /**会员所属机构**/
    private Long merchinsti;
    @Id
    @Column(name = "MEMBERID")
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    @Column(name = "MERCHNAME")
    public String getMerchname() {
        return merchname;
    }
    public void setMerchname(String merchname) {
        this.merchname = merchname;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MemberSqlType")
    @Column(name = "MERCHTYPE")
    public BusinessActorType getMerchtype() {
        return merchtype;
    }
    public void setMerchtype(BusinessActorType merchtype) {
        this.merchtype = merchtype;
    }
    @Column(name = "SETLCYCLE")
    public Long getSetlcycle() {
        return setlcycle;
    }
    public void setSetlcycle(Long setlcycle) {
        this.setlcycle = setlcycle;
    }
    @Column(name = "PRDTVER")
    public String getPrdtver() {
        return prdtver;
    }
    public void setPrdtver(String prdtver) {
        this.prdtver = prdtver;
    }
    @Column(name = "FEEVER")
    public String getFeever() {
        return feever;
    }
    public void setFeever(String feever) {
        this.feever = feever;
    }
    @Column(name = "SPILTVER")
    public String getSpiltver() {
        return spiltver;
    }
    public void setSpiltver(String spiltver) {
        this.spiltver = spiltver;
    }
    @Column(name = "RISKVER")
    public String getRiskver() {
        return riskver;
    }
    public void setRiskver(String riskver) {
        this.riskver = riskver;
    }
    @Column(name = "ROUTVER")
    public String getRoutver() {
        return routver;
    }
    public void setRoutver(String routver) {
        this.routver = routver;
    }
    @Column(name = "CASHVER")
    public String getCashver() {
        return cashver;
    }
    public void setCashver(String cashver) {
        this.cashver = cashver;
    }
    @Column(name = "PARENT")
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
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
    @Column(name = "MERCHINSTI")
    public Long getMerchinsti() {
        return merchinsti;
    }
    public void setMerchinsti(Long merchinsti) {
        this.merchinsti = merchinsti;
    }

}
