/* 
 * ComplexFieldAttrResolver.java  
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

import com.zlebank.zplatform.specification.metadata.ComplexField;
import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.FieldTagDefinition;
import com.zlebank.zplatform.specification.resolver.FieldAttrResolver;
import com.zlebank.zplatform.specification.resolver.FieldAttrResolverFactory;
import com.zlebank.zplatform.specification.resolver.XMLConfigurationResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午3:02:17
 * @since
 */
public class ComplexFieldAttrResolver extends FieldAttrResolver {

    /**
     *
     * @param element
     * @return
     */
    @Override
    public Field resolve(Element propertyFieldElement, Field filledField) {
        if (!(filledField instanceof ComplexField)) {
            throw new RuntimeException("except field type:"
                    + ComplexField.class + ",but actual field type"
                    + filledField.getClass());
        }
        ComplexField downCastField = (ComplexField) filledField;
        List<java.lang.reflect.Field> jFields = new ArrayList<java.lang.reflect.Field>();
        getAllFields(ComplexField.class, jFields);
        loadAttributes(jFields, downCastField, propertyFieldElement);
        loadProperties(downCastField, propertyFieldElement);
        return downCastField;
    }

    @SuppressWarnings("unchecked")
    private void loadProperties(ComplexField field, Element element) {
        List<Element> propertyElements = element.elements();
        for (Element propertyelement : propertyElements) {
            FieldTagDefinition tag = FieldTagDefinition
                    .formatValue(propertyelement.getName());
            Field propertyField = XMLConfigurationResolver.createField(tag);
            FieldAttrResolver fieldAttrResovler = FieldAttrResolverFactory
                    .getInstatnce().getAttrResolver(propertyField);
            propertyField = fieldAttrResovler.resolve(propertyelement, propertyField);
            field.getProperties().add(propertyField);
        }
    }

    private void loadAttributes(List<java.lang.reflect.Field> jFields,
            Field field,
            Element element) {
        for (java.lang.reflect.Field jField : jFields) {
            if (jField.getName().equals("properties")) {
                continue;
            }
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
