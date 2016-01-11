/* 
 * Header.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message;

import com.zlebank.zplatform.specification.RequestType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午10:13:13
 * @since
 */
public class Head {
    private RequestType requestType;
    private String version;
    
    public RequestType getRequestType() {
        return requestType;
    }
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
