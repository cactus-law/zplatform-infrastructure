/* 
 * AccountDAOImpl.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.service.GetDACService;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * AccountDAOImpl
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月22日 下午5:56:58
 * @since
 */
@Repository
public class AccountDAOImpl extends HibernateBaseDAOImpl<PojoAccount> implements AccountDAO { 
    private static final Log log = LogFactory.getLog(AccountDAOImpl.class);
    //等待10秒没取到数据就抛出异常
   public static final int TIME_OUT = 10000;
    @Autowired
    private GetDACService dacUtil;
    
    public PojoAccount get(long id) {
        return (PojoAccount) getSession().get(PojoAccount.class, id);
    } 
   public PojoAccount getByIdForUpdate(long id) {
        Criteria criteria = getSession().createCriteria(PojoAccount.class);
        criteria.add(Restrictions.eq("id", id));
        // 加锁
        criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
        criteria.setTimeout(TIME_OUT);
        return (PojoAccount) criteria.uniqueResult();
    } 
    public PojoAccount save(PojoAccount subject) { 
        subject.setDac(dacUtil.generteDAC(subject.getAcctCode(), subject.getBalance(), subject.getFrozenBalance(), subject.getTotalBanance()));
        return (PojoAccount) merge(subject);
    }
    
    @Override
    public PojoAccount update(PojoAccount subject) {
        subject.setDac(dacUtil.generteDAC(subject.getAcctCode(), subject.getBalance(), subject.getFrozenBalance(), subject.getTotalBanance()));
        return super.update(subject);
    }
    /**
     * 根据账户号得到账户
     * @param accCode
     * @return
     */
    @Override
    public PojoAccount getByAcctCode(String accCode) {
        if (log.isDebugEnabled()) {
            log.debug("【DAO】根据会计账户号得到账户："+accCode);
        }
        
        Criteria criteria = getSession().createCriteria(PojoAccount.class);
        criteria.add(Restrictions.eq("acctCode", accCode));
        
        PojoAccount pojoAccount = (PojoAccount) criteria.uniqueResult();
        getSession().refresh(pojoAccount);
        return pojoAccount;
    }
    /**
     *根据memberId得到账户
     * @param memberId
     * @return
     */
 
   @SuppressWarnings("unchecked")
   @Override
   public List<PojoAccount> getAllAccountByMId(String memberId) {
       Session session = this.getSession();
       Criteria criteria = session.createCriteria(PojoAccount.class, "p"); 
       criteria.add(Restrictions.eq("p.memberId", memberId));
       criteria.add(Restrictions.ne("p.status",AcctStatusType.LOGOUT));
       return criteria.list();
   
       
   }
    /**
     *
     * @param id
     * @return
     */
    @Override
    public PojoAccount getByid(long id) {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(PojoAccount.class, "p"); 
        criteria.add(Restrictions.eqOrIsNull("p.id", id));
        criteria.add(Restrictions.ne("p.status",AcctStatusType.LOGOUT));
        return (PojoAccount)criteria.uniqueResult();
    }

    /**
     * 得到指定序列
     * @param sequences
     * @return
     */
    public long getSequence(String sequences){
        String sql = " SELECT "+sequences+".NEXTVAL nextvalue FROM DUAL";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        long maxId = (long)(query.addScalar("nextvalue", StandardBasicTypes.LONG) ).uniqueResult();
         return  maxId;
       } 
    
}
