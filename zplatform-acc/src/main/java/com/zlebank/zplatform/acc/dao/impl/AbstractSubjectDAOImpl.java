/* 
 * AbstractSubjectDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.math.BigDecimal;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.dao.AbstractSubjectDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoAbstractSubject;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.acc.service.GetDACService;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月27日 下午5:30:19
 * @since 
 */
@Repository
public class AbstractSubjectDAOImpl extends HibernateBaseDAOImpl<PojoAbstractSubject> implements AbstractSubjectDAO {
    @Autowired
    private GetDACService dacUtil;
    @Value("${acc.dac.key}")
    private  String dacKey;
    
    /**
     * 根据ID得到科目
     *
     * @param id
     * @return
     */
    public PojoAbstractSubject get(long id) {
        return (PojoAbstractSubject) getSession().get(PojoAbstractSubject.class, id);
    }
    public PojoAbstractSubject update(PojoAbstractSubject subject) {
        subject.setDac(dacUtil.generteDAC(subject.getAcctCode(), subject.getBalance(), subject.getFrozenBalance(), subject.getTotalBanance()));
        return super.update(subject);
    }
    /**
     *  总账处理（叶子科目同步记账时调用）
     * @param parentId
     * @throws Exception 
     */
    @Override
    public void processLedger(PojoAccount account) throws Exception {
        Long parentId = account.getParentSubject().getId();
        if (parentId==null || AcctType.SUBJECT==account.getAcctType()) throw new AccBussinessException("E000009", new Object[]{account.getAcctCode()});
        PojoAbstractSubject pojo=account.getParentSubject();
        do {
            if (pojo.getId() != account.getParentSubject().getId()) 
                pojo = (PojoSubject) pojo.getParentSubject();
            pojo.setBalance(pojo.getBalance().plus(account.getBalance()));// 更新可用余额
            pojo.setFrozenBalance(pojo.getFrozenBalance().plus(account.getFrozenBalance()));// 更新冻结金额
            pojo.setCreditBalance(pojo.getCreditBalance().plus(account.getCreditBalance()));// 更新借记额
            pojo.setDebitBalance(pojo.getDebitBalance().plus(account.getDebitBalance()));// 更新代词额
            pojo.setTotalBanance(pojo.getTotalBanance().plus(account.getTotalBanance()));// 更新总余额
            merge(pojo);
        } while (parentId==null);
    }
    /**
     * 使用原生SQL更新解决并发问题
     * @param account
     * @return
     */
    @Override
    public int updateBySql(PojoAbstractSubject account) {
        // 状态条件
        String status="";
        if (account.getBalance().getAmount().compareTo(BigDecimal.ZERO) >= 0) {
            status = " '10' , '11', '99' ";
        } else {
            status = " '01' , '11', '99' ";
        }
        
        String updateSql = "update t_acc_acct t set t.balance = t.balance + :balance, t.frozen_balance = t.frozen_balance + :frozenBalance, t.total_balance = t.total_balance + :totalBalance, t.dac = md5('#'||t.acct_code||'#'||(t.balance + :balance)||'#'||(t.frozen_balance + :frozenBalance)||'#'||(t.total_balance + :totalBalance)||'#'||:dacKey) where t.acct_code = :acctCode and t.status not in (:status) and t.balance + :balance>=0";
        SQLQuery query= getSession().createSQLQuery(updateSql);
        query.setParameter("acctCode", account.getAcctCode());
        query.setParameter("balance", account.getBalance().getAmount().toPlainString());
        query.setParameter("frozenBalance", account.getFrozenBalance().getAmount().toPlainString());
        query.setParameter("totalBalance", account.getTotalBanance().getAmount().toPlainString());
        query.setParameter("dacKey", dacKey);
        query.setParameter("status", status);
        int i= query.executeUpdate();
        return i;
    }
}
