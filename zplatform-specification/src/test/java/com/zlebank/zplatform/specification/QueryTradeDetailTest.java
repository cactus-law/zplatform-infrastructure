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
 * 账户收支明细查询
 *
 * @author yangpeng
 * @version
 * @date 2015年9月16日 下午8:24:25
 * @since 
 */
public class QueryTradeDetailTest  {
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
        SpecificationProcessor sign = (SpecificationProcessor) springContext.getBean("queryTradeDetail");
        RequestType requestType = RequestType.formatValue(data.getJSONObject("head").getString(FILED_REQUEST_TYPE));
        Message request =parser.parse(data,requestType,true); 
        Response  res = sign.process(request);
        System.out.println("-----返回结果-----");
        System.out.println(res.getResCode()+res.getResDes());
        
//        SpecificationParser specificationParser = new SpecificationParser();
//        JSONObject json = specificationParser.parse(res);
//        System.out.println("-----返回结果-----");
//        System.out.println("-----json结果-----" + json);
//        System.out.println(res.getResCode()+res.getResDes());
        
    }
    
    private JSONObject mockRequestJson() { 
        
        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.QUERY_TRADE_DETAIL.getCode());
        head.put("version", "v1.0");
        
        
        
        JSONObject content = new JSONObject();
        content.put("merchNo", 200000000000272L);
        content.put("sessionId", String.valueOf(System.currentTimeMillis()));
        content.put("acctCode", "622132110101100000000000368");// check
        content.put("from", "2015-09-19");
        content.put("to", "2015-09-19");

        content.put("mac", "110");
        
        JSONObject json = new JSONObject();
        json.put("head", head);
        json.put("content", content);
        return json;
    }
}
