/* 
 * BusiAcctServiceImpl.java  
 * 
 * version 1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.enums.BuisAcctCodePrefix;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.BusiAcctNotExistException;
import com.zlebank.zplatform.acc.exception.BusiAcctRepeatException;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcct;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.Member;
import com.zlebank.zplatform.member.bean.enums.MemberType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 下午1:59:40
 * @since
 */
@Service
public class BusiAcctServiceImpl implements BusiAcctService {
    private final Log log = LogFactory.getLog(BusiAcctServiceImpl.class);

    @Autowired
    private SubjectSelector mappingTableSubjectSelector;
    @Autowired
    private BusiAcctDAO busiAcctDAO;
    @Autowired
    private AccountService accountServiceImpl;
    @Autowired
    private AccountDAO accountDAO;
 

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public String openBusiAcct(Member member, BusiAcct busiAcct, long userId)
            throws AbstractBusiAcctException {
        /*
         * 选取父级科目
         */
        Subject parentSubject = mappingTableSubjectSelector.select(member,
                busiAcct);
        String busiAcctCode = gengrateBusiAcctCode(member, busiAcct,
                parentSubject.getAcctCode(), "");
        busiAcct.setBusiAcctCode(busiAcctCode);

        /*
         * 添加会计科目
         */
        PojoBusiAcct pojoBusiAcct = busiAcctDAO.getByBusiAcctCode(busiAcctCode);
        if (pojoBusiAcct != null) {
            throw new BusiAcctRepeatException();
        }
        Account account = new Account();
        account.setParentSubject(parentSubject);
        account.setAcctCodeName(busiAcct.getBusiAcctName());
        try {
            account = accountServiceImpl.openAcct(account, member, userId);
        } catch (AbstractAccException e) {
            log.warn("Add account failed.Caused By: " + e.getMessage());
            throw new BusiAcctRepeatException(e);
        }

        /*
         * 添加账户科目
         */
        pojoBusiAcct = saveBusiAcct(busiAcct, member);

        /*
         * 绑定会计科目到账户科目
         */
        pojoBusiAcct.setAccountId(account.getId());

        return pojoBusiAcct.getBusiAcctCode();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BusiAcct getByBusiAcctCode(String busiAcctCode)
            throws AbstractBusiAcctException {
        PojoBusiAcct pojoBusiAcct = busiAcctDAO.getByBusiAcctCode(busiAcctCode);
        return BeanCopyUtil.copyBean(BusiAcct.class, pojoBusiAcct);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String getAccount(Usage usage, String memberId)
            throws AbstractBusiAcctException {
        if (log.isDebugEnabled()) {
            log.debug("通过会员ID和用途得到科目代码："+"【用途："+usage.getCode()+"】【会员号"+memberId+"】");
        }
        long accountId = busiAcctDAO.getAccount(usage, memberId);
        PojoAccount account = accountDAO.get(accountId);
        if (account == null) {
            throw new BusiAcctNotExistException();
        }
        return account.getAcctCode();
    }

    private String gengrateBusiAcctCode(Member member,
            BusiAcct busiAcct,
            String parentSubjectCode,
            String productNo) {
        StringBuilder sb = new StringBuilder();
        if(MemberType.Individual == member.getMemberType()){
            sb.append(BuisAcctCodePrefix.PRIVATE.getCode());
        }else if(MemberType.MERCHANT == member.getMemberType()){
            sb.append(BuisAcctCodePrefix.PUBLIC.getCode());
        }else{
            sb.append(BuisAcctCodePrefix.PUBLIC.getCode());
        }
        switch (busiAcct.getUsage().getPrimaryUsage()) {
            case FUND :
                // do noting
                break;
            case PRODUCT :
                // TODO
                // there is no product business ,so throw exception now,will add
                // when it has.
                throw new RuntimeException() {
                    /**
                     * serialVersionUID
                     */
                    private static final long serialVersionUID = 757505037670410404L;

                    public String getMessage() {
                        return "product business account is not support yet.";
                    }
                };
                // break;
            default :
                break;
        } 
        sb.append(busiAcct.getUsage().getCode());
        sb.append(member.getMemberType().getCode());
        sb.append(member.getMemberId());

        return sb.toString();
    }
    private PojoBusiAcct saveBusiAcct(BusiAcct busiAcct, Member member) {
        PojoBusiAcct pojoBusiAcct = new PojoBusiAcct();
        pojoBusiAcct.setUsage(busiAcct.getUsage());
        pojoBusiAcct.setBusiAcctCode(busiAcct.getBusiAcctCode());
        pojoBusiAcct.setBusiAcctName(busiAcct.getBusiAcctName());
        pojoBusiAcct.setMemberId(member.getMemberId());
        pojoBusiAcct.setUsage(busiAcct.getUsage());
        return busiAcctDAO.merge(pojoBusiAcct);
    }

    /**
     *  通过 会员ID 标示得到科目代码的ID
     * @param usage
     * @param memberId
     * @return
     * @throws AbstractBusiAcctException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long getAccountId(Usage usage, String memberId)  throws AbstractBusiAcctException {
        if (log.isDebugEnabled()) {
            log.debug("通过会员ID和用途得到科目代码："+"【用途："+usage.getCode()+"】【会员号"+memberId+"】");
        }
        long accountId = busiAcctDAO.getAccount(usage, memberId);
        return accountId;
    }
}
