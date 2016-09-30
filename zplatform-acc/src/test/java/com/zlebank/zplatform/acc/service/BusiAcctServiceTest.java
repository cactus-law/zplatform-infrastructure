/* 
 * BusiAcctServiceTest.java  
 * 
 * version 1.0
 *
 * 2015年9月1日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.mock.MemberMock;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月1日 上午8:58:08
 * @since
 */
public class BusiAcctServiceTest {
    private ApplicationContext context;
    BusiAcctService busiAcctService;
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("AccountContextTest.xml");
        busiAcctService = (BusiAcctService) context
                .getBean("busiAcctServiceImpl");
    }
    @Test
    public void testMerchantOpenBusiAcct() {
        BusinessActor member = new BusinessActor() {
            
            @Override
            public BusinessActorType getBusinessActorType() {
                // TODO Auto-generated method stub
                return BusinessActorType.INDIVIDUAL;
            }
            
            @Override
            public String getBusinessActorId() {
                // TODO Auto-generated method stub
                return "100000000000965";
            }
        };
        long userId = 45;
        BusiAcct busiAcct = new BusiAcct();
        busiAcct.setBusiAcctName("0965授信账户");
        busiAcct.setUsage(Usage.GRANTCREDIT);
        String acctCode = null;
        try {
            acctCode = busiAcctService.openBusiAcct(member, busiAcct, userId);
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
            Assert.fail();
        }
        @SuppressWarnings("unused")
        BusiAcct busiAcctResult;
        try {
            busiAcctResult = busiAcctService.getByBusiAcctCode(acctCode);
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    
    public void testGetByCode() {
        String busiActCode = "2001021010212345678901";
        BusiAcct busiAcct = null;
        try {
            busiAcct = busiAcctService.getByBusiAcctCode(busiActCode);
        } catch (AbstractBusiAcctException e) { 
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertEquals(busiActCode, busiAcct.getBusiAcctCode());
    }
    
    public void testGetAccount() {
        Usage usage = Usage.BASICPAY;
        String memberId = "12345678901";
        String accountCode = null;
        try {
            accountCode = busiAcctService.getAccount(usage, memberId);
        } catch (AbstractBusiAcctException e) { 
            e.printStackTrace();
            Assert.fail();
        }
        Assert.assertEquals("2001020212345678901", accountCode);
    }
}
