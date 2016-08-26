/* 
 * FinanceProductDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年8月22日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.dao.FinanceProductDAO;
import com.zlebank.zplatform.acc.pojo.PojoFinanceProduct;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月22日 下午3:07:29
 * @since 
 */
@Repository
public class FinanceProductDAOImpl extends HibernateBaseDAOImpl<PojoFinanceProduct> 
            implements FinanceProductDAO {

}
