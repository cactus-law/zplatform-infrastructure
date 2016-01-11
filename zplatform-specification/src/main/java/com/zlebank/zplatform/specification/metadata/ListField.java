/* 
i * List.java  
 * 
 * version 1.0
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

import com.zlebank.zplatform.specification.resolver.attr.ListFieldAttrResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午2:11:18
 * @since
 */
public class ListField extends Field {
    private String filedRefIds; 

    public String getFiledRefIds() {
        return filedRefIds;
    }
    public void setFiledRefIds(String filedRefIds) {
        this.filedRefIds = filedRefIds;
    } 

    @SuppressWarnings("unchecked")
    public Class<ListFieldAttrResolver> getAttrResolverClass() {
        return ListFieldAttrResolver.class;
    }
}
