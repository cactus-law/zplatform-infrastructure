/* 
 * TestMemberBean.java  
 * 
 * version TODO
 *
 * 2015年9月2日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月2日 下午5:44:30
 * @since 
 */
public class TestMemberBean implements BusinessActor {
        
    private String businessActorId;
    private String businessActorName;
    private BusinessActorType memberType;
    /**
     *
     * @return
     */
    @Override
    public String getBusinessActorId() {
        
        return businessActorId;
    }
    /**
     *
     * @return
     */
    @Override
    public BusinessActorType getBusinessActorType() {
        return memberType;
    }

    public void setMemberType(BusinessActorType memberType) {
        this.memberType = memberType;
    }
    public String getBusinessActorName() {
        return businessActorName;
    }
    public void setBusinessActorName(String businessActorName) {
        this.businessActorName = businessActorName;
    }
    public BusinessActorType getMemberType() {
        return memberType;
    }
    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }
}
