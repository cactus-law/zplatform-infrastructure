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
     * 群主名称
     */
    private String memberName;
    /**
     * 群主所在机构号
     */
    private String instiCode;
    
    /**
     * 群主所在机构名称
     */
    private String instiName;
    /**
     * 是否可提现到基本账户 0，1
     */
    private String drawable;
    /**
     * 是否可用0，1
     */
    private String useable;
    /**
     * 创建人主键
     */
    private long inuser;
    /**
     * 备注
     */
    private String note;
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
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }
    /**
     * @param memberName the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
     * @return the instiName
     */
    public String getInstiName() {
        return instiName;
    }
    /**
     * @param instiName the instiName to set
     */
    public void setInstiName(String instiName) {
        this.instiName = instiName;
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
     * @return the useable
     */
    public String getUseable() {
        return useable;
    }
    /**
     * @param useable the useable to set
     */
    public void setUseable(String useable) {
        this.useable = useable;
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
