package com.zlebank.member.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.member.test.ApplicationContextUtil;
import com.zlebank.zplatform.member.dao.ParaDicDAO;

class ParaDicTest {
    
    @Test
    public void test(){
        ApplicationContext context = ApplicationContextUtil.get();
        ParaDicDAO paraDicDAO = (ParaDicDAO)context.getBean("paraDicDAOImpl");
        String nextVal = paraDicDAO.getSeqNextval("SEQ_T_MERCH_DETA_MEMBERID");
        Assert.assertEquals(nextVal, "547");
    }
}
