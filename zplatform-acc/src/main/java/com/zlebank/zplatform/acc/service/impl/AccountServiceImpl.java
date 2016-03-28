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

import java.util.Date;

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
import com.zlebank.zplatform.member.bean.BusinessActor;

/**
 * 会计账户服务
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

    /**
     * 开通会计账户
     *
     * @param account
     * @param member
     * @param userId
     * @return
     * @throws AccountCreateRepeatException
     * @throws AccBussinessException
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
    public Account openAcct(Account account, BusinessActor member, long userId)throws AccountCreateRepeatException, AccBussinessException {
        PojoSubject pojoParentSubject = subjectDAO.get(account
                .getParentSubject().getId());
        // 生成会计账户代码
        String acctCode = gengrateAcctCode(member, account,
                pojoParentSubject.getAcctCode());
        
        // 检查会计账户代码是否存在
        PojoAccount pojoAccount = accountDAO.getByAcctCode(acctCode);
        if(pojoAccount!=null){
            throw new AccountCreateRepeatException();
        }
        // 生成会计账户
        pojoAccount = initNewAccount(acctCode, pojoParentSubject,
                member.getBusinessActorId(), userId, account.getAcctCodeName());
        // 保存会计账户
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
        account.setBusinessActorId(memberId);
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
        Date current = new Date();
        account.setInTime(current);
        account.setUpTime(current);
        account.setInUser(userId);
        account.setUpUser(userId);
        return account;
    }

    private String gengrateAcctCode(BusinessActor member,
            Account account,
            String parentSubjectCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(parentSubjectCode);
        sb.append(member.getBusinessActorType().getCode());
        sb.append(member.getBusinessActorId());
        return sb.toString();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Account getAccountBalanceById(long accountId) {
        Account rtn = new Account();
        PojoAccount pojoAccount = accountDAO.get(accountId);
        rtn.setFronzenBalance(pojoAccount.getFrozenBalance());
        rtn.setBalance(pojoAccount.getBalance());
        rtn.setTotalBalance(pojoAccount.getTotalBanance());
        rtn.setStatus(pojoAccount.getStatus().getCode());
        return rtn;
    }
}
