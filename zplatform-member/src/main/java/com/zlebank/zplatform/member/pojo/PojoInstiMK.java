package com.zlebank.zplatform.member.pojo;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.zlebank.zplatform.member.bean.enums.EncryptAlgorithm;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
/**
 * 
 * A POJO represent cooperative institution encrypt key pairs
 *
 * @author yangying
 * @version
 * @date 2016年1月13日 上午10:13:45
 * @since
 */
public class PojoInstiMK {
    private long id;
    private PojoCoopInsti coopInsti;
    private TerminalAccessType terminalAccessTyep;
    private EncryptAlgorithm encryptAlgorithm;
    private String localPriKey;
    private String localPubKey;
    private String instiPriKey;
    private String instiPubKey;
    
    @Column(name = "ID")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @ManyToOne
    public PojoCoopInsti getCoopInsti() {
        return coopInsti;
    }
    public void setCoopInsti(PojoCoopInsti coopInsti) {
        this.coopInsti = coopInsti;
    }
    
    @Type(type = "com.zlebank.zplatform.member.bean.enums.TerminalAccessType")
    @Column(name = "TERMINAL_ACCESS_TYPE")
    public TerminalAccessType getTerminalAccessTyep() {
        return terminalAccessTyep;
    }
    public void setTerminalAccessTyep(TerminalAccessType terminalAccessTyep) {
        this.terminalAccessTyep = terminalAccessTyep;
    }
    
    @Type(type = "com.zlebank.zplatform.member.bean.enums.EncryptAlgorithm")
    @Column(name = "ENCRYPT_ALGORITHM")
    public EncryptAlgorithm getEncryptAlgorithm() {
        return encryptAlgorithm;
    }
    public void setEncryptAlgorithm(EncryptAlgorithm encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }
    
    @Column(name = "LOCAL_PRIKEY")
    public String getLocalPriKey() {
        return localPriKey;
    }
    public void setLocalPriKey(String localPriKey) {
        this.localPriKey = localPriKey;
    }
    
    @Column(name = "LOCAL_PUBKEY")
    public String getLocalPubKey() {
        return localPubKey;
    }
    public void setLocalPubKey(String localPubKey) {
        this.localPubKey = localPubKey;
    }
    
    @Column(name = "INSTI_PRIKEY")
    public String getInstiPriKey() {
        return instiPriKey;
    }
    public void setInstiPriKey(String instiPriKey) {
        this.instiPriKey = instiPriKey;
    }
    
    @Column(name = "INSTI_PUBKEY")
    public String getInstiPubKey() {
        return instiPubKey;
    }
    public void setInstiPubKey(String instiPubKey) {
        this.instiPubKey = instiPubKey;
    }
}
