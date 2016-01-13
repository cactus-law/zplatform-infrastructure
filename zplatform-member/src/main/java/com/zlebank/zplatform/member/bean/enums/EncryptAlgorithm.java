package com.zlebank.zplatform.member.bean.enums;

public enum EncryptAlgorithm {
    RSA("RSA"), UNKNOW("");
    private String algName;
    private EncryptAlgorithm(String algName) {
        this.algName = algName;
    }

    public String getAlgName() {
        return algName;
    }

    public static EncryptAlgorithm fromValue(String algName) {
        for (EncryptAlgorithm encryptAlgorithm : EncryptAlgorithm.values()) {
            if (encryptAlgorithm.getAlgName() == algName) {
                return encryptAlgorithm;
            }
        }
        return UNKNOW;
    }
}
