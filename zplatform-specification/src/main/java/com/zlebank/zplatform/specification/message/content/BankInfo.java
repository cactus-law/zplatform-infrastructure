/* 
 * BankInfo.java  
 * 
 * version v1.0
 *
 * 2015年9月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message.content;

/**
 * 银行行号信息
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月21日 下午3:49:19
 * @since 
 */
public class BankInfo {
    /**开户行号**/
    private String bankNode;
    /**清算行号**/
    private String acctName;
    /**开户行名称**/
    private String bankName;
    /**开户行地址**/
    private String bankAdress;
    public String getBankNode() {
        return bankNode;
    }
    public void setBankNode(String bankNode) {
        this.bankNode = bankNode;
    }
    public String getAcctName() {
        return acctName;
    }
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankAdress() {
        return bankAdress;
    }
    public void setBankAdress(String bankAdress) {
        this.bankAdress = bankAdress;
    }
}
