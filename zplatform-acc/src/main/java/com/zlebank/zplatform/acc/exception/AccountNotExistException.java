/* 
 * AccountNotExistException.java  
 * 
 * version 1.0
 *
 * 2015年8月28日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.exception;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月28日 下午1:34:23
 * @since 
 */
public class AccountNotExistException extends AbstractAccException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7863238818025471019L;

    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return "EASAC0001";
    }

}
