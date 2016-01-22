/* 
 * GetAccountFailedException.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.exception;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午3:24:05
 * @since 
 */
public class GetAccountFailedException extends AbstractMemberDescException  {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -999540096602965636L;
    public GetAccountFailedException(Object ... para ) {
        this.params = para;
    }
    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        return "EMSIF0006";
    }
}