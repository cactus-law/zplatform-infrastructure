/* 
 * AccountServiceTest.java  
 * 
 * version 1.0
 *
 * 2015年9月6日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.impl.GetDACServiceImpl;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月6日 下午1:22:35
 * @since
 */
public class AccountServiceTest {
    private ApplicationContext context;
    AccountService accountService;
    private GetDACService dacUtil;
     
    public void init() {
        context = new ClassPathXmlApplicationContext("AccountContextTest.xml");
        accountService = (AccountService) context.getBean("accountServiceImpl");
        dacUtil = (GetDACServiceImpl) context.getBean("getDACService");
    }  
    public void testAddAcct() {

        long userId = 45;
        long parentSubjectId = 236;
        String acctCode = "2001010101";
        String acctName = "测试添加账户"; 

        try {
            acctCode = accountService.addAcct(parentSubjectId, acctCode, acctName, userId);
        } catch (AbstractAccException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
    
    public void testgenDac() { 
        String dac = dacUtil.generteDAC("20010101", Money.valueOf(180000), Money.ZERO, Money.valueOf(180000));
        System.out.println(dacUtil);
       // System.out.println(dacUtil.getDacKey());
        System.out.println(dac);
         
    }
}