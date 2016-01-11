/* 
 * FieldAttrResolverFactory.java  
 * 
 * version 1.0
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.resolver;

import java.util.HashMap;
import java.util.Map;

import com.zlebank.zplatform.specification.metadata.Field;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午2:41:07
 * @since 
 */
public class FieldAttrResolverFactory {
    
    private static FieldAttrResolverFactory instance;
    private Map<String,FieldAttrResolver> resovlerMap ;
    private FieldAttrResolverFactory(){
        
    }
    public static FieldAttrResolverFactory getInstatnce(){
        if(instance==null){
            instance = new FieldAttrResolverFactory();
            instance.resovlerMap = new HashMap<String, FieldAttrResolver>();
        }
        return instance;
    }
    
    public FieldAttrResolver getAttrResolver(Field field){
        Class<FieldAttrResolver> clazz =  field.getAttrResolverClass();
        FieldAttrResolver fieldAttrResolver = resovlerMap.get(clazz.getName());
        if(fieldAttrResolver == null){
            try {
                fieldAttrResolver = clazz.newInstance();
            } catch (Exception e) {
                RuntimeException re = new RuntimeException();
                re.initCause(e);
                throw re;
            }
            resovlerMap.put(clazz.getName(), fieldAttrResolver);
        }
        return fieldAttrResolver;
    }
}
