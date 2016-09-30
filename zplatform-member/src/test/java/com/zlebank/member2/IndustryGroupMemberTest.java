/* 
 * IndustryGroupMemberTest.java  
 * 
 * version TODO
 *
 * 2016年9月29日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member2;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.bean.InduGroupMemberBean;
import com.zlebank.zplatform.member.bean.InduGroupMemberCreateBean;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.service.IndustryGroupMemberService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月29日 下午5:41:38
 * @since 
 */
public class IndustryGroupMemberTest extends BaseTest {
        @Autowired
        private IndustryGroupMemberService industryGroupMemberService;
        @Test
        public void addMemberToGroup(){
            InduGroupMemberCreateBean bean=new InduGroupMemberCreateBean();
            bean.setGroupCode("0000000626");
            bean.setGroupId(19);
            bean.setInuser(45);
            bean.setMemberId("100000000000961");
            bean.setUsage(Usage.BASICPAY);
            try {
                industryGroupMemberService.addMemberToGroup(bean, true, BusinessActorType.INDUSTRY.getCode());
            } catch (AbstractBusiAcctException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Assert.fail();
            }
        }
        
        public void test_query(){
        	InduGroupMemberBean queryGroupMemberExist = industryGroupMemberService.queryGroupMemberExist("0000000637", "100000000000640", Usage.GRANTCREDIT.getCode());
        	System.out.println(queryGroupMemberExist.getGroupId());
        	//InduGroupMemberBean groupMemberByMemberIdAndGroupCode = industryGroupMemberService.getGroupMemberByMemberIdAndGroupCode("100000000000640", "0000000637");
        	//System.out.println(groupMemberByMemberIdAndGroupCode.getGroupId());
        }
}
