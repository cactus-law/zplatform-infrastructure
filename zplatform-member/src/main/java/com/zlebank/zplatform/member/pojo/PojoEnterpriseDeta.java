/* 
 * PojoEnterpriseDeta.java  
 * 
 * version TODO
 *
 * 2016年2月24日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.member.bean.enums.CardType;

/**
 * 企业会员
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年2月24日 下午3:39:26
 * @since 
 */
@Entity
@PrimaryKeyJoinColumn(name="ENTERPRISE_ID") 
@Table(name="T_ENTERPRISE_DETA")
public class PojoEnterpriseDeta extends PojoMember{
    /**会员号**/
    private String enterpriseMemberId;
    /**合作机构id**/
    private Long coopInstiId;
    /**所属行业**/
    private String mcc;
    /**所属行业子类别**/
    private String mcclist;
    /**企业所属机构**/
    private Long merchinsti;
    /**企业所属省**/
    private Long province;
    /**企业所属市**/
    private Long city;
    /**企业所属县**/
    private Long street;
    /**企业行政地区代码（由商户所属县得到）**/
    private String zonecode;
    /**企业邮编**/
    private String postcode;
    /**企业地址**/
    private String address;
    /**企业邮箱**/
    private String email;
    /**企业网址**/
    private String website;
    /**企业证件类型**/
    private CardType cardtype;
    /**税务登记证号**/
    private String taxno;
    /**营业执照号**/
    private String licenceno;
    /**组织机构代码证**/
    private String orgcode;
    /**法人**/
    private String corporation;
    /**法人证件号码**/
    private String corpno;
    /**法人身份证正面路径**/
    private String corpfile;
    /**法人身份证背面路径**/
    private String corpfileopp;
    /**税务登记证文件路径**/
    private String taxfile;
    /**营业执照文件路径**/
    private String licencefile;
    /**组织机构文件路径**/
    private String orgcodefile;
    /**联系人**/
    private String contact;
    /**联系人电话**/
    private String contphone;
    /**联系人职位**/
    private String conttitle;
    /**联系人邮箱**/
    private String contemail;
    /**联系人地址**/
    private String contaddress;
    /**联系人邮编**/
    private String contpost;
    /**客户来源**/
    private String custfrom;
    /**客户经理**/
    private String custmgr;
    /**客户经理部门**/
    private String custmgrdept;
    /**是否授权人办理1-是0-否**/
    private Long isdelegation;
    /**委托人**/
    private String signatory;
    /**委托人身份证**/
    private String signcertno;
    /**委托人证件照正面路径**/
    private String signcertfile;
    /**委托人证件照背面路径**/
    private String signcertfileopp;
    /**状态**/
    private String enterpriseStatus;
    /**初次业务时间**/
    private Date firsttime;
    /**写入人**/
    private Long inuser;
    /**写入时间**/
    private Date intime;
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

    @Column(name = "MEMBER_ID")
    public String getEnterpriseMemberId() {
        return enterpriseMemberId;
    }
    public void setEnterpriseMemberId(String enterpriseMemberId) {
        this.enterpriseMemberId = enterpriseMemberId;
    }

    @Column(name = "COOP_INSTI_ID")
    public Long getCoopInstiId() {
        return coopInstiId;
    }
    public void setCoopInstiId(Long coopInstiId) {
        this.coopInstiId = coopInstiId;
    }
    @Column(name = "MCC")
    public String getMcc() {
        return mcc;
    }
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
    @Column(name = "MCCLIST")
    public String getMcclist() {
        return mcclist;
    }
    public void setMcclist(String mcclist) {
        this.mcclist = mcclist;
    }
    @Column(name = "MERCHINSTI")
    public Long getMerchinsti() {
        return merchinsti;
    }
    public void setMerchinsti(Long merchinsti) {
        this.merchinsti = merchinsti;
    }
    @Column(name = "PROVINCE")
    public Long getProvince() {
        return province;
    }
    public void setProvince(Long province) {
        this.province = province;
    }
    @Column(name = "CITY")
    public Long getCity() {
        return city;
    }
    public void setCity(Long city) {
        this.city = city;
    }
    @Column(name = "STREET")
    public Long getStreet() {
        return street;
    }
    public void setStreet(Long street) {
        this.street = street;
    }
    @Column(name = "ZONECODE")
    public String getZonecode() {
        return zonecode;
    }
    public void setZonecode(String zonecode) {
        this.zonecode = zonecode;
    }
    @Column(name = "POSTCODE")
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "WEBSITE")
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    @Column(name = "CARDTYPE")
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.CardSqlType")
    public CardType getCardtype() {
        return cardtype;
    }
    public void setCardtype(CardType cardtype) {
        this.cardtype = cardtype;
    }
    @Column(name = "TAXNO")
    public String getTaxno() {
        return taxno;
    }
    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }
    @Column(name = "LICENCENO")
    public String getLicenceno() {
        return licenceno;
    }
    public void setLicenceno(String licenceno) {
        this.licenceno = licenceno;
    }
    @Column(name = "ORGCODE")
    public String getOrgcode() {
        return orgcode;
    }
    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
    @Column(name = "CORPORATION")
    public String getCorporation() {
        return corporation;
    }
    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }
    @Column(name = "CORPNO")
    public String getCorpno() {
        return corpno;
    }
    public void setCorpno(String corpno) {
        this.corpno = corpno;
    }
    @Column(name = "CORPFILE")
    public String getCorpfile() {
        return corpfile;
    }
    public void setCorpfile(String corpfile) {
        this.corpfile = corpfile;
    }
    @Column(name = "CORPFILEOPP")
    public String getCorpfileopp() {
        return corpfileopp;
    }
    public void setCorpfileopp(String corpfileopp) {
        this.corpfileopp = corpfileopp;
    }
    @Column(name = "TAXFILE")
    public String getTaxfile() {
        return taxfile;
    }
    public void setTaxfile(String taxfile) {
        this.taxfile = taxfile;
    }
    @Column(name = "LICENCEFILE")
    public String getLicencefile() {
        return licencefile;
    }
    public void setLicencefile(String licencefile) {
        this.licencefile = licencefile;
    }
    @Column(name = "ORGCODEFILE")
    public String getOrgcodefile() {
        return orgcodefile;
    }
    public void setOrgcodefile(String orgcodefile) {
        this.orgcodefile = orgcodefile;
    }
    @Column(name = "CONTACT")
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    @Column(name = "CONTPHONE")
    public String getContphone() {
        return contphone;
    }
    public void setContphone(String contphone) {
        this.contphone = contphone;
    }
    @Column(name = "CONTTITLE")
    public String getConttitle() {
        return conttitle;
    }
    public void setConttitle(String conttitle) {
        this.conttitle = conttitle;
    }
    @Column(name = "CONTEMAIL")
    public String getContemail() {
        return contemail;
    }
    public void setContemail(String contemail) {
        this.contemail = contemail;
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
    @Column(name = "CUSTFROM")
    public String getCustfrom() {
        return custfrom;
    }
    public void setCustfrom(String custfrom) {
        this.custfrom = custfrom;
    }
    @Column(name = "CUSTMGR")
    public String getCustmgr() {
        return custmgr;
    }
    public void setCustmgr(String custmgr) {
        this.custmgr = custmgr;
    }
    @Column(name = "CUSTMGRDEPT")
    public String getCustmgrdept() {
        return custmgrdept;
    }
    public void setCustmgrdept(String custmgrdept) {
        this.custmgrdept = custmgrdept;
    }
    @Column(name = "ISDELEGATION")
    public Long getIsdelegation() {
        return isdelegation;
    }
    public void setIsdelegation(Long isdelegation) {
        this.isdelegation = isdelegation;
    }
    @Column(name = "SIGNATORY")
    public String getSignatory() {
        return signatory;
    }
    public void setSignatory(String signatory) {
        this.signatory = signatory;
    }
    @Column(name = "SIGNCERTNO")
    public String getSigncertno() {
        return signcertno;
    }
    public void setSigncertno(String signcertno) {
        this.signcertno = signcertno;
    }
    @Column(name = "SIGNCERTFILE")
    public String getSigncertfile() {
        return signcertfile;
    }
    public void setSigncertfile(String signcertfile) {
        this.signcertfile = signcertfile;
    }
    @Column(name = "SIGNCERTFILEOPP")
    public String getSigncertfileopp() {
        return signcertfileopp;
    }
    public void setSigncertfileopp(String signcertfileopp) {
        this.signcertfileopp = signcertfileopp;
    }
    @Column(name = "STATUS")
    public String getEnterpriseStatus() {
        return enterpriseStatus;
    }
    public void setEnterpriseStatus(String enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }
    @Column(name = "FIRSTTIME")
    public Date getFirsttime() {
        return firsttime;
    }
    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "STEXAUSER")
    public Long getStexauser() {
        return stexauser;
    }
    public void setStexauser(Long stexauser) {
        this.stexauser = stexauser;
    }
    @Column(name = "STEXATIME")
    public Date getStexatime() {
        return stexatime;
    }
    public void setStexatime(Date stexatime) {
        this.stexatime = stexatime;
    }
    @Column(name = "STEXAOPT")
    public String getStexaopt() {
        return stexaopt;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    @Column(name = "CVLEXAUSER")
    public Long getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(Long cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    @Column(name = "CVLEXATIME")
    public Date getCvlexatime() {
        return cvlexatime;
    }
    public void setCvlexatime(Date cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    @Column(name = "CVLEXAOPT")
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
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
 /*   
  * 设值时Copy用
    pojo.setEnterpriseId(bean.getEnterpriseId());// 主键
    pojo.setMemberId(bean.getMemberId());// 会员号
    pojo.setCoopInstiId(bean.getCoopInstiId());// 合作机构id
    pojo.setMcc(bean.getMcc());// 所属行业
    pojo.setMcclist(bean.getMcclist());// 所属行业子类别
    pojo.setMerchinsti(bean.getMerchinsti());// 企业所属机构
    pojo.setProvince(bean.getProvince());// 企业所属省
    pojo.setCity(bean.getCity());// 企业所属市
    pojo.setStreet(bean.getStreet());// 企业所属县
    pojo.setZonecode(bean.getZonecode());// 企业行政地区代码（由商户所属县得到）
    pojo.setPostcode(bean.getPostcode());// 企业邮编
    pojo.setAddress(bean.getAddress());// 企业地址
    pojo.setEmail(bean.getEmail());// 企业邮箱
    pojo.setWebsite(bean.getWebsite());// 企业网址
    pojo.setCardtype(bean.getCardtype());// 企业证件类型
    pojo.setTaxno(bean.getTaxno());// 税务登记证号
    pojo.setLicenceno(bean.getLicenceno());// 营业执照号
    pojo.setOrgcode(bean.getOrgcode());// 组织机构代码证
    pojo.setCorporation(bean.getCorporation());// 法人
    pojo.setCorpno(bean.getCorpno());// 法人证件号码
    pojo.setCorpfile(bean.getCorpfile());// 法人身份证正面路径
    pojo.setCorpfileopp(bean.getCorpfileopp());// 法人身份证背面路径
    pojo.setTaxfile(bean.getTaxfile());// 税务登记证文件路径
    pojo.setLicencefile(bean.getLicencefile());// 营业执照文件路径
    pojo.setOrgcodefile(bean.getOrgcodefile());// 组织机构文件路径
    pojo.setContact(bean.getContact());// 联系人
    pojo.setContphone(bean.getContphone());// 联系人电话
    pojo.setConttitle(bean.getConttitle());// 联系人职位
    pojo.setContemail(bean.getContemail());// 联系人邮箱
    pojo.setContaddress(bean.getContaddress());// 联系人地址
    pojo.setContpost(bean.getContpost());// 联系人邮编
    pojo.setCustfrom(bean.getCustfrom());// 客户来源
    pojo.setCustmgr(bean.getCustmgr());// 客户经理
    pojo.setCustmgrdept(bean.getCustmgrdept());// 客户经理部门
    pojo.setIsdelegation(bean.getIsdelegation());// 是否授权人办理1-是0-否
    pojo.setSignatory(bean.getSignatory());// 委托人
    pojo.setSigncertno(bean.getSigncertno());// 委托人身份证
    pojo.setSigncertfile(bean.getSigncertfile());// 委托人证件照正面路径
    pojo.setSigncertfileopp(bean.getSigncertfileopp());// 委托人证件照背面路径
    pojo.setStatus(bean.getStatus());// 状态
    pojo.setFirsttime(bean.getFirsttime());// 初次业务时间
    pojo.setInuser(bean.getInuser());// 写入人
    pojo.setIntime(bean.getIntime());// 写入时间
    pojo.setStexauser(bean.getStexauser());// 初审人
    pojo.setStexatime(bean.getStexatime());// 初审时间
    pojo.setStexaopt(bean.getStexaopt());// 初审意见
    pojo.setCvlexauser(bean.getCvlexauser());// 复核人
    pojo.setCvlexatime(bean.getCvlexatime());// 复核时间
    pojo.setCvlexaopt(bean.getCvlexaopt());// 复核意见
    pojo.setNotes(bean.getNotes());// 备注
    pojo.setRemarks(bean.getRemarks());// 备注
    */


}
