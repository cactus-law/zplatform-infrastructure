package com.zlebank.zlpatform.acc2.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.zlebank.zlpatform.acc2.util.ApplicationContextAbled;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;

public class BusiAcctDAOTest extends ApplicationContextAbled{
    
    private BusiAcctDAO busiAcctDAO;
    
    @Before
    public void init() {
        busiAcctDAO = (BusiAcctDAO) context.getBean(BusiAcctDAO.class);
    }  
    @Test
    @Ignore
    public void test(){
        String accCode = busiAcctDAO.getAccCodeByUsageAndBusiActorId(Usage.fromValue("108"), "200000000000622");
        Assert.assertEquals("224602200000000000622", accCode);
    }
}
