/* 
 * PojoMemberEntranceInfo.java  
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
import com.zlebank.zplatform.specification.bean.enums.EntranceStatus;

/**
 * 商户接入信息
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 上午10:26:48
 * @since
 */
@Entity
@Table(name = "T_MEMBER_ENTRANCE_INFO")
public class PojoMemberEntranceInfo extends Pojo {

    /**会员号**/
    private String memberId;
    /**接入状态（01:初始化00:已签到）**/
    private EntranceStatus entranceStatus;
    /**最后签到时间**/
    private Date lastSignTime;
    /**签到统计**/
    private Long signCount;
    /**回调URL**/
    private String notifyUrl;

    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "MEMBER_ENTRANCE_INFO_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }

    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name = "ENTRANCE_STATUS")
    @Type(type = "com.zlebank.zplatform.specification.pojo.usertype.EntranceStatusSqlType")
    public EntranceStatus getEntranceStatus() {
        return entranceStatus;
    }
    public void setEntranceStatus(EntranceStatus entranceStatus) {
        this.entranceStatus = entranceStatus;
    }
    @Column(name = "LAST_SIGN_TIME")
    public Date getLastSignTime() {
        return lastSignTime;
    }
    public void setLastSignTime(Date lastSignTime) {
        this.lastSignTime = lastSignTime;
    }
    @Column(name = "SIGN_COUNT")
    public Long getSignCount() {
        return signCount;
    }
    public void setSignCount(Long signCount) {
        this.signCount = signCount;
    }
    @Column(name = "NOTIFY_URL")
    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
 
}
