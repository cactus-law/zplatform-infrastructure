/* 
 * BusiAcctRepeatException.java  
 * 
 * version 1.0
 *
 * 2015年9月1日 
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
 * @date 2015年9月1日 上午9:12:39
 * @since 
 */
public class BusiAcctRepeatException extends AbstractBusiAcctException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4605193348381615398L;

    /**
     *
     * @return
     */
    @Override
    public String getCode() { 
        return "EASBA0003";
    }
    
    public BusiAcctRepeatException() {
        super();
    }
    
    public BusiAcctRepeatException(Throwable cause) {
        super(cause);
    }

}
