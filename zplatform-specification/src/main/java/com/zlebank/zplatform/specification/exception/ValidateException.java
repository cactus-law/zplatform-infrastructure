/* 
 * ValidateFailException.java  
 * 
 * version 1.0
 *
 * 2015年9月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.exception;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月14日 下午3:36:43
 * @since 
 */
public class ValidateException extends SpecificationException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1535213985557777061L;

    /**
     *
     * @return
     */
    @Override
    public String getCode() { 
        return "ESSPS0001";
    }

}
