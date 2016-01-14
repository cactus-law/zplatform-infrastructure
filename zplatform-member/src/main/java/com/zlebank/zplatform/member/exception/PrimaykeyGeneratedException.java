package com.zlebank.zplatform.member.exception;

import com.zlebank.zplatform.commons.exception.AbstractDescribeException;

public class PrimaykeyGeneratedException extends AbstractDescribeException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1646466665156451742L;

    @Override
    public String getCode() {
        return "EMS02101";
    }

}
