package com.zlebank.zplatform.member.exception;

import java.util.ResourceBundle;

import com.zlebank.zplatform.commons.exception.AbstractDescribeException;

public class PrimaykeyGeneratedException extends AbstractDescribeException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1646466665156451742L;
    
    private static final  ResourceBundle RESOURCE = ResourceBundle.getBundle("exception_des");
	@Override
	public ResourceBundle getResourceBundle() {
		return RESOURCE;
	}
    
    @Override
    public String getCode() {
        return "EMS021001";
    }

}
