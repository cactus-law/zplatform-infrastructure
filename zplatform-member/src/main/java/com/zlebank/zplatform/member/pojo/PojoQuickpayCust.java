package com.zlebank.zplatform.member.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "T_QUICKPAY_CUST")
public class PojoQuickpayCust implements java.io.Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3677153108408492149L;
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

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "QUICKPAY_CUST_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "BINDCARDID")
    public String getBindcardid() {
        return bindcardid;
    }
    public void setBindcardid(String bindcardid) {
        this.bindcardid = bindcardid;
    }
    @Column(name = "INSTITUTION")
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    @Column(name = "CUSTOMERNO")
    public String getCustomerno() {
        return customerno;
    }
    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }
    @Column(name = "CARDNO")
    public String getCardno() {
        return cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    @Column(name = "CARDTYPE")
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    @Column(name = "ACCNAME")
    public String getAccname() {
        return accname;
    }
    public void setAccname(String accname) {
        this.accname = accname;
    }
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "IDTYPE")
    public String getIdtype() {
        return idtype;
    }
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }
    @Column(name = "IDNUM")
    public String getIdnum() {
        return idnum;
    }
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    @Column(name = "CVV2")
    public String getCvv2() {
        return cvv2;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    @Column(name = "VALIDTIME")
    public String getValidtime() {
        return validtime;
    }
    public void setValidtime(String validtime) {
        this.validtime = validtime;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "RELATEMEMBERNO")
    public String getRelatememberno() {
        return relatememberno;
    }
    public void setRelatememberno(String relatememberno) {
        this.relatememberno = relatememberno;
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
    @Column(name = "BANKCODE")
    public String getBankcode() {
        return bankcode;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    @Column(name = "BANKNAME")
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
	/**
	 * @return the devId
	 */
    @Column(name = "DEVID")
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