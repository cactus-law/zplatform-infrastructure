/* 
 * SubjectMappingNullException.java  
 * 
 * version 1.0
 *
 * 2015年8月25日 
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
 * @date 2015年8月25日 下午6:43:28
 * @since 
 */
public class BusiAcctToSubjectMappingNullException extends AbstractBusiAcctException{

    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 256588692268286177L;

    @Override
    public String getCode() { 
        return "EASBA0001";
    }

}
