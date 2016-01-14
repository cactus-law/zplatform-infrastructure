/* 
 * MetadataValidatorFactory.java  
 * 
 * version 1.0
 *
 * 2015年9月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.metadata.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.springframework.util.Assert;

import com.zlebank.zplatform.specification.exception.ValidateException;
import com.zlebank.zplatform.specification.metadata.Field;
import com.zlebank.zplatform.specification.metadata.FieldTagDefinition;
import com.zlebank.zplatform.specification.metadata.PropertyField;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月14日 下午1:52:04
 * @since
 */
public class MetadataValidatorFactory {

    private Map<FieldTagDefinition, MetadataValidator> validatorMap;

    private static MetadataValidatorFactory instance;
    public static MetadataValidatorFactory getInstance() {
        if (instance == null) {
            instance = new MetadataValidatorFactory();
            instance.validatorMap = new HashMap<FieldTagDefinition, MetadataValidator>();
        }
        return instance;
    }

    public MetadataValidator getMetadataValidator(FieldTagDefinition fieldTag) {
        MetadataValidator validator = validatorMap.get(fieldTag);
        if (validator == null) {
            validator = createValidator(fieldTag);
            validatorMap.put(fieldTag, validator);
        }
        return validator;
    }

    private MetadataValidator createValidator(FieldTagDefinition fieldTag) {
        MetadataValidator validator = null;
        switch (fieldTag) {
            case PROPERTY :
                validator = new PropertyFieldValidator();
                break;
            case LIST :
                validator = new ListFieldValidator();
                break;
            default :
                validator = new FieldValidator();
        }
        return validator;
    }

    class FieldValidator implements MetadataValidator {

        public boolean isExistAndNeedVali(Field field, JSONObject json)
                throws ValidateException {
            try {
                boolean isNeedGoOn = true;
                String key = field.getName();
                if (field.isOptional() && !json.has(key)) {
                    isNeedGoOn = false;
                }

                if (!field.isOptional()) {
                    Assert.isTrue(json.has(key));
                }

                if (field.isNullable() && (json.get(key) == null ||  json.get(key).equals(""))) {
                    isNeedGoOn = false;
                }

                if (!field.isNullable()) {
                   // if (json.get(key)==null || "".equals(json.get(key)))
                        isNeedGoOn =  true;
                }
                return isNeedGoOn;

            } catch (IllegalArgumentException e) {
                ValidateException ve = new ValidateException();
                ve.setParams(field.getName(),json.get(field.getName()));
                throw ve;
            }
        }
        /**
         *
         * @param field
         * @param json
         */
        @Override
        public void validate(Field field, JSONObject json)
                throws ValidateException {
            if (!validateValue(field, json)) {
                ValidateException validateFailException = new ValidateException();
                validateFailException.setParams(field.getName(), json.get(field.getName()));
                throw validateFailException;
            }
        }

        protected boolean validateValue(Field field, JSONObject json) {
            return true;
        }
    }

    class PropertyFieldValidator extends FieldValidator {
        @Override
        protected boolean validateValue(Field field, JSONObject json) {

            PropertyField _field = (PropertyField) field;
            String value = json.getString(field.getName());

            boolean isValidate = false;

            if (_field.getLength()!=-1&&value.length() > _field.getLength()) {
                isValidate = false;
                return isValidate;
            }
            if (!_field.isNullable() && value.length()<1) {
                isValidate = false;
                return isValidate;
            }

            Pattern p = null;
            boolean isMatch = false;
            switch (_field.getType()) {
                case A :
                    p = Pattern.compile("^[A-Za-z]+$");
                    break;
                case AN :
                    p = Pattern.compile("^[A-Za-z0-9]+$");
                    break;
                case ANS :
                    return true;
                case DATE :
                    // TODO
                    // SimpleDateFormat format = new
                    // SimpleDateFormat("yyyy-MM-dd");
                    return false;
                case TIMESTAMP :
                    // SimpleDateFormat format = new
                    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return false;
                case N :

                    if(_field.getLength()==-1){
                        System.out.println(_field.getName());
                    }
                    p = Pattern.compile("^\\d{0," + _field.getLength() + "}$");
                    break;
                case MONEY :
                    p = Pattern
                            .compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
                    break;
                default :
                    return false;
            }
            Matcher m = p.matcher(value);
            isMatch = m.matches();
            if (isMatch) {
                isValidate = true;
            }
            return isValidate;
        }
    }

    class ListFieldValidator extends FieldValidator {
        @Override
        public boolean validateValue(Field field, JSONObject json) {
            return true;
        }
    }
    class ComplexFieldValidator extends FieldValidator {
        @Override
        public boolean validateValue(Field field, JSONObject json) {
            return true;
        }
    }
}
