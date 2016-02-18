/* 
 * PropertyFieldAttr.java  
 * 
 * version 1.0
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.resolver;

import java.util.Arrays;
import java.util.List;

import org.dom4j.Element;

import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.Type;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午2:40:27
 * @since
 */
public abstract class FieldAttrResolver {
    public abstract Field resolve(Element element, Field filledField);

    protected void setValue(Field field,
            java.lang.reflect.Field jField,
            String value) throws IllegalArgumentException,
            IllegalAccessException, SecurityException, NoSuchFieldException {
        if (jField.getType().equals(Type.class)) {
            jField.set(field, Type.formatValue(value));
        }
        if (jField.getType().equals(Field.class)) {
            jField.set(field, Type.formatValue(value));
        }
        if (jField.getType() == boolean.class) {
            jField.set(field, Boolean.valueOf(value));
        }
        if (jField.getType() == long.class) {
            jField.set(field, Long.valueOf(value));
        }
        if (jField.getType() == int.class) {
            jField.set(field, Integer.valueOf(value));
        }
        if (jField.getType() == String.class) {
            jField.set(field, value);
        }
    }
    protected void setDefaultValue(Field field, java.lang.reflect.Field jField)
            throws IllegalArgumentException, IllegalAccessException {
        if (jField.getType().equals(Type.class)) {
            jField.set(field, Type.ANS);
        }
        if (jField.getType().equals(String.class)) {
            jField.set(field, null);
        }
        if (jField.getType() == boolean.class) {
            jField.set(field, false);
        }
        if (jField.getType() == long.class) {
            jField.set(field, -1L);
        }
        if (jField.getType() == int.class) {
            jField.set(field, -1);
        }
    }

    protected void getAllFields(Class<?> clazz,
            List<java.lang.reflect.Field> fields) {
        if (clazz.getSuperclass() != null) {
            getAllFields(clazz.getSuperclass(), fields);
        }
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
    }
}
