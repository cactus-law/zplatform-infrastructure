/* 
 * NotifyFactory.java  
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
import org.springframework.stereotype.Service;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午5:14:13
 * @since
 */
@Service
public class NotifyFactory {

    public Notify getNotify(ApplicationContext applicationContext,
            RequestType requestType) { 
        switch (requestType) {
            case SYCINDIVIDUALNOTIFY :
                return (Notify)applicationContext.getBean("openIndividualNotify"); 
            case SYCMERCHNOTIFY :
                return (Notify)applicationContext.getBean("openMinorMerchantNotify");  
            default :
                return null;
        }
    }

}
