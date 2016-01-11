/* 
 * XMLConfigurationResolver.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.resolver;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.metadata.ComplexField;
import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.FieldTagDefinition;
import com.zlebank.zplatform.specification.metadata.ListField;
import com.zlebank.zplatform.specification.metadata.PropertyField;
import com.zlebank.zplatform.specification.metadata.Specification;
;

/**
 * The class represent a resolver to resolve XML file to a {@link Specification}
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 下午1:24:16
 * @since
 */
public class XMLConfigurationResolver {

    private static final String ELEMENT_REQUEST = "request";
    private static final String ELEMENT_REQPONSE = "response";
    private static final String ELEMENT_HAED = "head";
    private static final String ELEMENT_CONTENT = "content"; 
    private static final String ELEMENT_REQUESTTYPE = "request-type";
    private static final String ELEMENT_VERSION = "version";

    private static final String DEFAULT_VERSION = "1.0";

    public Specification resolve(File xmlFile) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        saxReader.setValidation(true);
        saxReader.setEntityResolver(new ClasspathEntityResolver());
        Document document = saxReader.read(xmlFile);
        Element root = document.getRootElement();
        Element eRequest = root.element(ELEMENT_REQUEST);

        /*
         * resolve request type and version
         */
        Element eHead = eRequest.element(ELEMENT_HAED);
        Element eRequestType = eHead.element(ELEMENT_REQUESTTYPE);
        Element eVersion = eHead.element(ELEMENT_VERSION);
        RequestType requestType = RequestType.formatValue(eRequestType
                .getText());
        if (requestType == RequestType.UNKNOW) {
            throw new RuntimeException(
                    "resolve sepcification xml file error.Caused by: unknow request-type value");
        }
        String version = eVersion.getText();
        if (version == null || !version.equals(DEFAULT_VERSION)) {
            throw new RuntimeException(
                    "resolve sepcification xml file error.Caused by:  the value of supported version is only 1.0 now.");
        }

        Specification specification = Specification.newInstance(requestType,
                version);

        /*
         * resolve request:content
         */
        Element eRequestContent = eRequest.element(ELEMENT_CONTENT);
        @SuppressWarnings("unchecked")
        List<Element> eRequestFields = eRequestContent.elements();
        fillSpecification(specification.getRequestFields(), eRequestFields);

        /*
         * resolve response:content
         */
        Element eResponse = root.element(ELEMENT_REQPONSE);
        Element eResponseContent = eResponse.element(ELEMENT_CONTENT);
        @SuppressWarnings("unchecked")
        List<Element> eResponseFields = eResponseContent.elements();
        fillSpecification(specification.getResponseFields(), eResponseFields);

        return specification;
    }
   
    private void fillSpecification(Map<String,Field> fieldMap, List<Element> elements) {
        //List<Element> dependOtherElements = new ArrayList<Element>();
        //List<Element> listElements = new ArrayList<Element>();
        for (Element element : elements) {
            FieldTagDefinition tag = FieldTagDefinition.formatValue(element
                    .getName());
           /* // cache handler depend element,handler them later.
            // note don't supported depended transmit,so don't make depended
            // transmit in XML file
            Attribute attribute = element.attribute("depend-field");
            if (attribute != null && Boolean.valueOf(attribute.getValue())) {
                dependOtherElements.add(element);
                continue;
            }
            
            
            
          //list element ,ref others ,handler later
            if(tag == FieldTagDefinition.LIST){
                listElements.add(element);
                continue;
            }           */
            resolve(tag,element,fieldMap);
        }/*
        // TODO handler depended transmit
        for (Element element : dependOtherElements) {
            FieldTagDefinition tag = FieldTagDefinition.formatValue(element
                    .getName());
            resolve(tag,element,fieldMap);
        }
        
        for (Element element : listElements) {
            FieldTagDefinition tag = FieldTagDefinition.formatValue(element
                    .getName());
            resolve(tag,element,fieldMap);
        }*/
    }
    
    private void resolve(FieldTagDefinition tag,Element element,Map<String,Field> fieldMap){ 
        Field field = createField(tag);
        FieldAttrResolver fieldAttrResovler = FieldAttrResolverFactory
                .getInstatnce().getAttrResolver(field); 
        field = fieldAttrResovler.resolve(element, field); 
        fieldMap.put(field.getId(), field);
    }

    public static Field createField(FieldTagDefinition tag) {
        Field field;
        switch (tag) {
            case PROPERTY :
                field = new PropertyField();
                break;
            case COMPLEX :
                field = new ComplexField();
                break;
            case LIST :
                field = new ListField();
                break;
            default :
                // TODO
                throw new RuntimeException("");
        }
        return field;
    }

}
