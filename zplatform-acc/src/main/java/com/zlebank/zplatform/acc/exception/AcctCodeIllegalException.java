/* 
 * AcctCodeIllegalException.java  
 * 
 * version 1.0
 *
 * 2015年9月6日 
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
 * @date 2015年9月6日 下午12:24:21
 * @since 
 */
public class AcctCodeIllegalException extends AbstractAccException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6569339152035328020L;

    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return "EASAC0006";
    }

}
