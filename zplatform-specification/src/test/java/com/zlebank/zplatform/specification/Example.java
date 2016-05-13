/* 
 * Example.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
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
import com.zlebank.zplatform.specification.SpecificationProcessorFactory;
import com.zlebank.zplatform.specification.exception.SpecificationException;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.parser.SpecificationParser;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午9:29:10
 * @since
 */
public class Example {

    private JSONObject data;
    ApplicationContext springContext;
    @Before
    public void init() {
        data = mockRequestJson();
        springContext = new ClassPathXmlApplicationContext(
                "SpecificationContextTest.xml");
    }
    private final static String FILED_REQUEST_TYPE = "requestType";
    private final static String FILED_MERCHNO = "merchNo";
    private final static String FILED_SESSIONID = "sessionId";
    private JSONObject mockRequestJson() {

        JSONObject json = new JSONObject();

        JSONObject head = new JSONObject();
        head.put(FILED_REQUEST_TYPE, RequestType.SIGN.getCode());
        head.put("version", "v1.0");
        JSONObject content = new JSONObject();
        content.put(FILED_MERCHNO, "200000000000272");
        content.put(FILED_SESSIONID, "16");
        content.put("mac", "gsqEcPjN5iIt7TTYWFxvLrKHegmB+SSSWOh+cxC9+Xdbkym8RjSBJCwZwYpWuldkzuq4/t9U3S/1y1ySX95KulA6707D2VAMVL4GAN/2h6yjWxcCcUDcivnL6gRGPYpRuw6ZjL1p4Qp2l2jZWoHCU3g2Iphcu3BHzubTFsxrVJQ=");
        content.put("notifyUrl", "11");
        json.put("head", head);
        json.put("content", content);
        return json;
    }

    @Test
    @Ignore
    public void example() {
        SpecificationParser parser = (SpecificationParser) springContext
                .getBean("specificationParser");
        try {
            RequestType requestType = RequestType.formatValue(data.getJSONObject("head")
                    .getString(FILED_REQUEST_TYPE));
            Message request = parser.parse(data, requestType, true);

            SpecificationProcessor processor = ((SpecificationProcessorFactory) springContext
                    .getBean("specificationProcessorFactory"))
                    .getProcessor(requestType);
            Response response = processor.process(request);
            JSONObject jsonRes = parser.parse(response);
            System.out.println(jsonRes);
        } catch (Exception e) {
            if (e instanceof SpecificationException) {
                // TODO make AbstractAccException json response.
                // res :rescode =e.getCode() resdes= e.getMessage();
                e.printStackTrace();
            } else if (e instanceof RuntimeException) {
                // TODO make nested error json response
                e.printStackTrace();
            } else {
                // TODO make unknow error json response
                e.printStackTrace();
            }
        }
    }

}
