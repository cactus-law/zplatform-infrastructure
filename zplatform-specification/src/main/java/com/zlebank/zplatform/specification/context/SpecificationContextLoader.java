/* 
 * SpecificationContextLoader.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.context;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

import org.dom4j.DocumentException;

import com.zlebank.zplatform.specification.metadata.Specification;
import com.zlebank.zplatform.specification.resolver.XMLConfigurationResolver;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月10日 上午9:27:28
 * @since
 */
public class SpecificationContextLoader {

    private SpecificationContext specificationContext;
    public final static String ROOT_PATH = "/specification";
    private final static String FILE_SUFFIX = ".xml";

    private static SpecificationContextLoader instance;

    private SpecificationContextLoader() {
    }

    public static SpecificationContextLoader getInstance() {
        return getInstance(null);
    }
    /**
     * only support {@link DefaultSpecificationContext},so this method has a
     * modifier {@code private}.Consider support for user customize
     * {@link SpecificationContext} in later version.
     * 
     * @param specificationContext
     * @return
     */
    private static SpecificationContextLoader getInstance(SpecificationContext specificationContext) {
        if (instance == null) {
            instance = new SpecificationContextLoader();
            if (specificationContext == null) {
                instance.specificationContext = DefaultSpecificationContext
                        .getInstance();
            } else {
                instance.specificationContext = specificationContext;
            }
        }
        return instance;
    }
    
    public SpecificationContext getSpecificationContext(){
        return specificationContext;
    }
    /*
     * decide specificationContext only support for DefaultSpecificationContext
     */
    private DefaultSpecificationContext beforeLoad() {
        DefaultSpecificationContext _specificationContext = null;
        if (specificationContext instanceof DefaultSpecificationContext) {
            _specificationContext = (DefaultSpecificationContext) specificationContext;
        } else {
            throw new UnsupportedOperationException(
                    "Only support SpecificationContext type:"
                            + DefaultSpecificationContext.class.getName());
        }
        return _specificationContext;
    }
    /**
     * Load file to Specification from class path dir:specificatins
     */
    public void load() {
        File[] files = fetchFiles(ROOT_PATH);
        // for add operation ,cast SpecificationContext to
        // DefaultSpecificationContext
        DefaultSpecificationContext realContext = beforeLoad();

        for (File resource : files) {
            Specification specification = resolve(resource);
            realContext.add(specification);
        }
    }
    /**
     * Reload file to Specification from class path dir:specificatins.After
     * that,add to SpecificationContext if key not exist,replace if key is exist
     */
    public void reLoad() {
        File[] files = fetchFiles(ROOT_PATH);
        // for add and replace operation ,cast SpecificationContext to
        // DefaultSpecificationContext
        DefaultSpecificationContext realContext = beforeLoad();

        for (File resource : files) {
            Specification specification = resolve(resource);

            if (specificationContext.get(specification.getRequestType()) == null) {
                realContext.add(specification);
                continue;
            }
            realContext.replace(specification.getRequestType(), specification);
        }
    }

    private Specification resolve(File resource) {
        XMLConfigurationResolver resolver = new XMLConfigurationResolver();
        try {
            return resolver.resolve(resource);
        } catch (DocumentException e) {
            RuntimeException re = new RuntimeException("resolve xml file exception.");
            re.initCause(e);
            throw re;
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
                    "no specification definition file under classpath: "
                            + path);
        }
        return xmlFiles;
    }
}
