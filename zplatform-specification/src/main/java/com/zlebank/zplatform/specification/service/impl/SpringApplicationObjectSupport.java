/* 
 * SpringApplicationObjectSupport.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午4:54:31
 * @since
 */
public class SpringApplicationObjectSupport extends WebApplicationObjectSupport {
    protected ApplicationContext applicationContext;

    protected void fetchApplicationContext() {
        if (applicationContext == null) {
            applicationContext = getApplicationContext();
        }
        if (applicationContext == null) {
            applicationContext = getWebApplicationContext();
        }
        if (applicationContext == null) {
            // TODO
            throw new RuntimeException();
        }
    }
}
