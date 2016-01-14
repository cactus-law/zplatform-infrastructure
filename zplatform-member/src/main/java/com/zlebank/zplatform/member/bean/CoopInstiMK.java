package com.zlebank.zplatform.member.bean;

import com.zlebank.zplatform.member.bean.enums.EncryptAlgorithm;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;

/**
 * 
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2016年1月13日 下午4:43:12
 * @since
 */
public class CoopInstiMK {

    private String coopInstiCode;
    private EncryptAlgorithm encryptAlgorithm;
    private TerminalAccessType terminalAccessType;
    private String zplatformPriKey;
    private String zplatformPubKey;
    private String instiPriKey;
    private String instiPubKey;

    public String getCoopInstiCode() {
        return coopInstiCode;
    }
    public EncryptAlgorithm getEncryptAlgorithm() {
        return encryptAlgorithm;
    }
    public TerminalAccessType getTerminalAccessType() {
        return terminalAccessType;
    }
    public String getZplatformPriKey() {
        return zplatformPriKey;
    }
    public String getZplatformPubKey() {
        return zplatformPubKey;
    }
    public String getInstiPriKey() {
        return instiPriKey;
    }
    public String getInstiPubKey() {
        return instiPubKey;
    }
}
