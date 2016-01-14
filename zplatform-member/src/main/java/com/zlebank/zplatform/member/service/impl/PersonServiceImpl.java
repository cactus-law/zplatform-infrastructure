package com.zlebank.zplatform.member.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.Person;
import com.zlebank.zplatform.member.bean.PersonManager;
import com.zlebank.zplatform.member.bean.enums.MemberStatusType;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.bean.enums.SexType;
import com.zlebank.zplatform.member.dao.MemberBaseDAO;
import com.zlebank.zplatform.member.dao.PersonDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMemberBase;
import com.zlebank.zplatform.member.pojo.PojoPersonDeta;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.PersonService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

/* 
 * PersonServiceImpl.java  
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
 * @date 2015年9月9日 下午6:45:03
 * @since 
 */
@Service
public class PersonServiceImpl implements PersonService {
   /**个人生成memberID规则**/
    private final static String INDIPARATYPE="INDIBIN";
    @Autowired
    private PersonDAO persondao;
    @Autowired
    private MemberService memberservice;
    @Autowired
    private PrimayKeyService primayService;
    @Autowired
    private MemberBaseDAO memberBase;
    /**
     *
     * @param pers
     * @throws AbstractBusiAcctException 
     * @throws MemberBussinessException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public String save(Person pers,long userId) throws AbstractBusiAcctException, MemberBussinessException {
     //验证手机号是否存在
        if(persondao.getPersonByPhone(pers.getPhone())!=null){
           throw new MemberBussinessException("M100001");
        }
        // 验证邮箱是否存在
        if(persondao.getPersonByEmail(pers.getEmail())!=null){
            throw new MemberBussinessException("M100002");
         }

       //得到序列
        String memberId=primayService.getNexId(INDIPARATYPE);
        //开通会计账户
        memberservice.openBusiAcct(pers.getMembername(), memberId, userId);
        //开通会计账户
        PojoPersonDeta person=BeanCopyUtil.copyBean(PojoPersonDeta.class,pers);
        person.setMemberid(memberId);
        person.setpMemberid(memberId);
        person.setMembertype(BusinessActorType.INDIVIDUAL);
        person.setBindPhone(pers.getPhone());
       Date date=new Date();
        person.setIntime(date);
        person.setInuser(userId);
        person.setPIntime(date);
        person.setPInuser(userId);
        person.setBindEmail(person.getEmail());
        person.setMemberstat(MemberStatusType.NORMAL);
        person.setSex(SexType.fromValue(Integer.valueOf(pers.getSex())));
        person.setStatus(MemberStatusType.NORMAL);
        persondao.merge(person);
        //将信息插入到memberBase
       PojoMemberBase memberBasepo=new PojoMemberBase();
       memberBasepo.setMemberid(memberId);
       memberBasepo.setMerchname(pers.getMembername());
       memberBasepo.setMerchtype(BusinessActorType.INDIVIDUAL);
       memberBase.saveA(memberBasepo);
       return memberId;
    
    }
    /**
     *
     * @param phone
     * @return
     */
    @Override
    public PojoPersonDeta getPersonByPhone(String phone) {
      
       return persondao.getPersonByPhone(phone);
    }
    /**
     *
     * @param email
     * @return
     */
    @Override
    public PojoPersonDeta getPersonByEmail(String email) {
       return persondao.getPersonByEmail(email);
    }
    /**
     *
     * @param memberId
     * @return
     */
    @Override
    public PersonManager getPersonBeanByMemberId(String memberId) {
        
   PojoPersonDeta     person  =   persondao.getPersonByMemberId(memberId);
         
         PersonManager pm=  BeanCopyUtil.copyBean(PersonManager.class, person);
         pm.setStatus(person.getStatus()==null?"":person.getStatus().getCode());
      pm.setSex(person.getSex()==null? "":String.valueOf(person.getSex().getCode()));
      return  pm;
    }
    /**
     *
     * @param memberId
     * @return
     */
    @Override
    public PojoPersonDeta getPersonByMemberId(String memberId) {
    
        return persondao.getPersonByMemberId(memberId);
    }

    
}
