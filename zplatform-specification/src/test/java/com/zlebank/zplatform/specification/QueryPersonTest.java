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
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.SpecificationProcessor;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.parser.SpecificationParser;

/**
 * 个人会员查询
 *
 * @author yangpeng
 * @version
 * @date 2015年9月16日 下午8:24:25
 * @since 
 */
public class QueryPersonTest  {
    private JSONObject data;
    ApplicationContext springContext;
    private final static String FILED_REQUEST_TYPE = "requestType"; 
    @Before
    public void init() {
        data = mockRequestJson();
        springContext = new ClassPathXmlApplicationContext("SpecificationContextTest.xml");
    }
    @Test
    @Ignore
    public void testOpenMerchant() throws Exception, ClassNotFoundException, InstantiationException, IllegalAccessException{
        SpecificationParser parser = (SpecificationParser) springContext
                .getBean("specificationParser");
        SpecificationProcessor sign = (SpecificationProcessor) springContext.getBean("queryPerson");
        RequestType requestType = RequestType.formatValue(data.getJSONObject("head").getString(FILED_REQUEST_TYPE));
        Message request =parser.parse(data,requestType,true); 
        Response  res = sign.process(request);
        System.out.println("-----返回结果-----");
        System.out.println(res.getResCode()+res.getResDes());
        System.out.println(res.getString("memberId"));
        System.out.println(res.getString("status"));
    }
    
    private JSONObject mockRequestJson() { 
        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.SYCINDIVIDUALQUERY.getCode());
        head.put("version", "v1.0");
        
        JSONObject json = new JSONObject();
//        json.put(FILED_REQUEST_TYPE, RequestType.SYCINDIVIDUALQUERY.getCode());

        json.put("merchNo", 200000000000177L);
        json.put("sessionId", "1234567890123456");
        json.put("memberId", "");// check
        json.put("entranceMemId", "00000008");// check
       
        json.put("mac", "12321");
        
        JSONObject jsonT = new JSONObject();
        jsonT.put("head", head);
        jsonT.put("content", json);
        
        return jsonT;
    }
}
