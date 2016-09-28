/* 
 * PojoIndustryGroupMember.java  
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

/**
 * 行业群组<>会员关系
 *
 * @author houyong
 * @version
 * @date 2016年9月27日 下午5:40:25
 * @since 
 */
@Entity
@Table(name="t_industry_group_member")
public class PojoIndustryGroupMember implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7309867728596756540L;
    
    private long id;
    /**
     * 唯一标记-作为业务账户参与方
     */
    private String uniqueTag;
    /**
     * 群组会员号
     */
    private String memberId;
    /**
     * 群组代码
     */
    private String groupCode;
    /**
     * 群组主键
     */
    private String groupId;
    /**
     * 加入群组时间
     */
    private Date inTime;
    
    
    
    /**
     * @return the id
     */
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "industry_group_member_id"),
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
     * @return the uniqueTag
     */
    @Column(name="unique_tag")
    public String getUniqueTag() {
        return uniqueTag;
    }
    /**
     * @param uniqueTag the uniqueTag to set
     */
    public void setUniqueTag(String uniqueTag) {
        this.uniqueTag = uniqueTag;
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
     * @return the groupId
     */
    @Column(name="group_id")
    public String getGroupId() {
        return groupId;
    }
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
    
    
    
}
