/* 
 * Response.java  
 * 
 * version 1.0
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.message;




/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月11日 上午9:18:29
 * @since 
 */
public class Response extends RawMessage{ 
    
    private final static String FIELD_RESCODE = "rescode";
    private final static String FIELD_RESDES = "resdes";
    
    public String getResCode() { 
        return contentMap.get(FIELD_RESCODE) == null ? null : contentMap.get(FIELD_RESCODE).toString();
    }
    
    public String getResDes() { 
        if(contentMap.containsKey(FIELD_RESDES)){
            return (String)contentMap.get(FIELD_RESDES);
        }
        return null;
    }
    
    public void setResCode(String resCode) { 
        contentMap.put(FIELD_RESCODE, resCode);
    }
    
    public void setResDes(Object... param) { 
        contentMap.put(FIELD_RESDES,ResponseDescription.getMessage(getResCode() , param));
    }
    
}
