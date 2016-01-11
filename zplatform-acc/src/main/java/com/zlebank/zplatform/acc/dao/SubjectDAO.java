/* 
 * ISubjectDAO.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;

import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.commons.dao.BasePagedQueryDAO;

/**
 * Class Description
 *
 *科目DAO，负责科目的增删改查
 * @author yangpeng
 * @version
 * @date 2015年7月18日 上午10:18:56
 * @since 
 */
public interface SubjectDAO extends BasePagedQueryDAO<PojoSubject,Subject>{
    /**
     * 根据ID得到相应的科目
     * @param id
     * @return
     */
    public PojoSubject get(long id) throws AccBussinessException;
    /**
     * 
     * 根据科目号查询科目(未注销的科目)
     * @param code
     * @param excludeStatus
     * @return
     */
    public PojoSubject subjectByCode(String code) throws AccBussinessException;
    /**
     * 新增科目
     * @param subject
     * @return
     */
    public PojoSubject save(PojoSubject subject);
    /**
     * 根据父科目ID得到父科目
     * @return
     */
    public PojoSubject getParentByPID(Long  parentAcctId) throws AccBussinessException;
        /**
         * 得到自身未删除的子科目
         * @param parentAcctId
         * @return
         */

    public List<PojoSubject> getSubByOwn(PojoSubject subjectPojo);

}
