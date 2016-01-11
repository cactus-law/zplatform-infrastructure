/* 
 * SpecificationParser.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.context.DefaultSpecificationContext;
import com.zlebank.zplatform.specification.context.SpecificationContext;
import com.zlebank.zplatform.specification.exception.SpecificationException;
import com.zlebank.zplatform.specification.exception.ValidateException;
import com.zlebank.zplatform.specification.message.Head;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Request;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.metadata.ComplexField;
import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.FieldTagDefinition;
import com.zlebank.zplatform.specification.metadata.ListField;
import com.zlebank.zplatform.specification.metadata.PropertyField;
import com.zlebank.zplatform.specification.metadata.Specification;
import com.zlebank.zplatform.specification.metadata.validator.MetadataValidator;
import com.zlebank.zplatform.specification.metadata.validator.MetadataValidatorFactory;

/**
 * 
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午9:17:00
 * @since
 */
@Service
public class SpecificationParser {

    private SpecificationContext specificationContext = DefaultSpecificationContext
            .getInstance();

    public Message parse(JSONObject json,
            RequestType requestType,
            boolean isReuqest) throws SpecificationException,
            ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        /*
         * get the field map from SpecificationContext
         */
        Specification specification = specificationContext.get(requestType);
        Message message;
        Map<String, Field> fieldMap;
        if (isReuqest) {
            Request request = new Request();
            Head head = new Head();
            head.setRequestType(requestType);
            head.setVersion("1.0");
            request.setOrginJson(json);
            request.setHead(head);
            json = json.getJSONObject("content");
            message = request;
            fieldMap = Collections.unmodifiableMap(specification
                    .getRequestFields());
        } else {
            message = new Response();
            fieldMap = Collections.unmodifiableMap(specification
                    .getResponseFields());
        }

        /*
         * sort the fields by depend order
         */
        Map<String, Field> sortedByDependFields = new LinkedHashMap<String, Field>();
        sortedField(sortedByDependFields, fieldMap, json);

        /*
         * validate and set in message for each field
         */
        MetadataValidator validator;
        for (Field field : sortedByDependFields.values()) {
            // field can be go on
            if (!checkNeedGoOn(field, json)) {
                continue;
            }
            if (field instanceof PropertyField) {
                validator = MetadataValidatorFactory.getInstance()
                        .getMetadataValidator(FieldTagDefinition.PROPERTY);
                validator.validate(field, json);
                String key = field.getName();
                String value = json.getString(field.getName());
                message.setObject(key, value);
            } else if (field instanceof ListField) {
                // validate list field is valid
                validator = MetadataValidatorFactory.getInstance()
                        .getMetadataValidator(FieldTagDefinition.LIST);
                validator.validate(field, json);
                ListField _field = (ListField) field;

                // refField of list field is exist
                Field refField = fieldMap.get(_field.getFiledRefIds());
                if (refField == null) {
                    throw new RuntimeException("ListField:" + _field.getName()
                            + " has a reference to " + _field.getFiledRefIds()
                            + ".But reference field don't found in field map");
                }

                // refField can be go on
                if (!checkNeedGoOn(refField, json)) {
                    continue;
                }
                @SuppressWarnings("rawtypes")
                List list = handleListElement(_field,
                        json.getJSONObject(_field.getName()));
                message.setObject(field.getName(), list);
            } else if (field instanceof ComplexField) { 
                throw new RuntimeException(
                        "Only support complex-field or property-field for the ref of list-field.Please check xml");
            }
        }
        return message;
    }

    private boolean checkNeedGoOn(Field field, JSONObject json)
            throws ValidateException {
        MetadataValidator validator = MetadataValidatorFactory.getInstance()
                .getMetadataValidator(FieldTagDefinition.UNKONW);
        return validator.isExistAndNeedVali(field, json);
    }

    private void sortedField(Map<String, Field> sortedByDependFields,
            Map<String, Field> fieldMap,
            JSONObject json) {
        for (Field currendField : fieldMap.values()) {
            if (!sortedByDependFields.containsKey(currendField.getName()))
                addField(sortedByDependFields, fieldMap, json, currendField);
        }
    }

    private void addField(Map<String, Field> sortedByDependFields,
            Map<String, Field> fieldMap,
            JSONObject json,
            Field currentField) {
        if (currentField.getDependField() != null) {
            Field dependField = fieldMap.get(currentField.getDependField());
            if (dependField == null) {
                // TODOD dependField don't exist in specification field map
                throw new RuntimeException();
            }
            dependField = sortedByDependFields.get(currentField
                    .getDependField());
            if (dependField == null) {
                // dependField hasn't been added
                addField(sortedByDependFields, fieldMap, json, dependField);
            }
            dependField = sortedByDependFields.get(currentField
                    .getDependField());
            String value = json.getString(dependField.getName());
            if (value != null && value.equals(dependField.getDependValue())) {
                // json value equals the value loaded from xml
                sortedByDependFields.put(currentField.getName(), currentField);
            }
        } else {
            sortedByDependFields.put(currentField.getName(), currentField);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private List handleListElement(Field refField, JSONObject parentFieldJson)
            throws ValidateException {
        MetadataValidator validator;
        List list = null;
        JSONArray jsonArray = parentFieldJson.getJSONArray(refField.getName());
        if (refField instanceof PropertyField) {
            if (!checkNeedGoOn(refField, parentFieldJson)) {
                return list = new ArrayList<String>();
            }
            list = new ArrayList<String>();
            for (int i = 0; i < jsonArray.size(); i++) {
                validator = MetadataValidatorFactory.getInstance()
                        .getMetadataValidator(FieldTagDefinition.PROPERTY);
                validator.validate(refField, parentFieldJson);
                String value = jsonArray.getJSONObject(i).getString(
                        refField.getName());
                list.add(value);
            }
        } else if (refField instanceof ComplexField) {
            if (!checkNeedGoOn(refField, parentFieldJson)) {
                return list = new ArrayList();
            }
            list = new ArrayList();
            validator = MetadataValidatorFactory.getInstance()
                    .getMetadataValidator(FieldTagDefinition.COMPLEX);
            validator.validate(refField, parentFieldJson);
            JSONArray childArray = parentFieldJson.getJSONArray(refField
                    .getName());
            for (int i = 0; i < childArray.size(); i++) {
                try {
                    Class clazz = Class.forName(((ComplexField) refField)
                            .getTypeClass());

                    Object obj = clazz.newInstance();
                    for (Field currentPropertyField : ((ComplexField) refField)
                            .getProperties()) {
                        String value = jsonArray.getJSONObject(i).getString(
                                currentPropertyField.getName());
                        java.lang.reflect.Field jField = clazz
                                .getDeclaredField(currentPropertyField
                                        .getName());
                        jField.set(obj, value);
                    }

                    list.add(obj);
                } catch (Exception e) { 
                    e.printStackTrace();
                    RuntimeException re = new RuntimeException();
                    re.initCause(e);
                    throw re;
                } 
            }

        }
        return list;
    }

    public JSONObject parse(Message message) { 
        if(message instanceof Response){
            JSONObject content = new JSONObject();
            content.put("content", JSONObject.fromObject(message.getContentMap()));
            return content;
        }else if(message instanceof Request){
            JSONObject head = new JSONObject();
            head.put("requestType", ((Request) message).getRequestType().getCode());
            head.put("version", ((Request) message).getVersion()); 
            JSONObject request = new JSONObject();           
            request.put("content", JSONObject.fromObject(message.getContentMap()));
            request.put("head", head);
            return request;
        }else{
            throw new RuntimeException("can't paser message not Response or Request");
        }
        
    }

}
