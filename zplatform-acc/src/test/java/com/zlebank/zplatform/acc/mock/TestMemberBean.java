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
package com.zlebank.zplatform.acc.mock;
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
        
    private String memberId;
    private String merchName;
    private BusinessActorType memberType;
    /**
     *
     * @return
     */
    @Override
    public String getBusinessActorId() {
        
        return memberId;
    }
    /**
     *
     * @return
     */
    @Override
    public BusinessActorType getBusinessActorType() {
        return memberType;
    }
    public String getMerchName() {
        return merchName;
    }
    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public void setMemberType(BusinessActorType memberType) {
        this.memberType = memberType;
    }
  



}
