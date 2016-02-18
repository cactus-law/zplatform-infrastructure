/* 
 * AccountSubjectSelectDAOImpl.java  
 * 
 * version 1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.dao.BusiAcctToSubjectMappingDAO;
import com.zlebank.zplatform.acc.exception.BusiAcctToSubjectMappingNullException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcctSubjectMapping;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月25日 下午6:40:46
 * @since
 */
@Repository
public class BusiAcctToSubjectMappingDAOImpl
        extends
            HibernateBaseDAOImpl<PojoBusiAcctSubjectMapping>
        implements
            BusiAcctToSubjectMappingDAO {

    /**
     *
     * @param usage
     * @param memberType
     * @return
     */
    @Override
    public PojoSubject queryByConditions(PojoBusiAcctSubjectMapping conditions)
            throws BusiAcctToSubjectMappingNullException {
        Session session = getSession();
        Criteria criteria = session
                .createCriteria(PojoBusiAcctSubjectMapping.class);
        criteria.add(Example.create(conditions).excludeZeroes());
        PojoBusiAcctSubjectMapping mappedBusiAccSubject = (PojoBusiAcctSubjectMapping) criteria
                .uniqueResult();
        if (mappedBusiAccSubject == null) {
            throw new BusiAcctToSubjectMappingNullException();
        }
        return mappedBusiAccSubject.getSubject();
    }

}
