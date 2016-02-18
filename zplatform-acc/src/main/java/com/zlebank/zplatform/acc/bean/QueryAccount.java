/* 
 * QueryAccount.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

/**
 *账户查询bean
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午7:46:56
 * @since 
 */
public class QueryAccount {
    //会员号
    public String memberId;
    //账户状态
    public  String accStatus;
    //审核状态
    public   String chectStatus;
    //业务账户号
    public String busiCode;
    //会计账户号
    public String accCode;
    //作用
    public String usage;
    
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getAccStatus() {
        return accStatus;
    }
    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }
    public String getChectStatus() {
        return chectStatus;
    }
    public void setChectStatus(String chectStatus) {
        this.chectStatus = chectStatus;
    }
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    public String getAccCode() {
        return accCode;
    }
    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }
    
    
    
    }
