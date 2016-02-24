/* 
 * PojoMerchDeta.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.member.bean.enums.MerchStatusType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月7日 下午4:37:54 
 * @since 
 */
@Entity
@Table(name="T_MERCH_DETA")
public class PojoMerchDeta {
  
    /**主键**/
    private Long merchId;
    /**会员外键**/
    private Long memId;
    /**会员号**/
    private String memberId;
    /**上级商户**/
    private String parent;
    /**清算周期**/
    private Long setlcycle;
    /**清算类型**/
    private String  setltype;
    /**结算行号**/
    private String bankcode;
    /**开户行号**/
    private String banknode;
    /**开户账号**/
    private String accnum;
    /**开户名**/
    private String accname;
    /**服务费**/
    private Money charge;
    /**保证金**/
    private Money deposit;
    /**合约开始日期**/
    private Date agreemtStart;
    /**合约终止日期**/
    private Date agreemtEnd;
    /**产品代码**/
    private String prdtver;
    /**扣率版本**/
    private String feever;
    /**分润版本**/
    private String spiltver;
    /**风控版本**/
    private String riskver;
    /**路由版本**/
    private String routver;
    /**状态**/
    private MerchStatusType merchStatus;
    /**写入人**/
    private Long mInuser;
    /**写入时间**/
    private Date mIntime;
    /**初审人**/
    private Long stexauser;
    /**初审时间**/
    private Date stexatime;
    /**初审意见**/
    private String stexaopt;
    /**复核人**/
    private Long cvlexauser;
    /**复核时间**/
    private Date cvlexatime;
    /**复核意见**/
    private String cvlexaopt;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
 
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "MERCH_DETA_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    @Column(name = "MERCH_ID")
    public Long getMerchId() {
        return merchId;
    }
    public void setMerchId(Long merchId) {
        this.merchId = merchId;
    }
    @Column(name = "MEM_ID")
    public Long getMemId() {
        return memId;
    }
    public void setMemId(Long memId) {
        this.memId = memId;
    }
    
    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    @Column(name = "SETLCYCLE")
    public Long getSetlcycle() {
        return setlcycle;
    }
    @Column(name = "BANKCODE")
    public String getBankcode() {
        return bankcode;
    }
    @Column(name = "BANKNODE")
    public String getBanknode() {
        return banknode;
    }
    @Column(name = "ACCNUM")
    public String getAccnum() {
        return accnum;
    }
    @Column(name = "ACCNAME")
    public String getAccname() {
        return accname;
    }
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="CHARGE"))}) 
    public Money getCharge() {
        return charge;
    }
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="DEPOSIT"))}) 
    public Money getDeposit() {
        return deposit;
    }
    @Column(name = "AGREEMT_START")
    public Date getAgreemtStart() {
        return agreemtStart;
    }
    @Column(name = "AGREEMT_END")
    public Date getAgreemtEnd() {
        return agreemtEnd;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MerchStatusSqlType")
    @Column(name = "STATUS")
    public MerchStatusType getMerchStatus() {
        return merchStatus;
    }
    @Column(name = "INUSER")
    public Long getmInuser() {
        return mInuser;
    }
    @Column(name = "INTIME")
    public Date getmIntime() {
        return mIntime;
    }
    @Column(name = "STEXAUSER")
    public Long getStexauser() {
        return stexauser;
    }
    @Column(name = "STEXATIME")
    public Date getStexatime() {
        return stexatime;
    }
    @Column(name = "STEXAOPT")
    public String getStexaopt() {
        return stexaopt;
    }
    @Column(name = "CVLEXAUSER")
    public Long getCvlexauser() {
        return cvlexauser;
    }
    @Column(name = "CVLEXATIME")
    public Date getCvlexatime() {
        return cvlexatime;
    }
    @Column(name = "CVLEXAOPT")
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    @Column(name = "PRDTVER")
    public String getPrdtver() {
        return prdtver;
    }
    @Column(name = "FEEVER")
    public String getFeever() {
        return feever;
    }
    @Column(name = "SPILTVER")
    public String getSpiltver() {
        return spiltver;
    }
    @Column(name = "RISKVER")
    public String getRiskver() {
        return riskver;
    }
    @Column(name = "ROUTVER")
    public String getRoutver() {
        return routver;
    }
    @Column(name = "PARENT")
    public String getParent() {
        return parent;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setSetlcycle(Long setlcycle) {
        this.setlcycle = setlcycle;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    public void setBanknode(String banknode) {
        this.banknode = banknode;
    }
    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }
    public void setAccname(String accname) {
        this.accname = accname;
    }
    public void setCharge(Money charge) {
        this.charge = charge;
    }
    public void setDeposit(Money deposit) {
        this.deposit = deposit;
    }
    public void setAgreemtStart(Date agreemtStart) {
        this.agreemtStart = agreemtStart;
    }
    public void setAgreemtEnd(Date agreemtEnd) {
        this.agreemtEnd = agreemtEnd;
    }
    public void setMerchStatus(MerchStatusType merchStatus) {
        this.merchStatus = merchStatus;
    }
    public void setmInuser(Long mInuser) {
        this.mInuser = mInuser;
    }
    public void setmIntime(Date mIntime) {
        this.mIntime = mIntime;
    }
    public void setStexauser(Long stexauser) {
        this.stexauser = stexauser;
    }
    public void setStexatime(Date stexatime) {
        this.stexatime = stexatime;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    public void setCvlexauser(Long cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    public void setCvlexatime(Date cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
    }
    public void setPrdtver(String prdtver) {
        this.prdtver = prdtver;
    }
    public void setFeever(String feever) {
        this.feever = feever;
    }
    public void setSpiltver(String spiltver) {
        this.spiltver = spiltver;
    }
    public void setRiskver(String riskver) {
        this.riskver = riskver;
    }
    public void setRoutver(String routver) {
        this.routver = routver;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name = "SETLTYPE")
    public String getSetltype() {
        return setltype;
    }
    public void setSetltype(String setltype) {
        this.setltype = setltype;
    }

}
