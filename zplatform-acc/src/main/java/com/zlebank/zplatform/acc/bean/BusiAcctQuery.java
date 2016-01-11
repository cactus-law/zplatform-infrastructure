/* 
 * MemberQuery.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.bean;

import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version 会员查询结果
 * @date 2015年9月7日 下午7:58:59
 * @since 
 */
public class BusiAcctQuery  implements Bean{
    private AcctType AcctType;
    private Money balance;
    private Money fronzenBalance;
    private Money totalBalance;
    private String busiAcctName;
    private String busiAcctCode;
    private AcctStatusType status;
    private String memberID;
    /**科目号**/
    private String acctCode;
   /**会计账户Id**/
    private String acctId;
    
    private Usage usage;
    
    
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public String getAcctId() {
        return acctId;
    }
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public AcctStatusType getStatus() {
        return status;
    }
    public void setStatus(AcctStatusType status) {
        this.status = status;
    }
    public AcctType getAcctType() {
        return AcctType;
    }
    public void setAcctType(AcctType acctType) {
        AcctType = acctType;
    }
    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }
    public Money getFronzenBalance() {
        return fronzenBalance;
    }
    public void setFronzenBalance(Money fronzenBalance) {
        this.fronzenBalance = fronzenBalance;
    }
    public Money getTotalBalance() {
        return totalBalance;
    }
    public void setTotalBalance(Money totalBalance) {
        this.totalBalance = totalBalance;
    }
    public String getBusiAcctName() {
        return busiAcctName;
    }
    public void setBusiAcctName(String busiAcctName) {
        this.busiAcctName = busiAcctName;
    }
 
    public String getBusiAcctCode() {
        return busiAcctCode;
    }
    public void setBusiAcctCode(String busiAcctCode) {
        this.busiAcctCode = busiAcctCode;
    }
    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
  
    
}
