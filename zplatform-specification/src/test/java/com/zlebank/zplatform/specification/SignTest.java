/* 
 * SignTest.java  
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

import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.parser.SpecificationParser;

/**
 * 签到测试
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午5:12:17
 * @since 
 */
public class SignTest {
    private JSONObject data;
    ApplicationContext springContext;
    private final static String FILED_REQUEST_TYPE = "requestType";
    private final static String FILED_MERCHNO = "merchNo"; 
    private final static String FILED_SESSIONID = "sessionId"; 
    @Before
    public void init() {
        data = mockRequestJson();
        springContext = new ClassPathXmlApplicationContext("SpecificationContextTest.xml");
    }
    @Test
    @Ignore
    public void testEntranceAPILog() throws Exception, ClassNotFoundException, InstantiationException, IllegalAccessException{
//        ApplicationContext springContext = new ClassPathXmlApplicationContext("SpecificationContextTest.xml"); 
        SpecificationParser parser = (SpecificationParser) springContext
                .getBean("specificationParser");
        @SuppressWarnings("unused")
        SpecificationProcessor sign = (SpecificationProcessor) springContext.getBean("sign");
        RequestType requestType = RequestType.formatValue(data.getString(FILED_REQUEST_TYPE));
        @SuppressWarnings("unused")
        Message request =parser.parse(data,requestType,true); 
       // sign.process(request);
    }
    
    private JSONObject mockRequestJson() { 
        JSONObject json = new JSONObject();
        json.put(FILED_REQUEST_TYPE, RequestType.SIGN.getCode());
        json.put(FILED_MERCHNO, "21321312");
        json.put(FILED_SESSIONID, "12321312321");
        json.put("mac", "12321");
        json.put("notifyUrl", "12321");
        return json;
    }
}
