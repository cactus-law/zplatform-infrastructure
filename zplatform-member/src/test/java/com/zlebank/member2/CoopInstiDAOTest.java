/* 
 * CoopInstiDAOTest.java  
 * 
 * version TODO
 *
 * 2016年9月13日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member2;

import java.util.List;

import net.sf.json.util.JSONUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zlebank.zplatform.commons.dao.pojo.ProductModel;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.exception.AbstractCoopInstiException;
import com.zlebank.zplatform.member.service.CoopInstiProductService;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年9月13日 上午8:50:21
 * @since 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/ContextTest.xml")
public class CoopInstiDAOTest {

	@Autowired
	private CoopInstiDAO coopInstiDAO;
	@Autowired
	private CoopInstiProductService coopInstiProductService;
	
	
	public void test_queryCoopProduct(){
		List<ProductModel> coopProductList = coopInstiDAO.getCoopProductList(2);
		
		System.out.println(JSONUtils.valueToString(coopProductList));
	}
	@Test
	public void test_queryCoopProductList() throws AbstractCoopInstiException{
		List<ProductModel> products = coopInstiProductService.getProducts(2);
		
		System.out.println(JSONUtils.valueToString(products));
	}
}
