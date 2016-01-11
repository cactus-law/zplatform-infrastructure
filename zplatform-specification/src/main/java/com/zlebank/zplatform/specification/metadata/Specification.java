/* 
 * Specification.java  
 * 
 * version 1.0
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

import java.util.HashMap;
import java.util.Map;

import com.zlebank.zplatform.specification.RequestType;

/**
 * This class represent the api in doc 《商户服务接口》.Every instance of the class
 * represent a concrete api definition
 *
 * @author yangying
 * @version
 * @date 2015年9月9日 下午7:11:30
 * @since
 */
public class Specification {
    private Map<String,Field> requestFields;
    private RequestType requestType;
    private Map<String,Field> responseFields;
    private String version;

    private Specification() {
    }

    public static Specification newInstance(RequestType requestType,
            String version) {
        Specification instance = new Specification();
        instance.requestFields = new HashMap<String,Field>();
        instance.responseFields = new HashMap<String,Field>();
        instance.requestType = requestType;
        instance.version = version;
        return instance;
    }

    public Map<String,Field> getRequestFields() {
        return requestFields;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Map<String,Field> getResponseFields() {
        return responseFields;
    }

    public String getVersion() {
        return version;
    }

}
