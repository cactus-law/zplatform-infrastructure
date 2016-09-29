/* 
 * PojoIndustryGroup.java  
 * 
 * version TODO
 *
 * 2016年9月27日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.CommonStatus;

/**
 * 行业群组
 *
 * @author houyong
 * @version
 * @date 2016年9月27日 下午5:39:40
 * @since 
 */
@Entity
@Table(name="T_INDUSTRY_GROUP")
public class PojoIndustryGroup implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2162380763698513991L;
    /**
     * 主键
     */
    private long id;
    /**
     * 群组代码
     */
    private String groupCode;
    /**
     * 群组名称
     */
    private String groupName;
    /**
     * 群组会员号
     */
    private String memberId;
    /**
     * 群主所在机构号
     */
    private String instiCode;
    
    /**
     * 是否可提现到基本账户 0，1
     */
    private String drawable;
    /**
     * 是否可用0，1
     */
    private CommonStatus status;
    /**
     * 创建人主键
     */
    private long inuser;
    /**
     * 备注
     */
    private String note;
    /**
     * 备注2
     */
    private String remarks;
    /**
     * 创建时间
     */
    private Date inTime;
    /**
     * 更新时间
     */
    private Date upTime;
    
    
    
    /**
     * @return the id
     */
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "industry_group__id"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo")})
    @Id
    @GeneratedValue(generator = "id_gen")
    @Column(name="id" ,nullable=false,unique=true)
    public long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return the groupCode
     */
    @Column(name="group_code")
    public String getGroupCode() {
        return groupCode;
    }
    /**
     * @param groupCode the groupCode to set
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    /**
     * @return the groupName
     */
    @Column(name="group_name")
    public String getGroupName() {
        return groupName;
    }
    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * @return the memberId
     */
    @Column(name="member_id")
    public String getMemberId() {
        return memberId;
    }
    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    /**
     * @return the instiCode
     */
    @Column(name="insti_code")
    public String getInstiCode() {
        return instiCode;
    }
    /**
     * @param instiCode the instiCode to set
     */
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    
    /**
     * @return the drawable
     */
    @Column(name="drawable")
    public String getDrawable() {
        return drawable;
    }
    /**
     * @param drawable the drawable to set
     */
    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }
    /**
     * @return the inuser
     */
    @Column(name="inuser")
    public long getInuser() {
        return inuser;
    }
    /**
     * @param inuser the inuser to set
     */
    public void setInuser(long inuser) {
        this.inuser = inuser;
    }
    /**
     * @return the note
     */
    @Column(name="note")
    public String getNote() {
        return note;
    }
    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
    /**
     * @return the inTime
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="intime")
    public Date getInTime() {
        return inTime;
    }
    /**
     * @param inTime the inTime to set
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    /**
     * @return the upTime
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="uptime")
    public Date getUpTime() {
        return upTime;
    }
    /**
     * @param upTime the upTime to set
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
    /**
     * @return the status
     */
    @Column(name="status")
    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.CommonStatusSqlType")
    public CommonStatus getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(CommonStatus status) {
        this.status = status;
    }
    /**
     * @return the remarks
     */
    @Column(name="remarks")
    public String getRemarks() {
        return remarks;
    }
    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}
