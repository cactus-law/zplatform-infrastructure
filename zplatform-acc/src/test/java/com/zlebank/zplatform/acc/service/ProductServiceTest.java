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

import java.util.Date;

import org.junit.Test;
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
        bean.setFinancier("200000000001432");
        bean.setFundManager("100000000001423");
        bean.setProductCode("8000000001");
        bean.setProductName("测试新建产品");
        bean.setNotes("互金测试产品");
        bean.setRemarks("测试使用");
        try {
            fProductService.openProduct(bean, 0L);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
