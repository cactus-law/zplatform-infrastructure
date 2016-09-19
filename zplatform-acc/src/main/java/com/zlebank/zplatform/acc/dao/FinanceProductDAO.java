/* 
 * FinanceProductDAO.java  
 * 
 * version TODO
 *
 * 2016年8月22日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import com.zlebank.zplatform.acc.pojo.PojoFinanceProduct;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月22日 下午3:04:28
 * @since 
 */
public interface FinanceProductDAO extends BaseDAO<PojoFinanceProduct> {
	public PojoFinanceProduct getProductByCode(String productCode);
}
