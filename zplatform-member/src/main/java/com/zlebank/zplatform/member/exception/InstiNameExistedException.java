package com.zlebank.zplatform.member.exception;

public class InstiNameExistedException extends CoopInstiException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6264070864150327441L;

    @Override
    public String getCode() {
        return "EMS020002";
    }
}
