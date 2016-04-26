/* 
 * MerchServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.MerchantBean;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.bean.enums.MerchStatusType;
import com.zlebank.zplatform.member.dao.MemberBaseDAO;
import com.zlebank.zplatform.member.dao.MerchDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMemberBase;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.member.service.PrimayKeyService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 上午10:09:47
 * @since 
 */
@Service
public class MerchServiceImpl implements MerchService {
    /**个人生成memberID规则**/
    private final static String MERCHPARATYPE="MERCHBIN";
    @Autowired    
    private MerchDAO merchDao;
    @Autowired
    private PrimayKeyService primayService;
    @Autowired
    private MemberService memberservice;
    @Autowired
    private MemberBaseDAO memberBase;
        
    /**
     *
     * @return
     * @throws MemberBussinessException 
     * @throws AbstractBusiAcctException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public String createMinorMerchant(MerchantBean mb,Long userId) throws MemberBussinessException, AbstractBusiAcctException {
        
        if(StringUtil.isEmpty(mb.getParent())){
           //格式不合法
            throw new MemberBussinessException("M100004");
        }
        // 得到父级商户
          PojoMerchDeta merch= merchDao.getParentMerch(mb.getParent());
          if(merch==null){
              //TODO 父级商户不存在
              throw new MemberBussinessException("M100005");
          }
            PojoMerchDeta merchPo=  BeanCopyUtil.copyBean(PojoMerchDeta.class, mb);
            merchPo.setPrdtVer(merch.getPrdtVer());
            merchPo.setFeeVer(merch.getFeeVer());
            merchPo.setSpiltVer(merch.getSpiltVer());
            merchPo.setRiskVer(merch.getRiskVer());
            merchPo.setRoutVer(merch.getRoutVer());
            //得到序列
            @SuppressWarnings("deprecation")
            String memberId=primayService.getNexId(MERCHPARATYPE);
           Date date=new Date();
            merchPo.setInTime(date);
            merchPo.setInTime(date);
            merchPo.setInUser(userId);
            merchPo.setMemberId(memberId);
            merchPo.setMerchStatus(MerchStatusType.NORMAL);
            merchDao.saveA(merchPo);
            //开通会计账户
            memberservice.openBusiAcct("", memberId, userId);// TODO: 传入商户的名称
            PojoMemberBase memberbasePo=  BeanCopyUtil.copyBean(PojoMemberBase.class, merchPo);
            memberbasePo.setMerchtype(BusinessActorType.ENTERPRISE);
            memberBase.saveA(memberbasePo);
            return memberId;
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    public PojoMerchDeta getParentMerch(String memberId) {
        return merchDao.getParentMerch(memberId);
    }

    /**
     *
     * @param taxno
     * @return
     */
    @Override
    public PojoMerchDeta getMerchByTaxno(String taxno) {
      return merchDao.getMerchByTaxno(taxno);
    }

    /**
     *
     * @param licenceNo
     * @return
     */
    @Override
    public PojoMerchDeta getMerchByLicenceNo(String licenceNo) {
        return merchDao.getMerchByLicenceNo(licenceNo);
    
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public PojoMerchDeta getMerchByEmail(String email) {
       return  merchDao.getMerchByEmail(email);
    }

    /**
     *
     * @param phone
     * @return
     */
    @Override
    public PojoMerchDeta getMerchByPhone(String phone) {
      return merchDao.getMerchByPhone(phone);
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public PojoMerchDeta getMerchBymemberId(String memberId) {
      return merchDao.getMerchBymemberId(memberId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void update(PojoMerchDeta pojoMerchDeta) {
         merchDao.update(pojoMerchDeta);
        
    }

}
