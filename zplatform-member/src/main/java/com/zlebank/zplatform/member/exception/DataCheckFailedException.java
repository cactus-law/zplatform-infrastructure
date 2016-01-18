/* 
 * DataCheckFailedException.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.exception;

/**
 * 数据校验异常
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午7:15:57
 * @since 
 */
public class DataCheckFailedException extends AbstractMemberException  {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -999540096602965636L;
    public DataCheckFailedException(Object ... para ) {
        this.params = para;
    }
    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        return "EMSIF0004";
    }
}