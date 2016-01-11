/* 
 * AccBussinessException.java  
 * 
 * version v1.0
 *
 * 2015年8月26日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.exception;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月26日 上午10:24:12
 * @since 
 */
public class AccBussinessException extends AbstractAccException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8982263131254694025L;
    private String code;
    public AccBussinessException(String code,Object ...param) {
        this.code=code;
        this.setParams(param);
    }
    public AccBussinessException(String code) {
        this.code=code;
    }
    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        return code;
    }

}
