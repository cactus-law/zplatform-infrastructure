/* 
 * Nofity.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import java.net.UnknownHostException;

import com.zlebank.zplatform.specification.message.Response;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午5:15:24
 * @since 
 */
public interface Notify {
    
    void addNotify(RequestType notifyRequestType,String version ,Response response)throws UnknownHostException;
    
}
