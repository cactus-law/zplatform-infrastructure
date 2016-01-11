/* 
 * AccSubjectSelectorDAO.java  
 * 
 * version 1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import com.zlebank.zplatform.acc.exception.BusiAcctToSubjectMappingNullException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcctSubjectMapping;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月25日 下午6:36:26
 * @since
 */
public interface BusiAcctToSubjectMappingDAO extends BaseDAO<PojoBusiAcctSubjectMapping> {
    public PojoSubject queryByConditions(PojoBusiAcctSubjectMapping conditions)
            throws BusiAcctToSubjectMappingNullException;
}
