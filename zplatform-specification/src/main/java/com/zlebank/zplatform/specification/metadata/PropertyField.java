/* 
 * Field.java  
 * 
 * version 1.0
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

import com.zlebank.zplatform.specification.resolver.attr.PropertyFieldAttrResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月9日 下午7:10:48
 * @since
 */
public class PropertyField extends Field { 
     
    private Type type;
    private int length; 
    
    public PropertyField() {
        this.optional = false;
        this.nullable = false;
        this.type = Type.ANS;
    } 
    public void setId(String id) {
        this.id = id;
    } 
    public void setName(String name) {
        this.name = name;
    } 
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    @SuppressWarnings("unchecked")
    public Class<PropertyFieldAttrResolver> getAttrResolverClass(){
        return PropertyFieldAttrResolver.class;
    } 
}


