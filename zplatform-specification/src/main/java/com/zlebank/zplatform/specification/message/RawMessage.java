/* 
 * RawMessage.java  
 * 
 * version 1.0
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 上午11:01:39
 * @since
 */
abstract class RawMessage implements Message { 
    ///TODO
    //note : for sorted field to json string ,using TreeMap.
    //need test performance later;
    protected Map<String, Object> contentMap = new TreeMap<String, Object>();
    /**
     * return the copy of the value which the specified key is mapped,or null if
     * this message contains no mapping for the key.
     * 
     *
     * @param key
     * @param targetClass
     * @return
     */
    @Override
    public <T> T getObject(String key, Class<T> targetClass) {
        Object source = contentMap.get(key);
        if (source == null) {
            return null;
        }
        if (!source.getClass().isAssignableFrom(targetClass)) {
            throw new RuntimeException("cann't cast " + source.getClass()
                    + " to " + targetClass);
        }
        T t = BeanCopyUtil.copyBean(targetClass, source);
        return t;
    }

    

    /**
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {

        return String.valueOf(contentMap.get(key));
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public long getLong(String key) {
        return Long.valueOf(getString(key));
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public int getInt(String key) {
        return Integer.valueOf(getString(key));
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public boolean getBoolean(String key) {
        Object o = contentMap.get(key);
        if (o.getClass().equals(boolean.class)
                || o.getClass().equals(Boolean.class)) {
            return (Boolean) o;
        }
        throw new RuntimeException("except boolean value,bue not");
    }
    /**
     * 
     *
     * @param key
     * @param value
     */
    public void setObject(String key, Object value){
        contentMap.put(key, value) ;
    } 
    
    public Map<String, Object> getContentMap(){
        return Collections.unmodifiableMap(contentMap);
    }
}
