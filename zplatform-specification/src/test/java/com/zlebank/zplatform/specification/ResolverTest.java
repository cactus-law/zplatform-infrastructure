/* 
 * ResolverTest.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

import org.dom4j.DocumentException;
import org.junit.Test;

import com.zlebank.zplatform.specification.metadata.ComplexField;
import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.ListField;
import com.zlebank.zplatform.specification.metadata.PropertyField;
import com.zlebank.zplatform.specification.metadata.Specification;
import com.zlebank.zplatform.specification.resolver.XMLConfigurationResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 下午3:53:41
 * @since
 */
public class ResolverTest {
    private final static String FILE_SUFFIX = ".xml";
    @Test
    public void test() {
        File[] files = fetchFiles("/specification");
        XMLConfigurationResolver resolver = new XMLConfigurationResolver();
        for (File file : files) {
            try {
                Specification specification = resolver.resolve(file);
                System.out.println("request type: "
                        + specification.getRequestType());
                System.out.println(specification.getVersion());
                System.out.println(specification.getRequestFields().entrySet()
                        .size());
                System.out.println(specification.getResponseFields().entrySet()
                        .size());
                System.out.println("------request---");
                for (Field field : specification.getRequestFields().values()) {                    
                    printField(field); 
                    System.out.println();
                }
                System.out.println("-----response----");
                for (Field field : specification.getResponseFields().values()) {
                   
                    printField(field);
                    System.out.println();
                }
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private void printField(Field field) throws IllegalArgumentException,
            IllegalAccessException {
        System.out.println(field.getId());
        if (field instanceof PropertyField) {
            for (java.lang.reflect.Field jField : PropertyField.class
                    .getDeclaredFields()) {
                jField.setAccessible(true);
                System.out.println(jField.getName() + ":" + jField.get(field));
            }
        } else if (field instanceof ComplexField) {
            System.out.println(field.getClass().getName());
            ComplexField complexField = (ComplexField) field;
            for (java.lang.reflect.Field jField : ComplexField.class
                    .getDeclaredFields()) {
                jField.setAccessible(true);
                System.out.println(jField.getName() + ":" + jField.get(field));
            }

            for (Field childField : complexField.getProperties()) {
                printField(childField);
            }
        } else if (field instanceof ListField) {
            System.out.println(field.getClass().getName());
            ListField listField = (ListField) field;
            for (java.lang.reflect.Field jField : ListField.class
                    .getDeclaredFields()) {
                jField.setAccessible(true);
                System.out.println(jField.getName() + ":" + jField.get(field));
            }

            System.out.println("refIds" + ":" + listField.getFiledRefIds());

        }
    }

    /**
     * resource file must under {@link ROOT_PATH},and is a XML file
     * 
     * @param path
     * @return
     */
    private File[] fetchFiles(String path) {
        URL url = getClass().getResource(path);
        File file = new File(url.getFile());
        File[] xmlFiles = null;
        if (file.isDirectory()) {
            xmlFiles = file.listFiles(new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith(FILE_SUFFIX)) {
                        return true;
                    }
                    return false;
                }
            });
        }

        if (xmlFiles == null) {
            throw new RuntimeException(
                    "no specification definition file under classpath: " + path);
        }
        return xmlFiles;
    }
}
