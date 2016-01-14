package com.zlebank.zplatform.member.exception;

public class OpenCoopInstiBusiAcctException extends AbstractCoopInstiException{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4798382357024594439L;

    public OpenCoopInstiBusiAcctException(Object... params){
        this.params = params;
    }
    
    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return "EMS020004";
    }

}
