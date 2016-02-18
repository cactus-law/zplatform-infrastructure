/* 
 * UnknowRequestTypeException.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
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
 * @date 2015年9月11日 下午12:30:32
 * @since 
 */
public class UnknowRequestTypeException extends SpecificationException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3892159405599014241L;

    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        return "ESSPS0002";
    }

}
