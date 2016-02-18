/* 
 * MerchSynchorQueryTest.java  
 * 
 * version TODO
 *
 * 2015年9月18日 
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

import com.zlebank.zplatform.specification.exception.SpecificationException;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.parser.SpecificationParser;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月18日 下午12:07:10
 * @since 
 */
public class MerchSynchorQueryTest {
    private JSONObject data;
    ApplicationContext springContext;
    private final static String FILED_REQUEST_TYPE = "requestType"; 
    SpecificationProcessor msq ;
    SpecificationParser parser ;
    @Before
    public void init() {
        data = mockRequestJson();
        springContext = new ClassPathXmlApplicationContext("SpecificationContextTest.xml");
        msq = (SpecificationProcessor) springContext.getBean("queryMerchant");
        parser = (SpecificationParser) springContext
                .getBean("specificationParser");
    
    }
    
    
    @Test
    public void testRealProcess() throws SpecificationException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RequestType requestType = RequestType.formatValue(data.getJSONObject("head").getString(FILED_REQUEST_TYPE));
        Message request =parser.parse(data,requestType,true); 
        Response  res = msq.process(request);
        System.out.println("-----返回结果-----");
        System.out.println(res.getResCode()+res.getResDes());
        
        
    }

    
    private JSONObject mockRequestJson() { 
        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.SYCMERCHQUERY.getCode());
        head.put("version", "v1.0");
        
        JSONObject json = new JSONObject();
//        json.put(FILED_REQUEST_TYPE, RequestType.SYCMERCHQUERY.getCode());
        json.put("merchNo", "200000000000195");
        json.put("sessionId", "1234567890123456");
        json.put("minorMerchNo", "200000000000333");
        json.put("entranceMemId", "LH1234567891");// check
        json.put("mac", "12321");
        
        JSONObject jsonT = new JSONObject();
        jsonT.put("head", head);
        jsonT.put("content", json);
        
        
        return jsonT;
    }
    
}
