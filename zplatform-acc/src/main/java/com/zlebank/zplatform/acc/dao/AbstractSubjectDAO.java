/* 
 * AbstractSubjectDAO.java  
 * 
 * version TODO
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import com.zlebank.zplatform.acc.pojo.PojoAbstractSubject;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月27日 下午5:33:00
 * @since 
 */
public interface AbstractSubjectDAO  extends BaseDAO<PojoAbstractSubject>{
    /**
     * 根据ID得到相应的科目
     * @param id
     * @return
     */
    public PojoAbstractSubject get(long id);
    /**
     *  总账处理（叶子科目同步记账时调用）
     * @param parentId
     * @throws Exception 
     */
    public void processLedger(PojoAccount account) throws Exception;
    /**
     * 使用原生SQL更新解决并发问题
     * @param acctid
     * @return PojoAccount
     */
    public int updateBySql(PojoAbstractSubject account);
}
