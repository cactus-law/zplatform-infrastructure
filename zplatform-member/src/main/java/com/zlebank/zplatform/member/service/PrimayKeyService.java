/* 
 * PrimayKeyService.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.exception.MemberBussinessException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:45:15
 * @since 
 */
public interface PrimayKeyService {
    /**
     * 得到序列
     * @param keyName
     * @return
     * @throws MemberBussinessException 
     */
    public String getNexId(String paraType) throws MemberBussinessException;

}
