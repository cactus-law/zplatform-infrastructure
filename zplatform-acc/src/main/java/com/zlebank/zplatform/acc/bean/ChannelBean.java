package com.zlebank.zplatform.acc.bean;

public class ChannelBean {
    // 通道代码
    private String chnlcode;
    // 通道名称
    private String chnlname;
    // 机构号
    private String insticode;
    // 通道类型
    private String chnltype;
    // 通道子类型
    private String subtype;
    // 状态
    private String status;
    // 受理业务代码集
    private String appset;
    // 备注
    private String notes;
    // 备注
    private String remarks;
    // 通道成本科目
    private String acctCodeCost;
    // 通道备付金科目(银行存款科目)
    private String acctCodeDeposit;
    // 通道应收款科目
    private String acctCodeReceivable;
    // 用户ID
    private long userId;

    public String getChnlcode() {
        return chnlcode;
    }
    public void setChnlcode(String chnlcode) {
        this.chnlcode = chnlcode;
    }
    public String getChnlname() {
        return chnlname;
    }
    public void setChnlname(String chnlname) {
        this.chnlname = chnlname;
    }
    public String getInsticode() {
        return insticode;
    }
    public void setInsticode(String insticode) {
        this.insticode = insticode;
    }
    public String getChnltype() {
        return chnltype;
    }
    public void setChnltype(String chnltype) {
        this.chnltype = chnltype;
    }
    public String getSubtype() {
        return subtype;
    }
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAppset() {
        return appset;
    }
    public void setAppset(String appset) {
        this.appset = appset;
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
    public String getAcctCodeCost() {
        return acctCodeCost;
    }
    public void setAcctCodeCost(String acctCodeCost) {
        this.acctCodeCost = acctCodeCost;
    }
    public String getAcctCodeDeposit() {
        return acctCodeDeposit;
    }
    public void setAcctCodeDeposit(String acctCodeDeposit) {
        this.acctCodeDeposit = acctCodeDeposit;
    }
    public String getAcctCodeReceivable() {
        return acctCodeReceivable;
    }
    public void setAcctCodeReceivable(String acctCodeReceivable) {
        this.acctCodeReceivable = acctCodeReceivable;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
