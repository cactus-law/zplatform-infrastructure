/* 
 * SpecificationException.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.exception;

import com.zlebank.zplatform.commons.exception.AbstractDescribeException;

/**
 * Represent Specification Exception.The code rule of its sub class please
 * see {@link AbstractDescribeException}. Specify value of field subSystem is
 * SS,the value of field module is PS.
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 下午12:16:14
 * @since
 * @see AbstractDescribeException
 */
public abstract class SpecificationException extends AbstractDescribeException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7682492108188678390L;

}
