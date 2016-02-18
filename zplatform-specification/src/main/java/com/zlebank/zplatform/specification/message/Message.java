/* 
 * Message.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message;

import java.util.Map;


/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午9:18:16
 * @since
 */
public interface Message { 
    String getString(String key);
    long getLong(String key);
    int getInt(String key);
    boolean getBoolean(String key);
    <T> T getObject(String key, Class<T> targetClass);
   
    void setObject(String key, Object o);
    Map<String, Object> getContentMap();
}
