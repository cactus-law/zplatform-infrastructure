/* 
 * AccountServiceImpl.java  
 * 
 * version 1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectDAO;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.AccountCreateRepeatException;
import com.zlebank.zplatform.acc.exception.AccountNotExistException;
import com.zlebank.zplatform.acc.exception.AcctCodeIllegalException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAbstractSubject;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.GetDACService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.Member;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月25日 上午11:56:34
 * @since
 */
@Service
public class AccountServiceImpl implements AccountService {



    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private SubjectDAO subjectDAO;
    
    @Autowired
    private GetDACService dacUtil;
    @Override
    public void checkDAC(PojoAbstractSubject account) throws AccBussinessException {
        //验证DAC
        String oldDac=account.getDac();
        String newDac=dacUtil.generteDAC(account.getAcctCode(),account.getBalance() , account.getFrozenBalance(),account.getTotalBanance());
       if(!oldDac.equals(newDac))
           throw new AccBussinessException("E100005");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account getByAcctCode(String acctCode) throws AbstractAccException {
        PojoAccount account = accountDAO.getByAcctCode(acctCode);
        if (account == null) {
            AccountNotExistException exception = new AccountNotExistException();
            exception.setParams(acctCode);
            throw exception;
        }
        return BeanCopyUtil.copyBean(Account.class, account);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
    public Account openAcct(Account account, Member member, long userId)throws AccountCreateRepeatException, AccBussinessException {
        PojoSubject pojoParentSubject = subjectDAO.get(account
                .getParentSubject().getId());
        String acctCode = gengrateAcctCode(member, account,
                pojoParentSubject.getAcctCode());
        
        
        PojoAccount pojoAccount = accountDAO.getByAcctCode(acctCode);
        if(pojoAccount!=null){
            throw new AccountCreateRepeatException();
        }
        pojoAccount = initNewAccount(acctCode, pojoParentSubject,
                member.getMemberId(), userId, account.getAcctCodeName());
        pojoAccount = saveAcct(pojoAccount);
        return BeanCopyUtil.copyBean(Account.class, pojoAccount);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public String addAcct(long parentSubjectId,String acctCode,String acctName,long userId)throws AbstractAccException{
        PojoSubject pojoParentSubject = subjectDAO.get(parentSubjectId);
        if(pojoParentSubject==null){
            throw new AccountNotExistException();
        }
        String parentAcctCode = pojoParentSubject.getAcctCode();
        if(acctCode.length()<(parentAcctCode.length()+2)){
            throw new AcctCodeIllegalException();
        }
        
        if(!acctCode.substring(0,parentAcctCode.length()).equals(parentAcctCode)){
            throw new AcctCodeIllegalException();
        }
        
        PojoAccount pojoAccount = accountDAO.getByAcctCode(acctCode);
        if(pojoAccount!=null){
            throw new AccountCreateRepeatException();
        }
        pojoAccount = initNewAccount(acctCode, pojoParentSubject,
                null, userId,acctName);
        
        pojoAccount = saveAcct(pojoAccount);
        
        return acctCode;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PojoAccount saveAcct(PojoAccount account) {
        return accountDAO.save(account);
    }

    private PojoAccount initNewAccount(String acctCode,
            PojoSubject parentSubject,
            String memberId,
            long userId,
            String acctName) {
        PojoAccount account = new PojoAccount();
        account.setAcctCode(acctCode);
        account.setAcctType(AcctType.ACCOUNT);
        account.setCrdr(parentSubject.getCrdr());
        account.setAcctElement(parentSubject.getAcctElement());
        account.setMemberId(memberId);
        account.setBalance(Money.ZERO);
        account.setFrozenBalance(Money.ZERO);
        account.setTotalBanance(Money.ZERO);
        account.setCreditBalance(Money.ZERO);
        account.setDebitBalance(Money.ZERO);
        // 生成dac
        String dac = dacUtil.generteDAC(acctCode, account.getBalance(),
                account.getFrozenBalance(), account.getTotalBanance());
        account.setDac(dac);
        account.setParentSubject(parentSubject);
        account.setStatus(AcctStatusType.NORMAL);
        account.setUpUser(userId);
        account.setAcctCodeName(acctName);
        return account;
    }

    private String gengrateAcctCode(Member member,
            Account account,
            String parentSubjectCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(parentSubjectCode);
        sb.append(member.getMemberType().getCode());
        sb.append(member.getMemberId());
        return sb.toString();
    }
}
