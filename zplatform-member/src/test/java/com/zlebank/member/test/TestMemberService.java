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
package com.zlebank.member.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

/**
 * 
 * 会员服务类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月13日 下午5:24:10
 * @since
 */
public class TestMemberService {
    private final static String PERSONPARATYPE="PERSONBIN";// 个人会员
    private final static String MERCHPARATYPE="MERCHBIN";// 企业会员
    private final static String COOPINSTI="COOPINSTIBIN";// 合作机构
    private ApplicationContext ac = ApplicationContextUtil.get();
    private MemberService memberService;
    private PrimayKeyService primayService;
    @Before
    public void init() {
        ac = ApplicationContextUtil.get();
        memberService =  (MemberService) ac.getBean("memberServiceImpl");
        primayService =  (PrimayKeyService) ac.getBean("primayKeyServiceImpl");
    }
    
    /**
     * 开通【个人会员】会员账户（业务账户和会计账户）
     */
    public void openBusiAcctTest() {
        long userId=99;
        String memberId="";
        try {
            memberId=primayService.getNexId(PERSONPARATYPE);
        } catch (MemberBussinessException e1) {
            e1.printStackTrace();
        }
        String name="长恨歌-个人";
        try {
            memberService.openBusiAcct(name, memberId, userId);
        } catch (MemberBussinessException e) {
            e.printStackTrace();
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 开通【企业会员】会员账户（业务账户和会计账户）
     */
    public void openEnterpriseAcctTest() {
        long userId=99;
        String memberId="";
        try {
            memberId=primayService.getNexId(MERCHPARATYPE);
        } catch (MemberBussinessException e1) {
            e1.printStackTrace();
        }
        String name="长恨歌-企业";
        try {
            memberService.openBusiAcct(name, memberId, userId);
        } catch (MemberBussinessException e) {
            e.printStackTrace();
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 开通【合作机构】账户（业务账户和会计账户）
     */
    @Test
    public void openCoopInstiTest() {
        long userId=99;
        String memberId="";
        try {
            memberId=primayService.getNexId(COOPINSTI);
        } catch (MemberBussinessException e1) {
            e1.printStackTrace();
        }
        String name="长恨歌-合作机构";
        try {
            memberService.openBusiAcct(name, memberId, userId);
        } catch (MemberBussinessException e) {
            e.printStackTrace();
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
