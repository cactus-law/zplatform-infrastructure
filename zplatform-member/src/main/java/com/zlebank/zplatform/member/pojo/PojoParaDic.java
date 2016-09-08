/* 
 * PrimayKey.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:08:00
 * @since
 */
@Entity
    @Table(name="T_PARA_DIC")
    public class PojoParaDic implements Serializable{
    private Long tId;
    private String paraCode;
    private Long parentId;
    private String paraName;
    private String paraType;
    private int hasSub;
    private int status;
    private Long added;
    private String remaRks;
    @Id
    @Column(name = "TID")
    public Long gettId() {
        return tId;
    }
    @Column(name = "PARA_CODE")
    public String getParaCode() {
        return paraCode;
    }
    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }
    @Column(name = "PARA_NAME")
    public String getParaName() {
        return paraName;
    }
    @Column(name = "PARA_TYPE")
    public String getParaType() {
        return paraType;
    }
    @Column(name = "HAS_SUB")
    public int getHasSub() {
        return hasSub;
    }
    @Column(name = "STATUS")
    public int getStatus() {
        return status;
    }
    @Column(name = "ADDED")
    public Long getAdded() {
        return added;
    }
    @Column(name = "REMARKS")
    public String getRemaRks() {
        return remaRks;
    }
    public void settId(Long tId) {
        this.tId = tId;
    }
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public void setParaName(String paraName) {
        this.paraName = paraName;
    }
    public void setParaType(String paraType) {
        this.paraType = paraType;
    }
    public void setHasSub(int hasSub) {
        this.hasSub = hasSub;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setAdded(Long added) {
        this.added = added;
    }
    public void setRemaRks(String remaRks) {
        this.remaRks = remaRks;
    }

}
