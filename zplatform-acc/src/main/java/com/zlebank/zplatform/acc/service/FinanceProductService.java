/* 
 * FinanceProductService.java  
 * 
 * version TODO
 *
 * 2016年7月20日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import com.zlebank.zplatform.acc.bean.FinanceProductBean;

/**
 * 
 *
 * @author houyong
 * @version
 * @date 2016年7月20日 下午4:15:29
 * @since
 */
public interface FinanceProductService {
	/**
	 * 开通金融产品
	 * 
	 * @param bean
	 * @throws Exception
	 */
	void openProduct(FinanceProductBean bean, long userId) throws Exception;

	/**
	 * 通过产品代码获取产品信息
	 * 
	 * @param productCode
	 * @return
	 */
	public FinanceProductBean getProductByCode(String productCode);
}
