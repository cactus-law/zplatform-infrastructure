/* 
 * ComplexField.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

import java.util.ArrayList;
import java.util.List;

import com.zlebank.zplatform.specification.resolver.attr.ComplexFieldAttrResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 下午4:34:26
 * @since
 */
public class ComplexField extends Field {
    private List<Field> properties = new ArrayList<Field>();
    private String typeClass;
    

    public List<Field> getProperties() {
        return properties;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }
 

    @SuppressWarnings("unchecked")
    public Class<ComplexFieldAttrResolver> getAttrResolverClass() {
        return ComplexFieldAttrResolver.class;
    }
}
