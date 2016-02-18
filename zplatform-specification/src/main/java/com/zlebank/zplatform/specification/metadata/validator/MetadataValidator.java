/* 
 * MetadataValidator.java  
 * 
 * version 1.0
 *
 * 2015年9月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata.validator;

import net.sf.json.JSONObject;

import com.zlebank.zplatform.specification.exception.ValidateException;
import com.zlebank.zplatform.specification.metadata.Field;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月14日 上午10:33:00
 * @since
 */
public interface MetadataValidator {

    /**
     * 
     * @param field
     * @param json
     * @return false if field is optional and couldn't find JSON object in
     *         json,or field can be null and value is null
     * @throws ValidateException
     *             if field is not optional and couldn't find JSON object in
     *             json,or field can not be null but value is null
     */
    public boolean isExistAndNeedVali(Field field, JSONObject json)
            throws ValidateException;
    /**
     * Validate the value.Usually,invoke {@link isExistAndNeedVali} method make
     * sure value is exist and need to be validated
     * 
     * @param field
     * @param json
     * @return
     * @throws ValidateException
     *             if value in JSON is not valid
     */
    public void validate(Field field, JSONObject json) throws ValidateException;
}
