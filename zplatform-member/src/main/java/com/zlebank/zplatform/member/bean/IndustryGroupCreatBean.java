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

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午10:04:24
 * @since 
 */
public class IndustryGroupCreatBean implements Serializable,Bean {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8092262869089912780L;
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
    
}
