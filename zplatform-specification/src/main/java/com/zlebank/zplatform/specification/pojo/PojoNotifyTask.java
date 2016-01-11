/* 
 * PojoSpecificationNotifyTask.java  
 * 
 * version 1.0
 *
 * 2015年9月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.commons.dao.pojo.Pojo;
import com.zlebank.zplatform.specification.bean.enums.SendStatus;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月18日 下午4:37:10
 * @since
 */
@Entity
@Table(name="T_SPEC_NOTIFY_TASK")
public class PojoNotifyTask extends Pojo{
 
    private String memberId;
    private String srcSessionId;

    private int sendTimes;
    private int maxSendTimes;
    private String data;
    private SendStatus sendStatus;
    private String httpResCode; 
    private String loadIP;
    
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "NOTIFY_TASK_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    } 
    @Column(name="MEMBER_ID",length=15)
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    @Column(name="SRCSESSION_ID")
    public String getSrcSessionId() {
        return srcSessionId;
    }
    public void setSrcSessionId(String srcSessionId) {
        this.srcSessionId = srcSessionId;
    }
    
    @Column(name="SEND_TIMES")
    public int getSendTimes() {
        return sendTimes;
    }
    public void setSendTimes(int sendTimes) {
        this.sendTimes = sendTimes;
    }
    
    @Column(name="MAX_SEND_TIMES")
    public int getMaxSendTimes() {
        return maxSendTimes;
    }
    public void setMaxSendTimes(int maxSendTimes) {
        this.maxSendTimes = maxSendTimes;
    }
    
    @Column(name="DATA")
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    @Type(type="com.zlebank.zplatform.specification.pojo.usertype.SendStatusSqlType")
    @Column(name="SEND_STATUS")
    public SendStatus getSendStatus() {
        return sendStatus;
    }
    public void setSendStatus(SendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }
    
    @Column(name="HTTP_RESCODE")
    public String getHttpResCode() {
        return httpResCode;
    }
    public void setHttpResCode(String httpResCode) {
        this.httpResCode = httpResCode;
    }
    @Column(name="LOAD_IP")    
    public String getLoadIP() {
        return loadIP;
    }
    public void setLoadIP(String loadIP) {
        this.loadIP = loadIP;
    }

}
