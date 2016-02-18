package com.zlebank.zplatform.acc.exception;

public class InvalidChannelData extends AbstractBusiAcctException {

    public InvalidChannelData(Object ...param) {
        this.params = param;
    }
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5202445586438348105L;

    @Override
    public String getCode() {
        return "EASCN0001";
    }
}
