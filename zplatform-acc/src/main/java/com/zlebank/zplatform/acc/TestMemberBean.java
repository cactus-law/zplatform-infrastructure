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
import com.zlebank.zplatform.member.bean.Member;
import com.zlebank.zplatform.member.bean.enums.MemberType;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月2日 下午5:44:30
 * @since 
 */
public class TestMemberBean implements Member {
        
    private String memberId;
    private String merchName;
    private MemberType memberType;
    /**
     *
     * @return
     */
    @Override
    public String getMemberId() {
        
        return memberId;
    }
    /**
     *
     * @return
     */
    @Override
    public MemberType getMemberType() {
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
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
  



}
