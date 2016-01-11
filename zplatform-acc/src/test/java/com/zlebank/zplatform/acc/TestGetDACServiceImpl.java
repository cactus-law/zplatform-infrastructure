/* 
 * TestGetDACServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zlebank.zplatform.acc.pojo.PojoAbstractSubject;
import com.zlebank.zplatform.acc.service.impl.GetDACServiceImpl;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月16日 下午4:13:02
 * @since
 */
public class TestGetDACServiceImpl {

    
    private static GetDACServiceImpl getdac;

    @BeforeClass
    public static void getSubject() {
        getdac = (GetDACServiceImpl) ApplicationContextUtil.get().getBean(
                "getDACServiceImpl");

    }

    @Test
    public void test() {

        SessionFactory sessionFactory = (SessionFactory) ApplicationContextUtil
                .get().getBean("sessionFactory");
        Session sesison = sessionFactory.openSession();
        sesison.beginTransaction();
        Criteria criteria = sesison.createCriteria(PojoAbstractSubject.class);
        @SuppressWarnings("unchecked")
        List<PojoAbstractSubject> list = criteria.list();
        
        for (PojoAbstractSubject pojoAbstractSubject : list) {
            String dac = getdac.generteDAC(pojoAbstractSubject.getAcctCode(),
                    pojoAbstractSubject.getBalance(), pojoAbstractSubject.getFrozenBalance(),
                    pojoAbstractSubject.getTotalBanance());
            pojoAbstractSubject.setDac(dac);
            sesison.merge(pojoAbstractSubject);
        }
        
        sesison.getTransaction().commit();
        sesison.close();
    }

}
