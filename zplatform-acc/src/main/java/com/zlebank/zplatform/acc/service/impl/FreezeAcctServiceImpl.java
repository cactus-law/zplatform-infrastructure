/* 
 * AccountServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年8月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.FreezeAcctService;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * 账户冻结实现类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月21日 上午11:56:34
 * @since 
 */
@Service
public class FreezeAcctServiceImpl implements FreezeAcctService{

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private AccountService accountService;
    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void freezeAcct(Account para) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(para.getId());
            this.upAccount(pojo, para);
            pojo.setStatus(AcctStatusType.FREEZE);
            pojo = accountDAO.update(pojo);
    }

    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void unFreezeAcct(Account para) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(para.getId());
            this.upAccount(pojo, para);
            pojo.setStatus(AcctStatusType.NORMAL);
            pojo = accountDAO.update(pojo);
    }

    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void stopInAcct(com.zlebank.zplatform.acc.bean.Account para) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(para.getId());
            this.upAccount(pojo, para);
            StringBuffer status = new StringBuffer();
            status.append("1");
            status.append(pojo.getStatus().getCode().charAt(1));
            pojo.setStatus(AcctStatusType.fromValue(status.toString()));
            pojo = accountDAO.update(pojo);
    }

    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void reopenInAcct(com.zlebank.zplatform.acc.bean.Account para) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(para.getId());
            this.upAccount(pojo, para);
            StringBuffer status = new StringBuffer();
            status.append("0");
            status.append(pojo.getStatus().getCode().charAt(1));
            pojo.setStatus(AcctStatusType.fromValue(status.toString()));
            pojo = accountDAO.update(pojo);
    }

    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void stopOutAcct(com.zlebank.zplatform.acc.bean.Account para) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(para.getId());
            this.upAccount(pojo, para);
            StringBuffer status = new StringBuffer();
            status.append(pojo.getStatus().getCode().charAt(0));
            status.append("1");
            pojo.setStatus(AcctStatusType.fromValue(status.toString()));
            pojo = accountDAO.update(pojo);
    }

    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void reopenOutAcct(Account para) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(para.getId());
           this.upAccount(pojo, para);
            StringBuffer status = new StringBuffer();
            status.append(pojo.getStatus().getCode().charAt(0));
            status.append("0");
            pojo.setStatus(AcctStatusType.fromValue(status.toString()));
            pojo = accountDAO.update(pojo);
    }

    /**
     * 注销账户
     * @param freeze
     * @return
     * @throws AccBussinessException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void logOutAcct(Account freeze) throws AccBussinessException {
            PojoAccount pojo= isAccountExists(freeze.getId());
            this.upAccount(pojo, freeze);
            if (pojo.getTotalBanance().compareTo(Money.valueOf(BigDecimal.ZERO))>0) {
                throw new AccBussinessException("E000010",pojo.getTotalBanance().getAmount().toPlainString());
            }
            pojo.setStatus(AcctStatusType.LOGOUT);
            pojo = accountDAO.update(pojo);
    }

    /**
     * 账户存在检查
     * @param accCode
     * @return pojo
     * @throws AccBussinessException 
     */
    private PojoAccount isAccountExists(Long id) throws AccBussinessException{
        PojoAccount pojo = accountDAO.getByIdForUpdate(id);
        if (pojo == null) {
            throw new AccBussinessException("EASAC0002",String.valueOf(id));
        }
        //验证DAC
        accountService.checkDAC(pojo);
        return pojo;
    }

    private void upAccount(PojoAccount pojo,Account para){
        if(para!=null&&StringUtil.isNotEmpty(para.getUpUserId())){
            pojo.setUpUser(Long.valueOf(para.getUpUserId()));
            pojo.setUpTime(new Date());
        }
        
    }

    /**
     *
     * @param para
     * @return
     * @throws AccBussinessException 
     */
    @Override
    public boolean getStatus(Account para) throws AccBussinessException {
        if(para==null)
            return false;
        PojoAccount pojo= isAccountExists(para.getId());
        if(pojo==null)
            return false;
      return  (para.getStatus()).equals(pojo.getStatus().getCode());
        
    }

}
