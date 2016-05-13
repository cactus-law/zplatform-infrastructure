package com.zlebank.zlpatform.acc2.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.zlebank.zlpatform.acc2.util.ApplicationContextAbled;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.pojo.PojoAccount;

public class AccountDAOTest extends ApplicationContextAbled{
    private AccountDAO accountDAO;
    
    @Before
    public void init() {
        accountDAO = (AccountDAO) context.getBean(AccountDAO.class);
    }  
    
    @Test
    @Ignore
    public void test(){
        PojoAccount pojoAccount = accountDAO.getByAcctCode("224801");
        Assert.assertEquals("224801", pojoAccount.getAcctCode());
    }
}
