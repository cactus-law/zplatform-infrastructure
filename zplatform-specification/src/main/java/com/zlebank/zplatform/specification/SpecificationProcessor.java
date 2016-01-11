/* 
 * SpecificationProcessor.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午9:59:00
 * @since 
 */
public interface SpecificationProcessor {
    
    public Response process(Message request);
}
