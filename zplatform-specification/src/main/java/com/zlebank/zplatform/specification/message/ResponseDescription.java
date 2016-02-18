/* 
 * ResponseDescription.java  
 * 
 * version 1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message;

import java.util.ResourceBundle;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 上午9:47:42
 * @since 
 */
public class ResponseDescription {
    private final static ResourceBundle RESOURCE = ResourceBundle
            .getBundle("response_des");

    public static String getMessage(String code,Object... param) {
        String message = RESOURCE.getString(code);
        return String.format(message, param);
    }
}
