/* 
 * MemberBalanceBean.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.math.BigDecimal;

/**
 * 会员余额
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 上午11:58:46
 * @since 
 */
public class MemberBalanceBean {
    /** 余额 **/
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
}
