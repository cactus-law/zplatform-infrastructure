/* 
 * FieldTypeDeifnition.java  
 * 
 * version 1.0
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午2:33:12
 * @since 
 */
public enum FieldTagDefinition {
    COMPLEX("complex-field"),LIST("list-field"),PROPERTY("property-field"),
    /**
     * not a filed tag
     */
    UNKONW("NAFT");
    private String code;
    private FieldTagDefinition(String code){
        this.code = code;
    }
    
    public static FieldTagDefinition formatValue(String code){
        for(FieldTagDefinition fieldTagDef:values()){
            if(fieldTagDef.code.equals(code)){
                return fieldTagDef;
            }
        }
        return UNKONW;
    }
}
