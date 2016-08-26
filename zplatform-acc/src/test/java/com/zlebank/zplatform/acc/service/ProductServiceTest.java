/* 
 * ProductServiceTest.java  
 * 
 * version TODO
 *
 * 2016年8月26日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.FinanceProductBean;



/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月26日 上午10:03:00
 * @since 
 */
public class ProductServiceTest extends BaseTest{
    @Autowired
    private  FinanceProductService fProductService;
    //@Test
    public void open(){
        FinanceProductBean bean=new FinanceProductBean();
        bean.setFinancier("200000000000626");
        bean.setFundManager("200000000000627");
        bean.setProductCode("8000000001");
        bean.setProductName("测试新建产品");
        try {
            fProductService.openProduct(bean, 0L);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
