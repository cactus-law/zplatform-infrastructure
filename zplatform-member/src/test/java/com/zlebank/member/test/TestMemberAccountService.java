/* 
 * TestMemberAccountService.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.member.bean.MemberBalanceBean;
import com.zlebank.zplatform.member.bean.MemberBalanceDetailBean;
import com.zlebank.zplatform.member.bean.MemberBean;
import com.zlebank.zplatform.member.bean.Person;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.GetAccountFailedException;
import com.zlebank.zplatform.member.service.MemberAccountService;

/**
 * 会员账户相关服务
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午3:29:03
 * @since 
 */
public class TestMemberAccountService {
    private ApplicationContext ac;
    private MemberAccountService memberAccountService;
    @Before
    public void init() {
        ac = ApplicationContextUtil.get();
        memberAccountService =  (MemberAccountService) ac.getBean("memberAccountServiceImpl");
    }
    /**
     * 账户余额
     */
//    @Test
    public void queryBalanceTest() {
        MemberBean member = new Person();
        member.setMemberId("100000000000494");
        MemberBalanceBean queryBalance = null;
        try {
            queryBalance = memberAccountService.queryBalance(MemberType.INDIVIDUAL, member);
        } catch (DataCheckFailedException | GetAccountFailedException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(queryBalance);
        System.out.println(queryBalance.getBalance());
    }
    
    /**
     * 账户收支明细
     */
    @Test
    public void queryBalanceDetailTest() {
        MemberBean member = new Person();
        member.setMemberId("100000000000435");
        PagedResult<MemberBalanceDetailBean> queryBalance = null;
        try {
            queryBalance = memberAccountService.queryBalanceDetail(MemberType.INDIVIDUAL, member, 1, 10);
        
            Assert.assertNotNull(queryBalance);
            System.out.println(queryBalance.getTotal());
            List<MemberBalanceDetailBean> rtn = queryBalance.getPagedResult();
            for(MemberBalanceDetailBean bean : rtn) {
                System.out.println(String.format("收支类型:%s\t 金额:%s \t 时间:%s", bean.getBudgetType(),bean.getTxnAmt(),bean.getTxnTime()));
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
