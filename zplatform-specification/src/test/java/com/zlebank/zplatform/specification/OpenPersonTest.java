/* 
 * PersonSynch.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
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
 * 个人会员开户
 *
 * @author yangpeng
 * @version
 * @date 2015年9月16日 下午8:24:25
 * @since 
 */
public class OpenPersonTest  {
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
        SpecificationProcessor sign = (SpecificationProcessor) springContext.getBean("openPerson");
        RequestType requestType = RequestType.formatValue(data.getJSONObject("head").getString(FILED_REQUEST_TYPE));
        Message request =parser.parse(data,requestType,true); 
        Response  res = sign.process(request);
        System.out.println("-----返回结果-----");
        System.out.println(res.getResCode()+res.getResDes());
        System.out.println(res.getString("memberId"));
    }
    
    private JSONObject mockRequestJson() { 
        
        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.SYCINDIVIDUAL.getCode());
        head.put("version", "v1.0");
        
        JSONObject content = new JSONObject();
//        json.put(FILED_REQUEST_TYPE, RequestType.SYCINDIVIDUAL.getCode());

        content.put("merchNo", 200000000000272L);
        content.put("sessionId", String.valueOf(System.currentTimeMillis()));
        content.put("entranceMemId", "lxs");// check
        content.put("phone", 15802712561L);// check
        content.put("email", "luxiaoshuai01@zlebank.com");// check
        content.put("name", "鲁晓帅");
        content.put("loginName", "luxiaoshuai02");
        content.put("loginPwd", "FK0d0c3SFKTWYMwPefLlwrlIj2PyUlJPwcrPW/TIDhgahQ8vUhvtcSDwCYNwpoc0v2IC892w9gmj8T1UKJourQvQSQfZl/3xVmJ/Z3wCqstM3N8m4HgrKEBUx4+fnQVvkoyOhsuU2c31bJUhxS3srDwQM0OHzj9k5Cs466t6ld4=");
        content.put("gender", "f");
        content.put("mac", "12321");
        
        JSONObject json = new JSONObject();
        json.put("head", head);
        json.put("content", content);
        return json;
    }
}
