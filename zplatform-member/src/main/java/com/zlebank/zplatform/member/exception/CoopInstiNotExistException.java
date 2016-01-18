package com.zlebank.zplatform.member.exception;

public class CoopInstiNotExistException extends AbstractCoopInstiException{
    
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1816111259947651277L;

    public CoopInstiNotExistException(Object... params){
        this.params = params;
    }
    
    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return "EMS020005";
    }

}
