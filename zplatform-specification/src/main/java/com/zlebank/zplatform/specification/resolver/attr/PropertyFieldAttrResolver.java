/* 
 * PropertyFieldAttrResolver.java  
 * 
 * version 1.0
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.resolver.attr;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.PropertyField;
import com.zlebank.zplatform.specification.resolver.FieldAttrResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午2:41:57
 * @since
 */
public class PropertyFieldAttrResolver extends FieldAttrResolver {

    /**
     *
     * @param element
     * @return
     */
    @Override
    public Field resolve(Element propertyFieldElement, Field filledField) {
        if (!(filledField instanceof PropertyField)) {
            throw new RuntimeException("except field type:"
                    + PropertyField.class + ",but actual field type"
                    + filledField.getClass());
        }
        PropertyField downCastField = (PropertyField) filledField;
        List<java.lang.reflect.Field> jFields = new ArrayList<java.lang.reflect.Field>();
        getAllFields(PropertyField.class, jFields); 
        loadAttributes(jFields, downCastField, propertyFieldElement); 
        return downCastField;
    }

    private void loadAttributes(List<java.lang.reflect.Field> jFields,
            Field field,
            Element element) {
        for (java.lang.reflect.Field jField : jFields) {
            Attribute attribute = element.attribute(jField.getName());
            try {
                jField.setAccessible(true);
                if (attribute == null) {
                    setDefaultValue(field, jField);
                } else { 
                    setValue(field, jField, attribute.getValue()); 
                }
            } catch (Exception e) {
                e.printStackTrace();
                RuntimeException re = new RuntimeException();
                re.initCause(e);
                throw re;
            }
        }
    }

}
