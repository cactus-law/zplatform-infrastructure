/* 
 * AccRules.java  
 * 
 * version TODO
 *
 * 2015年9月1日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;


/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月1日 上午9:20:31
 * @since 
 */
public class AccEntryRules {
    
    public Object id;
    // 状态
    public String status;
    // 交易类型
    private String txntype;
    // 科目标记 (个人资产、个人产品、商户资产、商户产品、父级商户账户)
    private String acctCode;
    // 余额方向
    private String crdr;
    // 产品代码
    private String prdtcode;
    // 渠道代码
    private String chnlcode;
    // 是否同步记账
    private String isSync;
    // 分录金额计算 ：A本金 ：B佣金 ：C手续费 计算符号+-*/
    private String ordform;
    // 分录顺序
    private String orders;
    // 是否显示
    private String isShow;
    public Object getId() {
        return id;
    }
    public void setId(Object id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTxntype() {
        return txntype;
    }
    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getCrdr() {
        return crdr;
    }
    public void setCrdr(String crdr) {
        this.crdr = crdr;
    }
    public String getPrdtcode() {
        return prdtcode;
    }
    public void setPrdtcode(String prdtcode) {
        this.prdtcode = prdtcode;
    }
    public String getChnlcode() {
        return chnlcode;
    }
    public void setChnlcode(String chnlcode) {
        this.chnlcode = chnlcode;
    }
    public String getIsSync() {
        return isSync;
    }
    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }
    public String getOrdform() {
        return ordform;
    }
    public void setOrdform(String ordform) {
        this.ordform = ordform;
    }
    public String getOrders() {
        return orders;
    }
    public void setOrders(String orders) {
        this.orders = orders;
    }
    public String getIsShow() {
        return isShow;
    }
    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    
}
