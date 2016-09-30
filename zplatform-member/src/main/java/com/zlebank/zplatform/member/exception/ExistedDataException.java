/* 
 * ExistedDataException.java  
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
 * @date 2016年9月30日 上午10:49:43
 * @since 
 */
public class ExistedDataException extends BaseException{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5687759656879971969L;

    public ExistedDataException(Class<?> clazz){
        super(clazz.getName());
    }
}
