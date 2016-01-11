/* 
 * FeePojo.java  
 * 
 * version TODO
 *
 * 2015年11月3日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo;

import java.util.Date;

import javax.persistence.Column;

/**
 * 扣率pojo
 *
 * @author yangpeng
 * @version
 * @date 2015年11月3日 下午6:02:00
 * @since 
 */
public class FeePojo {
    
    /**标识**/
    private Long feeid;
    /**产品版本**/
    private String prdtver;
    /**扣率版本**/
    private String feever;
    /**扣率名称**/
    private String feename;
    /**状态00在用其他停用**/
    private String status;
    /**写入时间**/
    private Date intime;
    /**写入人**/
    private Long inuser;
    /**更新时间**/
    private Date uptime;
    /**更新人**/
    private Long upuser;
    /**更新标记**/
    private String initflag;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    @Column(name = "FEEID")
    public Long getFeeid() {
        return feeid;
    }
    public void setFeeid(Long feeid) {
        this.feeid = feeid;
    }
    @Column(name = "PRDTVER")
    public String getPrdtver() {
        return prdtver;
    }
    public void setPrdtver(String prdtver) {
        this.prdtver = prdtver;
    }
    @Column(name = "FEEVER")
    public String getFeever() {
        return feever;
    }
    public void setFeever(String feever) {
        this.feever = feever;
    }
    @Column(name = "FEENAME")
    public String getFeename() {
        return feename;
    }
    public void setFeename(String feename) {
        this.feename = feename;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "UPTIME")
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    @Column(name = "UPUSER")
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    @Column(name = "INITFLAG")
    public String getInitflag() {
        return initflag;
    }
    public void setInitflag(String initflag) {
        this.initflag = initflag;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
