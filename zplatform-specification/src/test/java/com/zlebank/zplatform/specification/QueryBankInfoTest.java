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


import net.sf.json.JSONArray;
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
 * 
 * 银行行号查询
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月21日 下午5:08:35
 * @since
 */
public class QueryBankInfoTest  {
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
        SpecificationProcessor sign = (SpecificationProcessor) springContext.getBean("queryBankInfo");
        RequestType requestType = RequestType.formatValue(data.getJSONObject("head").getString(FILED_REQUEST_TYPE));
        Message request =parser.parse(data,requestType,true); 
        Response  res = sign.process(request);
        System.out.println("-----返回结果-----");
        System.out.println(res.getResCode()+res.getResDes());
        
        SpecificationParser specificationParser = new SpecificationParser();
        JSONObject json = specificationParser.parse(res);
        System.out.println("-----json结果-----");
        JSONObject content =  (JSONObject) json.get("content");
        JSONArray subMsgs = JSONArray.fromObject(content.get("banks"));
        for (int i = 0; i < subMsgs.size(); i++) {
            System.out.println((i+1)+"行:"+subMsgs.get(i));
        }
    }
    
    private JSONObject mockRequestJson() { 
        
        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.QUERY_BANK_NO.getCode());
        head.put("version", "v1.0");
        
        
        
        JSONObject content = new JSONObject();
        content.put("merchNo", 200000000000272L);
        content.put("sessionId", String.valueOf(System.currentTimeMillis()));
        content.put("bankName", "荆州松滋支行西斋分理处");// check

        content.put("mac", "110");
        
        JSONObject json = new JSONObject();
        json.put("head", head);
        json.put("content", content);
        return json;
    }
}
