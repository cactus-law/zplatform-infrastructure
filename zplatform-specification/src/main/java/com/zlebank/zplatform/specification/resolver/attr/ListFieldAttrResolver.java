/* 
 * ListFieldAttrResolver.java  
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
import com.zlebank.zplatform.specification.metadata.ListField;
import com.zlebank.zplatform.specification.resolver.FieldAttrResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月13日 下午2:58:38
 * @since
 */
public class ListFieldAttrResolver extends FieldAttrResolver {

    /**
     *
     * @param element
     * @param filledField
     * @return
     */
    @Override
    public Field resolve(Element element, Field filledField) {
        if (!(filledField instanceof ListField)) {
            throw new RuntimeException("except field type:"
                    + ListField.class + ",but actual field type"
                    + filledField.getClass());
        }
        ListField downCastField = (ListField) filledField;
        List<java.lang.reflect.Field> jFields = new ArrayList<java.lang.reflect.Field>();
        getAllFields(ListField.class, jFields);

        loadAttributes(jFields, downCastField, element);
        return downCastField;
    }

    private void loadAttributes(List<java.lang.reflect.Field> jFields,
            Field field,
            Element element) {
        for (java.lang.reflect.Field jField : jFields) {
            try {
                if (jField.getName().equals("filedRefIds")) {
                    jField.setAccessible(true);
                    setValue(field, jField, element.element("ref").attribute("value")
                            .getValue());
                    continue;
                }
                Attribute attribute = element.attribute(jField.getName());
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
