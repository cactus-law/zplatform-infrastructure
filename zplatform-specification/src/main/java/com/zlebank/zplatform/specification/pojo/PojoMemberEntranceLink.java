/* 
 * PojoMemberEntranceLink.java  
 * 
 * version 1.0
 *
 * 2015年9月16日 
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
import com.zlebank.zplatform.specification.bean.enums.EntranceType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 下午12:03:20
 * @since
 */
@Entity
@Table(name = "T_MEMBER_ENTRANCE_LINK")
public class PojoMemberEntranceLink extends Pojo {

    /**接入类型（'api':接口接入'app':应用程序接入'ms':管理系统接入）**/
    private EntranceType entranceType;
    /**会员号**/
    private String memberId;
    /**所属会员号**/
    private String belongMemberId;
    /**对方的内部会员号**/
    private String entranceMemberId;
    
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "MEMBER_ENTRANCE_LINK_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
    public long getId() {
        return id;
    }

    @Column(name = "ENTRANCE_TYPE")
    @Type(type = "com.zlebank.zplatform.specification.pojo.usertype.EntranceTypeSqlType")
    public EntranceType getEntranceType() {
        return entranceType;
    }
    public void setEntranceType(EntranceType entranceType) {
        this.entranceType = entranceType;
    }
    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name = "BELONG_MEMBER_ID")
    public String getBelongMemberId() {
        return belongMemberId;
    }
    public void setBelongMemberId(String belongMemberId) {
        this.belongMemberId = belongMemberId;
    }
    @Column(name = "ENTRANCE_MEMBER_ID")
    public String getEntranceMemberId() {
        return entranceMemberId;
    }
    public void setEntranceMemberId(String entranceMemberId) {
        this.entranceMemberId = entranceMemberId;
    }

}
