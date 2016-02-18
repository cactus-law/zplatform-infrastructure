/* 
 * SubjectAccountRule.java  
 * 
 * version TODO
 *
 * 2015年10月23日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月23日 上午9:43:37
 * @since 
 */
public class SubjectAccountRule {
    /**状态**/
    private String status;
    /**余额方向**/
    private String crdr;
    /**同步异步**/
    private String isSync;
    /**交易类型代码**/
    private String txnType;

    private String id;
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCrdr() {
        return crdr;
    }

    public void setCrdr(String crdr) {
        this.crdr = crdr;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }
    
    
    

}
