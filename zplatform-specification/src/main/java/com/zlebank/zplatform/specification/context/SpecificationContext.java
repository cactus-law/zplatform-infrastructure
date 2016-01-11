/* 
 * SpecificationContext.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.context;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.metadata.Specification;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 上午9:30:22
 * @since 
 */
public interface SpecificationContext {
    public Specification get(RequestType requestType);
}
