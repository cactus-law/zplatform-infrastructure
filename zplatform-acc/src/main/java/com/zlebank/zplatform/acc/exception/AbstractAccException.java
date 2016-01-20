/* 
 * AccException.java  
 * 
 * version 1.0
 *
 * 2015年8月24日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.exception;

import java.util.ResourceBundle;

import com.zlebank.zplatform.commons.exception.AbstractDescribeException;

/**
 * Abstract acc module exception Represent Business Account Exception.The code
 * rule of its sub class please see {@link AbstractDescribeException}. Specify
 * value of field subSystem is AS,the value of field module is AC
 *  
 * 
 * @author yangying
 * @version
 * @date 2015年8月24日 上午10:38:54
 * @since
 * @see AbstractDescribeException
 */
public abstract class AbstractAccException extends AbstractDescribeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4901729172010673980L;
    
    private static final  ResourceBundle RESOURCE = ResourceBundle.getBundle("exception_des");
    @Override
	public ResourceBundle getResourceBundle() {
		return RESOURCE;
	}
}
