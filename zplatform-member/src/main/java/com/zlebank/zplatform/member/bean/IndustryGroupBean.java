/* 
 * IndustryGroupBean.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.bean;

import java.io.Serializable;
import java.util.Date;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午10:04:24
 * @since 
 */
public class IndustryGroupBean implements Serializable,Bean {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8092262869089912780L;

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
    private String status;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the inuser
     */
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
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**
     * @return the inTime
     */
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
    public Date getUpTime() {
        return upTime;
    }
    /**
     * @param upTime the upTime to set
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
    
    
}
