/* 
 * Field.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

import com.zlebank.zplatform.specification.resolver.FieldAttrResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 下午4:39:47
 * @since
 */
public abstract class Field {
    protected String id;
    protected String name;
    protected boolean optional;
    protected boolean nullable;
    protected String dependField;
    protected Object dependValue;
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public boolean isOptional() {
        return optional;
    }
    public void setOptional(boolean optional) {
        this.optional = optional;
    }
    public boolean isNullable() {
        return nullable;
    }
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
    public String getDependField() {
        return dependField;
    }
    public void setDependField(String dependField) {
        this.dependField = dependField;
    }
    public Object getDependValue() {
        return dependValue;
    }
    public void setDependValue(Object dependValue) {
        this.dependValue = dependValue;
    }
    public abstract <T extends FieldAttrResolver> Class<T> getAttrResolverClass();
}
