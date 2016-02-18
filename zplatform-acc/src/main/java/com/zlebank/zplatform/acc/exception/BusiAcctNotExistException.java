/* 
 * BusiAcctNotExistException.java  
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
 * @date 2015年9月1日 上午9:38:59
 * @since 
 */
public class BusiAcctNotExistException extends AbstractBusiAcctException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8276664994151759479L;

    
    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return "EASBA0002";
    }

}
