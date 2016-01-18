package com.zlebank.member.test;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.bean.MerchantBean;
import com.zlebank.zplatform.member.bean.Person;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.member.service.PersonService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

/* 
 * personserviceTest.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月9日 下午6:47:49
 * @since 
 */
public class personserviceTest {
    static ApplicationContext context = null;
    static PersonService personservice = null;
   static PrimayKeyService primay=null;
   static MerchService mer=null;
   static MemberService member=null;
    static {
        context = ApplicationContextUtil.get();
        personservice =  (PersonService) context.getBean("personServiceImpl");
        primay=(PrimayKeyService) context.getBean("primayKeyServiceImpl");
        mer =  (MerchService) context.getBean("merchServiceImpl");
        member =(MemberService)context.getBean("memberServiceImpl");
    }
    @Test
    @Ignore
    public void test() throws AbstractBusiAcctException, MemberBussinessException {
        Person p=new Person();
        p.setEmail("316237459@qq.com");
        p.setLoginName("yangyongpeng");
        p.setPwd("asdfasdf1");
        p.setMemberName("杨鹏");
        p.setPhone("13718738633");
      personservice.save(p,22L);
        }
    
    @Test
    @Ignore
     public void tests() throws MemberBussinessException{
        primay.getNexId("PERSONBIN");
         
     }
    @Test
    @Ignore
    public void  openOneTest() throws MemberBussinessException{
        try {
            member.openBusiAcct("郭嘉一级", "200000000000001", 2L);
            member.openBusiAcct("郭嘉二级", "200000000000002", 2L);
        } catch (AbstractBusiAcctException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
     @Test
     @Ignore
    public void testMerch() throws MemberBussinessException, AbstractBusiAcctException{
        MerchantBean mb=new MerchantBean();
        mb.setMerchname("杨鹏测试商户");
        mb.setAlias("yp");
        mb.setProvince(110L);
        mb.setCity(120L);
        mb.setStreet(130L);
        mb.setTaxno("aabb147");
        mb.setLicenceno("12346");
        mb.setOrgcode("12346");
        mb.setCorporation("woshiyangp");
        mb.setCorpno("aaaa");
        mb.setCorpfile("aaabbbcc");
        mb.setContact("aabbccddee");
        mb.setBankcode("农业银行");
        mb.setBanknode("gongs");
        mb.setAccname("杨鹏资产");
        mb.setParent("200000000000177");
        mb.setAccnum("62100202584874");
        mer.createMinorMerchant(mb, 22l);
       
    }
     
     public  void testMerchByid(){
         
         
     }

}
