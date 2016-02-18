/* 
 * ClasspathEntityResolver.java  
 * 
 * version 1.0
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.resolver;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.zlebank.zplatform.specification.context.SpecificationContextLoader;

/**
 * Implementation of <code>org.xml.sax.EntityResolver</code> that loads
 * entitities (for example dtd files) from the classpath.
 * 
 * @author yangying
 * @version
 * @date 2015年9月10日 下午1:32:24
 * @since
 */
public class ClasspathEntityResolver implements EntityResolver {
    public InputSource resolveEntity(String publicId, String systemId)
            throws SAXException, IOException {

        if (systemId != null) {
            int index = systemId.lastIndexOf('/');
            if (index != -1) {
                systemId = systemId.substring(index + 1);
            }
            systemId = SpecificationContextLoader.ROOT_PATH+"/"+ systemId;
            InputStream istr = getClass().getResourceAsStream(systemId);
            if (istr != null) {
                return new InputSource(istr);
            }
        }
        return null;
    }
}
