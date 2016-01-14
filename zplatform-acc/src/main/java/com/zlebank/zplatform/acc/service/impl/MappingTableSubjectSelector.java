/* 
 * MappingTableSubjectSelector.java  
 * 
 * version 1.0
 *
 * 2015年8月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.dao.BusiAcctToSubjectMappingDAO;
import com.zlebank.zplatform.acc.exception.BusiAcctToSubjectMappingNullException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcctSubjectMapping;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.BusinessActor;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月21日 下午5:59:16
 * @since
 */
@Service
public class MappingTableSubjectSelector implements SubjectSelector {

    @Autowired
    BusiAcctToSubjectMappingDAO busiToAcctSubjectMappingDAO;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Subject select(BusinessActor member, BusiAcct busiAcct)
            throws BusiAcctToSubjectMappingNullException {
        PojoBusiAcctSubjectMapping mappingQueryCondition = new PojoBusiAcctSubjectMapping(); 
        mappingQueryCondition.setBusinessActorType(member.getBusinessActorType());
        mappingQueryCondition.setUsage(busiAcct.getUsage()); 
        PojoSubject pojoSubject = busiToAcctSubjectMappingDAO.queryByConditions(mappingQueryCondition);
        if(pojoSubject == null){
            throw new BusiAcctToSubjectMappingNullException();
        }
        return BeanCopyUtil.copyBean(Subject.class, pojoSubject);
    }
    
    
}
