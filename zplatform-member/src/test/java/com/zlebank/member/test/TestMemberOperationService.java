/* 
 * TestMemberOperationService.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member.test;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.commons.test.RandomUtil;
import com.zlebank.zplatform.member.bean.MemberBean;
import com.zlebank.zplatform.member.bean.Person;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.exception.CreateBusiAcctFailedException;
import com.zlebank.zplatform.member.exception.CreateMemberFailedException;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.InvalidMemberDataException;
import com.zlebank.zplatform.member.exception.LoginFailedException;
import com.zlebank.zplatform.member.service.MemberOperationService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午5:45:34
 * @since 
 */
public class TestMemberOperationService {
    private ApplicationContext ac;
    private MemberOperationService memberOperationService;
    @Before
    public void init() {
        ac = ApplicationContextUtil.get();
        memberOperationService =  (MemberOperationService) ac.getBean("memberOperationServiceImpl");
    }
    
    public void registMemberTest() {
        MemberBean member = new Person();
        member.setMemberName(RandomUtil.randomAlphabet("anonymity", 4));
        member.setLoginName(member.getMemberName());
        member.setPhone("99999999999");
        member.setPwd("null");
        member.setInstiId(25L);
        try {
            memberOperationService.registMember(MemberType.INDIVIDUAL, member);
        } catch (InvalidMemberDataException | CreateMemberFailedException
                | CreateBusiAcctFailedException e) {
            e.printStackTrace();
        }
    }
//    @Test
    public void loginTest() {
        MemberBean member = new Person();
//        member.setLoginName("luxiaoshuai");
        member.setPhone("13426342943");
        member.setPwd("abc123");
        member.setInstiId(25L);
        try {
            String memberId = memberOperationService.login(MemberType.INDIVIDUAL, member);
            System.out.println("登陆成功：会员号为："+memberId);
        } catch ( DataCheckFailedException | LoginFailedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 设置支付密码
     */
//    @Test
    public void verifyPayPwdTest() {
        MemberBean member = new Person();
        member.setLoginName("luxiaoshuai");
        member.setPhone("13426342943");
//        member.setPaypwd("123456");
        member.setPwd("abc1234");
        member.setInstiId(25L);

        // 重置支付密码
        resetPayPwd(member);

        // 验证支付密码
        verifyPayPwd(member);
        
        // 重置登陆密码
        resetPwd(member);
        
        // 验证登陆密码
        login(member);
      
    }
    
    /**
     * 验证登陆密码
     * @param member
     */
    private void login(MemberBean member) {
        try {
            String memberId = memberOperationService.login(MemberType.INDIVIDUAL, member);
            System.out.println("登陆成功：会员号为："+memberId);
        } catch ( DataCheckFailedException | LoginFailedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 重置登陆密码
     * @param member
     */
    private void resetPwd(MemberBean member) {
        try {
            boolean memberId = memberOperationService.resetLoginPwd(MemberType.INDIVIDUAL, member, "abc123",true);
            Assert.assertTrue(memberId);
        } catch ( DataCheckFailedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 重置密码
     * @param member
     */
    private void verifyPayPwd(MemberBean member) {
        try {
            boolean memberId = memberOperationService.verifyPayPwd(MemberType.INDIVIDUAL, member);
            Assert.assertTrue(memberId);
        } catch ( DataCheckFailedException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param member
     */
    private void resetPayPwd(MemberBean member) {
        try {
            boolean memberId = memberOperationService.resetPayPwd(MemberType.INDIVIDUAL, member, "123456", false);
            Assert.assertTrue(memberId);
            
        } catch ( DataCheckFailedException e) {
            e.printStackTrace();
        }
    }
}
