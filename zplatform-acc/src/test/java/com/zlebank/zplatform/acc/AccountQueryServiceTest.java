/* 
 * AccountQueryDao.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.service.AccountQueryService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月7日 下午12:09:38
 * @since 
 */
public class AccountQueryServiceTest {

    static ApplicationContext context = null;
    static AccountQueryService queryservice = null;
    
    static {
        context = ApplicationContextUtil.get();
        queryservice =  (AccountQueryService) context.getBean("accountQueryServiceImpl");
    }
     
    public void testGetAllAccountByMId() {
       List<BusiAcct> busi=queryservice.getBusiACCByMid("10000000102l");
          
       assertEquals(2, busi.size());
    }

    
    public void testGetAccountByID() {
        BusiAcctQuery query=   queryservice.getMemberQueryByID("62213211010110000000102");
        assertEquals("20010101010110000000102", query.getAcctCode());
    }
    
     
    public void testGetAccEntryById() {
     Account acc=  queryservice.getAccountByID(283);
       assertEquals("20010101010110000000102", acc.getAcctCode());
    }
    
     
    public void tests() {
       List<Account> account= queryservice.getAllAccountByMId("10000000102l");
       assertEquals(2, account.size());
    }
     
    public void getAllMemberBybCode() {
        BusiAcctQuery mq=    queryservice.getBusiQueryBybCode("62213211010110000000102");
        assertEquals("62213211010110000000102", mq.getBusiAcctCode());
    }
    
}
