/* 
 * NotifyTaskTest.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午10:01:38
 * @since 
 */
public class NotifyTaskTest {
    ApplicationContext springContext;
    
     
    public void init() { 
        springContext = new ClassPathXmlApplicationContext(
                "SpecificationContextTest.xml");
    }
    
    
    public void test(){
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
