/* 
 * TestMemberService.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.member.test.ApplicationContextUtil;
import com.zlebank.zplatform.commons.test.RandomUtil;
import com.zlebank.zplatform.member.exception.PrimaykeyGeneratedException;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

/**
 * 
 * 会员服务类
 *
 * @author Luxiaoshuai,yangying
 * @version
 * @date 2016年1月13日 下午5:24:10
 * @since
 */
public class TestMemberService {
    private final static String INDIVVIDUAL = "INDIBIN";// 个人会员
    private final static String MERCHPARATYPE = "MERCHBIN";// 企业会员
    private final static String MEMBER_IDSEQUENCES = "seq_t_merch_deta_memberid";// 会员号生成用序列
    private ApplicationContext ac = ApplicationContextUtil.get();
    private MemberService memberService;
    private PrimayKeyService primayService;
    @Before
    public void init() {
        ac = ApplicationContextUtil.get();
        memberService = (MemberService) ac.getBean("memberServiceImpl");
        primayService = (PrimayKeyService) ac.getBean("primayKeyServiceImpl");
    }

    /**
     * 开通个人会员账户
     */
    @Test
    public void openIndiBusiAcct() {
        long userId = 99;
        String memberId = "";
        try {
            memberId = primayService.getNexId(INDIVVIDUAL,
                    MEMBER_IDSEQUENCES);
        } catch (PrimaykeyGeneratedException e1) {
            e1.printStackTrace();
        }

        String name = RandomUtil.randomAlphabet("Indiv-test-", 7);
        try {
            memberService.openBusiAcct(name, memberId, userId);
        }  catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * 开通企业会员账户
     */
    @Test
    public void openEnterpriseAcctTest() {
        long userId = 99;
        String memberId = "";
        try {
            memberId = primayService.getNexId(MERCHPARATYPE,
                    MEMBER_IDSEQUENCES);
        } catch (PrimaykeyGeneratedException e1) {
            e1.printStackTrace();
        }
        String name = RandomUtil.randomAlphabet("Enterprise-test-", 7);
        try {
            memberService.openBusiAcct(name, memberId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
