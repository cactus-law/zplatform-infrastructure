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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.metadata.Specification;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 上午9:14:02
 * @since
 */
public class DefaultSpecificationContext implements SpecificationContext {

    private static DefaultSpecificationContext instance;

    private ConcurrentMap<RequestType, Specification> specifications;

    private DefaultSpecificationContext() {
    }

    public static DefaultSpecificationContext getInstance() {
        if (instance == null) {
            instance = new DefaultSpecificationContext();
            instance.specifications = new ConcurrentHashMap<RequestType, Specification>();
        }
        return instance;
    }

    public void add(Specification specification) {
        if (specification.getRequestType() == null
                || specification.getRequestType() == RequestType.UNKNOW) {
            throw new RuntimeException(
                    "add specification to SpecificationContext.Because RequestType is"
                            + specification.getRequestType());
        }
        specifications.putIfAbsent(specification.getRequestType(),
                specification);
    }

    public void replace(RequestType requestType, Specification specification) {
        specifications.replace(requestType, specification);
    }

    public Specification get(RequestType requestType) {
        return specifications.get(requestType);
    }

}
