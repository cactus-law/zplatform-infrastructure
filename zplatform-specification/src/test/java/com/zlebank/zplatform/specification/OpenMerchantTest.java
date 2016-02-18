/* 
 * OpenMerchantTest.java  
 * 
 * version TODO
 *
 * 2015年9月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.SpecificationProcessor;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.parser.SpecificationParser;

/**
 * 二级商户开户
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月17日 下午5:43:36
 * @since 
 */
public class OpenMerchantTest {
    private JSONObject data;
    ApplicationContext springContext;
    private final static String FILED_REQUEST_TYPE = "requestType"; 
    @Before
    public void init() {
        data = mockRequestJson();
        springContext = new ClassPathXmlApplicationContext("SpecificationContextTest.xml");
    }
    @Test
    public void testOpenMerchant() throws Exception, ClassNotFoundException, InstantiationException, IllegalAccessException{
        SpecificationParser parser = (SpecificationParser) springContext
                .getBean("specificationParser");
        SpecificationProcessor sign = (SpecificationProcessor) springContext.getBean("openMerchant");
        RequestType requestType = RequestType.formatValue(data.getJSONObject("head").getString(FILED_REQUEST_TYPE));
        Message request =parser.parse(data,requestType,true); 
        Response  res = sign.process(request);
        System.out.println("-----返回结果-----");
        System.out.println(res.getResCode()+res.getResDes());
    }
    
    private JSONObject mockRequestJson() { 
        
        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.SYCMINORMERCH.getCode());
        head.put("version", "v1.0");
        
        JSONObject content = new JSONObject();
        

        content.put("merchNo", "200000000000195");
        content.put("sessionId", String.valueOf(System.currentTimeMillis()));
        content.put("merchId", "LH123456789");
        content.put("email", "luxiaoshuai1152@169.com");// check
        content.put("name", "luxiaoshuai");
        content.put("alias", "lu");
        content.put("province", "110000");
        content.put("city", "110100");
        content.put("county", "110105");
        content.put("taxregNo", "SW00000001SW00000001SW01101119EE");// check
        content.put("licenceNo", "GS00000001SW00000001SW01101119EE");// check
        content.put("orgCode", "1234567890");
        content.put("address", "太阳宫");
        content.put("postCode", "100086");
        content.put("primaryBusiness", "1");
        content.put("corporation", "鲁晓帅");
        content.put("corpCertType", "1");
        content.put("corpCertNo", "131122198809042433");
        content.put("despositBank", "313100001792");//402551080008
        content.put("accountName", "鲁晓帅");
        content.put("accountNo", "6226091212413805");
        
//        content.put("contacts", "联晓帅");
//        content.put("contactsTelNo", "13126342943");// check
//        content.put("contactsAddr", "丰台东大街");
//        content.put("contactsPostCode", "100091");
//        content.put("contactsEmail", "bruce.lun@163.com");

        content.put("mac", "12321");
        
        JSONObject json = new JSONObject();
        json.put("head", head);
        json.put("content", content);

        return json;
    }
}
