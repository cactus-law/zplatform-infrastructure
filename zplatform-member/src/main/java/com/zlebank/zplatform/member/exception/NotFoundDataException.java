/* 
 * NotFoundDataException.java  
 * 
 * version TODO
 *
 * 2016年9月30日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.exception;

import com.zlebank.zplatform.commons.exception.BaseException;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月30日 上午10:49:08
 * @since 
 */
public class NotFoundDataException extends BaseException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4054049019984063048L;

    public NotFoundDataException(Class<?> clazz, String errorMsg){
        super(clazz.getName()+errorMsg);
    }
}
