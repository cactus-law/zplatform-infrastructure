/* 
 * PojoEntranceAPILog.java  
 * 
 * version 1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.commons.dao.pojo.Pojo;
import com.zlebank.zplatform.specification.RequestType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 下午12:06:48
 * @since
 */
@Entity
@Table(name = "T_ENTRANCE_API_LOG")
public class PojoEntranceAPILog extends Pojo {

    /**序列号yyMMdd24hhmmss0001**/
    private String seqNo;
    /**请求类型**/
    private RequestType requestType;
    /**请求时间**/
    private Date requestTime;
    /**响应时间**/
    private Date responseTime;
    /**MAC**/
    private String mac;
    /**响应代码**/
    private String resCode;
    /**响应描述**/
    private String resDes;
    /**事务id**/
    private String sessionId;
    /**会员号**/
    private String memberId;
    /**版本号**/
    private String version;

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "ENTRANCE_API_LOG_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }

    @Column(name = "SEQ_NO")
    public String getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    @Column(name = "REQUEST_TYPE")
    @Type(type = "com.zlebank.zplatform.specification.pojo.usertype.RequestSqlType")
    public RequestType getRequestType() {
        return requestType;
    }
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }
    @Column(name = "REQUEST_TIME")
    public Date getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
    @Column(name = "RESPONSE_TIME")
    public Date getResponseTime() {
        return responseTime;
    }
    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
    @Column(name = "MAC")
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    @Column(name = "RES_CODE")
    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    @Column(name = "RES_DES")
    public String getResDes() {
        return resDes;
    }
    public void setResDes(String resDes) {
        this.resDes = resDes;
    }
    @Column(name = "SESSION_ID")
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
