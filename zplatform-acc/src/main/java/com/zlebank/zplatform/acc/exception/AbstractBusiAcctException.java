/* 
 * AbstractBusiAcctException.java  
 * 
 * version 1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.exception;

import java.util.ResourceBundle;

import com.zlebank.zplatform.commons.exception.AbstractDescribeException;

/**
 * Represent Business Account Exception.The code rule of its sub class please
 * see {@link AbstractDescribeException}. Specify value of field subSystem is
 * AS,the value of field module is BA.
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 下午12:16:14
 * @since
 * @see AbstractDescribeException
 */
public abstract class AbstractBusiAcctException extends
		AbstractDescribeException {

	/**
	 * serialVersionUID
	 */

	private static final long serialVersionUID = 8599951385672960149L;

	public AbstractBusiAcctException() {
		super();
	}

	public AbstractBusiAcctException(Throwable cause) {
		super(cause);
	}

	private static final ResourceBundle RESOURCE = ResourceBundle
			.getBundle("exception_des");

	@Override
	public ResourceBundle getResourceBundle() {
		return RESOURCE;
	}
}
