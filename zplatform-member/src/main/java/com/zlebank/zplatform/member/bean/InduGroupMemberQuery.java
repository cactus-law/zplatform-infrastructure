/* 
 * QueryInduGroup.java  
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

import com.zlebank.zplatform.acc.bean.enums.Usage;


/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午10:02:50
 * @since 
 */
public class InduGroupMemberQuery implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3906651344300997175L;
    
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
    private long groupId;
    /**
     * 加入群组时间
     */
    private Date inTime;
    
    
    /**
     * 可用状态
     */
    private String status;
    
    /**
     * 加入群组账户标记
     */
    private Usage usage;

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
     * @return the uniqueTag
     */
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
    public long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
     * @return the usage
     */
    public Usage getUsage() {
        return usage;
    }

    /**
     * @param usage the usage to set
     */
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    
    
}
