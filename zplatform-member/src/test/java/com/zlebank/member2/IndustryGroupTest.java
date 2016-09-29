/* 
 * IndustryGroupTest.java  
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

import com.zlebank.zplatform.member.bean.IndustryGroupBean;
import com.zlebank.zplatform.member.service.IndustryGroupService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月29日 下午4:29:03
 * @since 
 */

public class IndustryGroupTest extends BaseTest{
    @Autowired
    private IndustryGroupService industryGroupService;
    @Test
    public void addGroup(){
        IndustryGroupBean groupBean=new IndustryGroupBean();
        groupBean.setGroupName("测试名称");
        groupBean.setInstiCode("300000000000027");
        groupBean.setMemberId("200000000000626");
        groupBean.setInuser(45);
        groupBean.setDrawable("0");
        try {
            industryGroupService.addGroup(groupBean);
        } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
        }
    }
}
