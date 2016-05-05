/* 
 * QuickpayCustBean.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午7:02:27
 * @since 
 */
public class QuickpayCustBean implements Bean{
    /**标识**/
    private Long id;
    /**绑卡标示**/
    private String bindcardid;
    /**所属机构-支付机构代码**/
    private String institution;
    /**证联商户号**/
    private String customerno;
    /**银行卡号**/
    private String cardno;
    /**卡类型1-借记卡2-贷记卡**/
    private String cardtype;
    /**银行卡账户名称**/
    private String accname;
    /**手机号**/
    private String phone;
    /**"证件类型01：身份证；**/
    private String idtype;
    /**证件号码**/
    private String idnum;
    /**CVV2**/
    private String cvv2;
    /**卡有效期**/
    private String validtime;
    /**状态-00:已绑定，01:待绑定02:解绑**/
    private String status;
    /**关联证联会员号**/
    private String relatememberno;
    /****/
    private String notes;
    /****/
    private String remarks;
    /**银行代码**/
    private String bankcode;
    /**银行名称**/
    private String bankname;
    /**设备标示**/
    private String devId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBindcardid() {
        return bindcardid;
    }
    public void setBindcardid(String bindcardid) {
        this.bindcardid = bindcardid;
    }
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getCustomerno() {
        return customerno;
    }
    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }
    public String getCardno() {
        return cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    public String getAccname() {
        return accname;
    }
    public void setAccname(String accname) {
        this.accname = accname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getIdtype() {
        return idtype;
    }
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }
    public String getIdnum() {
        return idnum;
    }
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    public String getCvv2() {
        return cvv2;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    public String getValidtime() {
        return validtime;
    }
    public void setValidtime(String validtime) {
        this.validtime = validtime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRelatememberno() {
        return relatememberno;
    }
    public void setRelatememberno(String relatememberno) {
        this.relatememberno = relatememberno;
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
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getBankcode() {
        return bankcode;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
	/**
	 * @return the devId
	 */
	public String getDevId() {
		return devId;
	}
	/**
	 * @param devId the devId to set
	 */
	public void setDevId(String devId) {
		this.devId = devId;
	}
    
}
