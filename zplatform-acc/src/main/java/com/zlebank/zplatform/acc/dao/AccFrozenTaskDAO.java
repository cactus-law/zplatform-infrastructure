/* 
 * AccFrozenTaskDAO.java  
 * 
 * version TODO
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;

import com.zlebank.zplatform.acc.bean.AccountAmountQuery;
import com.zlebank.zplatform.acc.pojo.PojoAccFrozenTask;
import com.zlebank.zplatform.commons.dao.BasePagedQueryDAO;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午7:04:40
 * @since 
 */
public interface AccFrozenTaskDAO  extends BasePagedQueryDAO<PojoAccFrozenTask,AccountAmountQuery>{
    public PojoAccFrozenTask get(long id);
    public PojoAccFrozenTask getByIdForUpdate(long id);
    public List<PojoAccFrozenTask> getLockRecords(int id);
    public PojoAccFrozenTask getAccountByID(Long id);

}
