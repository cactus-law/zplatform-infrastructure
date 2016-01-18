/* 
 * PojoAutonymIdenti.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午4:55:58
 * @since 
 */
@Entity
@Table(name="T_AUTONYM_IDENTI")
public class PojoAutonymIdenti {
    /**主键，标识**/
    private Long id;
    /**会员号**/
    private String memberId;
    /**真实姓名**/
    private String realname;
    /**证件类型**/
    private String identiType;
    /**证件号**/
    private String identiNum;
    /**状态，00-有效，01-无效**/
    private String status;
    /**录入时间**/
    private Long inuser;
    /**录入时间**/
    private Date intime;
    /**更新人**/
    private Long upuser;
    /**更新时间**/
    private Date uptime;
    
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "AUTONYM_IDENTI_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name = "REALNAME")
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    @Column(name = "IDENTI_TYPE")
    public String getIdentiType() {
        return identiType;
    }
    public void setIdentiType(String identiType) {
        this.identiType = identiType;
    }
    @Column(name = "IDENTI_NUM")
    public String getIdentiNum() {
        return identiNum;
    }
    public void setIdentiNum(String identiNum) {
        this.identiNum = identiNum;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "UPUSER")
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    @Column(name = "UPTIME")
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
}
