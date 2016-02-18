/* 
 * StartUpListener.java  
 * 
 * version 1.0
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.startup;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.specification.context.SpecificationContextLoader;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月9日 下午8:29:24
 * @since
 */
@Service
public class StartUpListener
        implements
            ApplicationListener<ContextRefreshedEvent> {

    private AtomicBoolean hasLoaded = new AtomicBoolean(false);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null)
            if (!hasLoaded.get()) {
                hasLoaded.getAndSet(true);
                SpecificationContextLoader.getInstance().load();
            }else{
                SpecificationContextLoader.getInstance().reLoad();
            }
    }
}
