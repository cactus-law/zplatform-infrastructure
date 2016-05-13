/* 
 * ContextLoaderTest.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.context.DefaultSpecificationContext;
import com.zlebank.zplatform.specification.context.SpecificationContext;
import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.PropertyField;
import com.zlebank.zplatform.specification.metadata.Specification;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 下午3:57:39
 * @since 
 */
public class ContextLoaderTest {
    
    @Test
    @Ignore
    public void test() throws IllegalArgumentException, IllegalAccessException{ 
        @SuppressWarnings({"unused", "resource"})
        ApplicationContext springContext = new ClassPathXmlApplicationContext("SpecificationContextTest.xml"); 
        
        SpecificationContext context = DefaultSpecificationContext.getInstance(); 
        
        Specification specification = context.get(RequestType.SIGN);
        
        for(Field field:specification.getRequestFields().values()){
            System.out.println("-------");
             for(java.lang.reflect.Field jField:PropertyField.class.getDeclaredFields()){
                 jField.setAccessible(true);
                 System.out.println(jField.getName()+":"+ jField.get(field));
             }
             System.out.println("-------");
        }
        
        for(Field field:specification.getResponseFields().values()){
            System.out.println("-------");
            for(java.lang.reflect.Field jField:PropertyField.class.getDeclaredFields()){
                jField.setAccessible(true);
                System.out.println(jField.getName()+":"+ jField.get(field));
            }
            System.out.println("-------");
       } 
    }
    
}
