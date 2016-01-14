package com.zlebank.zplatform.member.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
@Entity
@Table(name="T_COOP_INSTI_MK")
public class PojoInstiMK {
    private long id;
    private PojoCoopInsti coopInsti;
    private TerminalAccessType terminalAccessType;
    private EncryptAlgorithm encryptAlgorithm;
    private String zplatformPriKey;
    private String zplatformPubKey;
    private String instiPriKey;
    private String instiPubKey;
    
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "COOP_INSTI_MK_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @ManyToOne
    @JoinColumn(name="COOP_INSTI_ID")
    public PojoCoopInsti getCoopInsti() {
        return coopInsti;
    }
    public void setCoopInsti(PojoCoopInsti coopInsti) {
        this.coopInsti = coopInsti;
    }
    
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.TerminalAccessTypeSqlType")
    @Column(name = "TERMINAL_ACCESS_TYPE",length=2,nullable=false)
    public TerminalAccessType getTerminalAccessType() {
        return terminalAccessType;
    }
    public void setTerminalAccessType(TerminalAccessType terminalAccessType) {
        this.terminalAccessType = terminalAccessType;
    }
    
    @Type(type = "com.zlebank.zplatform.member.pojo.usertype.EncryptAlgorithmSqlType")
    @Column(name = "ENCRYPT_ALGORITHM",length=32)
    public EncryptAlgorithm getEncryptAlgorithm() {
        return encryptAlgorithm;
    }
    public void setEncryptAlgorithm(EncryptAlgorithm encryptAlgorithm) {
        this.encryptAlgorithm = encryptAlgorithm;
    }
    
    @Column(name = "ZPLATFORM_PRIKEY",length=1024)
    public String getZplatformPriKey() {
        return zplatformPriKey;
    }
    public void setZplatformPriKey(String zplatformPriKey) {
        this.zplatformPriKey = zplatformPriKey;
    }
    
    @Column(name = "ZPLATFORM_PUBKEY",length=2048)
    public String getZplatformPubKey() {
        return zplatformPubKey;
    }
    public void setZplatformPubKey(String zplatformPubKey) {
        this.zplatformPubKey = zplatformPubKey;
    }
    
    @Column(name = "INSTI_PRIKEY",length=1024)
    public String getInstiPriKey() {
        return instiPriKey;
    }
    
    public void setInstiPriKey(String instiPriKey) {
        this.instiPriKey = instiPriKey;
    }
    
    @Column(name = "INSTI_PUBKEY",length=2048)
    public String getInstiPubKey() {
        return instiPubKey;
    }
    public void setInstiPubKey(String instiPubKey) {
        this.instiPubKey = instiPubKey;
    }
}
