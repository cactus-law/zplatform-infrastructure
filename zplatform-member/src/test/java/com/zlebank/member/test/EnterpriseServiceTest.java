/* 
 * EnterpriseServiceTest.java  
 * 
 * version TODO
 *
 * 2016年3月21日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.zlebank.zplatform.member.bean.EnterpriseBean;
import com.zlebank.zplatform.member.bean.EnterpriseRealNameQueryBean;
import com.zlebank.zplatform.member.service.EnterpriseRealnameApplyService;
import com.zlebank.zplatform.member.service.EnterpriseService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年3月21日 下午2:21:03
 * @since 
 */
public class EnterpriseServiceTest {
    public ApplicationContext context;
    private EnterpriseService enterpriseService;
    private EnterpriseRealnameApplyService realNameService;
    @Before
    public void init() {
        context = ApplicationContextUtil.get();
        enterpriseService = (EnterpriseService)context.getBean(EnterpriseService.class);
        realNameService=context.getBean(EnterpriseRealnameApplyService.class);
    }
    
    public void testGetEnterprise() {
        EnterpriseBean memberId = enterpriseService.getEnterpriseByMemberId("200000000000597");
        System.out.println(memberId.getEnterpriseName());
        Assert.notNull(memberId);
    }
    //@Test
    public void testQueryRealName(){
        EnterpriseRealNameQueryBean queryBean=new EnterpriseRealNameQueryBean();
        realNameService.queryPaged(1, 10, queryBean);
    }
}
