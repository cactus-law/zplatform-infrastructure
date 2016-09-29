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

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午10:04:24
 * @since 
 */
public class InduGroupMemberCreateBean implements Serializable,Bean {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8092262869089912780L;

    
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
     * 加入群组账户标记
     */
    private Usage usage;
    
    private long inuser;


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
    
    
   
}
