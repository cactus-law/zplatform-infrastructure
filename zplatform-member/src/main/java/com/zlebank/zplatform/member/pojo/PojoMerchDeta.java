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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.member.bean.enums.CardType;
import com.zlebank.zplatform.member.bean.enums.IndustryType;
import com.zlebank.zplatform.member.bean.enums.MerchStatusType;
import com.zlebank.zplatform.member.bean.enums.MerchType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月7日 下午4:37:54 
 * @since 
 */
@Entity
@PrimaryKeyJoinColumn(name="MERCHID") 
@Table(name="T_MERCH_DETA")
public class PojoMerchDeta extends PojoMember{
  
    /**会员号**/
    private String dateMemberid;
    /**商户名称**/
    private String merchname;
    /**商户简称**/
    private String alias;
    /**商户英文名称**/
    private String engname;
    /**商户所属机构**/
    private Long merchinsti;
    /**商户所属省**/
    private Long province;
    /**商户所属市**/
    private Long city;
    /**商户所属县**/
    private Long street;
    /**商户地址**/
    private String address;
    /**税务登记证号**/
    private String taxno;
    /**营业执照号**/
    private String licenceno;
    /**组织机构代码证**/
    private String orgcode;
    /**商户网址**/
    private String website;
    /**商户类型:0代理商1普通商户**/
    private MerchType merchtype;
    /**所属行业:0餐饮1酒店连锁2其他3大型商场连锁4娱乐5金融6物流**/
    private IndustryType trade;
    /**法人**/
    private String corporation;
    /**法人身份证号码**/
    private String corpno;
    /**联系人**/
    private String contact;
    /**联系人电话**/
    private String contphone;
    /**联系人职位**/
    private String conttitle;
    /**联系人邮箱**/
    private String contemail;
    /**客户来源**/
    private String custfrom;
    /**客户经理**/
    private String custmgr;
    /**客户经理部门**/
    private String custmgrdept;
    /**签约人**/
    private String signatory;
    /**签约人电话**/
    private String signphone;
    /**清算周期**/
    private Long setlcycle;
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
    /**开户行所属省**/
    private Long bnkProvince;
    /**开户行所属市**/
    private Long bnkCity;
    /**开户行所属县**/
    private Long bnkStreet;
    /**邮编**/
    private String postcode;
    /**邮箱**/
    private String email;
    /**身份证文件目录**/
    private String corpfile;
    /**税务登记证文件目录**/
    private String taxfile;
    /**营业执照文件目录**/
    private String licencefile;
    /**组织机构文件目录**/
    private String orgcodefile;
    /**状态**/
    private MerchStatusType status;
    /**初次业务时间**/
    private Date firsttime;
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
    /**商户秘钥**/
    private String secretKey;
    /**行政地区代码（由商户所属县得到）**/
    private String zonecode;
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
    /**收银台版本**/
    private String cashver;
    /**上级商户**/
    private String parent;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    /**证件类型**/
    private CardType cardtype;
    /**联系人地址**/
    private String contaddress;
    /**联系人邮编**/
    private String contpost;
 
    @Column(name = "MEMBERID")
    public String getDateMemberid() {
        return dateMemberid;
    }
    @Column(name = "MERCHNAME")
    public String getMerchname() {
        return merchname;
    }
    @Column(name = "ALIAS")
    public String getAlias() {
        return alias;
    }
    @Column(name = "ENGNAME")
    public String getEngname() {
        return engname;
    }   
    @Column(name = "MERCHINSTI")
    public Long getMerchinsti() {
        return merchinsti;
    }
    @Column(name = "PROVINCE")
    public Long getProvince() {
        return province;
    }
    @Column(name = "CITY")
    public Long getCity() {
        return city;
    }
    @Column(name = "STREET")
    public Long getStreet() {
        return street;
    }
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }
    @Column(name = "TAXNO")
    public String getTaxno() {
        return taxno;
    }
    @Column(name = "LICENCENO")
    public String getLicenceno() {
        return licenceno;
    }
    @Column(name = "ORGCODE")
    public String getOrgcode() {
        return orgcode;
    }
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MerchSqlType")
    @Column(name = "MERCHTYPE")
    public MerchType getMerchtype() {
        return merchtype;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.IndustrySqlType")
    @Column(name = "TRADE")
    public IndustryType getTrade() {
        return trade;
    }
    @Column(name = "CORPORATION")
    public String getCorporation() {
        return corporation;
    }
    @Column(name = "CORPNO")
    public String getCorpno() {
        return corpno;
    }
    @Column(name = "CONTACT")
    public String getContact() {
        return contact;
    }
    @Column(name = "CONTPHONE")
    public String getContphone() {
        return contphone;
    }
    @Column(name = "CONTTITLE")
    public String getConttitle() {
        return conttitle;
    }
    @Column(name = "CONTEMAIL")
    public String getContemail() {
        return contemail;
    }
    @Column(name = "CUSTFROM")
    public String getCustfrom() {
        return custfrom;
    }
    @Column(name = "CUSTMGR")
    public String getCustmgr() {
        return custmgr;
    }
    @Column(name = "CUSTMGRDEPT")
    public String getCustmgrdept() {
        return custmgrdept;
    }
    @Column(name = "SIGNATORY")
    public String getSignatory() {
        return signatory;
    }
    @Column(name = "SIGNPHONE")
    public String getSignphone() {
        return signphone;
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
    @Column(name = "BNK_PROVINCE")
    public Long getBnkProvince() {
        return bnkProvince;
    }
    @Column(name = "BNK_CITY")
    public Long getBnkCity() {
        return bnkCity;
    }
    @Column(name = "BNK_STREET")
    public Long getBnkStreet() {
        return bnkStreet;
    }
    @Column(name = "POSTCODE")
    public String getPostcode() {
        return postcode;
    }
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    @Column(name = "CORPFILE")
    public String getCorpfile() {
        return corpfile;
    }
    @Column(name = "TAXFILE")
    public String getTaxfile() {
        return taxfile;
    }
    @Column(name = "LICENCEFILE")
    public String getLicencefile() {
        return licencefile;
    }
    @Column(name = "ORGCODEFILE")
    public String getOrgcodefile() {
        return orgcodefile;
    }
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.MerchStatusSqlType")
    @Column(name = "STATUS")
    public MerchStatusType getStatus() {
        return status;
    }
    @Column(name = "FIRSTTIME")
    public Date getFirsttime() {
        return firsttime;
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
    @Column(name = "SECRET_KEY")
    public String getSecretKey() {
        return secretKey;
    }
    @Column(name = "ZONECODE")
    public String getZonecode() {
        return zonecode;
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
    @Column(name = "CASHVER")
    public String getCashver() {
        return cashver;
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
    public void setDateMemberid(String dateMemberid) {
        this.dateMemberid = dateMemberid;
    }
    public void setMerchname(String merchname) {
        this.merchname = merchname;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public void setEngname(String engname) {
        this.engname = engname;
    }
    public void setMerchinsti(Long merchinsti) {
        this.merchinsti = merchinsti;
    }
    public void setProvince(Long province) {
        this.province = province;
    }
    public void setCity(Long city) {
        this.city = city;
    }
    public void setStreet(Long street) {
        this.street = street;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }
    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno;
    }
    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setMerchtype(MerchType merchtype) {
        this.merchtype = merchtype;
    }
    public void setTrade(IndustryType trade) {
        this.trade = trade;
    }
    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }
    public void setCorpno(String corpno) {
        this.corpno = corpno;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setContphone(String contphone) {
        this.contphone = contphone;
    }
    public void setConttitle(String conttitle) {
        this.conttitle = conttitle;
    }
    public void setContemail(String contemail) {
        this.contemail = contemail;
    }
    public void setCustfrom(String custfrom) {
        this.custfrom = custfrom;
    }
    public void setCustmgr(String custmgr) {
        this.custmgr = custmgr;
    }
    public void setCustmgrdept(String custmgrdept) {
        this.custmgrdept = custmgrdept;
    }
    public void setSignatory(String signatory) {
        this.signatory = signatory;
    }
    public void setSignphone(String signphone) {
        this.signphone = signphone;
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
    public void setBnkProvince(Long bnkProvince) {
        this.bnkProvince = bnkProvince;
    }
    public void setBnkCity(Long bnkCity) {
        this.bnkCity = bnkCity;
    }
    public void setBnkStreet(Long bnkStreet) {
        this.bnkStreet = bnkStreet;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCorpfile(String corpfile) {
        this.corpfile = corpfile;
    }
    public void setTaxfile(String taxfile) {
        this.taxfile = taxfile;
    }
    public void setLicencefile(String licencefile) {
        this.licencefile = licencefile;
    }
    public void setOrgcodefile(String orgcodefile) {
        this.orgcodefile = orgcodefile;
    }
    public void setStatus(MerchStatusType status) {
        this.status = status;
    }
    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
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
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public void setZonecode(String zonecode) {
        this.zonecode = zonecode;
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
    public void setCashver(String cashver) {
        this.cashver = cashver;
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
    @Column(name = "CARDTYPE")
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.CardSqlType")
    public CardType getCardtype() {
        return cardtype;
    }
    public void setCardtype(CardType cardtype) {
        this.cardtype = cardtype;
    }
    @Column(name = "CONTADDRESS")
    public String getContaddress() {
        return contaddress;
    }
    public void setContaddress(String contaddress) {
        this.contaddress = contaddress;
    }
    @Column(name = "CONTPOST")
    public String getContpost() {
        return contpost;
    }
    public void setContpost(String contpost) {
        this.contpost = contpost;
    }
}
