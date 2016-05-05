/* 
 * BusinessAcct.java  
 * 
 * version 1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import com.zlebank.zplatform.acc.bean.enums.Usage;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 上午11:41:17
 * @since
 */
public class BusiAcct {
    private Usage usage;
    private String busiAcctCode;
    private String busiAcctName;
    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    public String getBusiAcctCode() {
        return busiAcctCode;
    }
    public void setBusiAcctCode(String busiAcctCode) {
        this.busiAcctCode = busiAcctCode;
    }
    public String getBusiAcctName() {
        return busiAcctName;
    }
    public void setBusiAcctName(String busiAcctName) {
        this.busiAcctName = busiAcctName;
    }
}
