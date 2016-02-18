/* 
 * AccFrozenTaskDAOImpl.java  
 * 
 * version v1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.AccountAmountQuery;
import com.zlebank.zplatform.acc.bean.enums.FrozenStatusType;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.dao.AccFrozenTaskDAO;
import com.zlebank.zplatform.acc.pojo.PojoAccFrozenTask;
import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午7:03:57
 * @since 
 */
@Repository
public class AccFrozenTaskDAOImpl extends AbstractPagedQueryDAOImpl<PojoAccFrozenTask,AccountAmountQuery>  implements AccFrozenTaskDAO {
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";
    
    public PojoAccFrozenTask get(long id) {
        return (PojoAccFrozenTask) getSession().get(PojoAccFrozenTask.class, id);
    }


    @Override
    public PojoAccFrozenTask getByIdForUpdate(long id) {
        Criteria criteria = getSession().createCriteria(PojoAccFrozenTask.class);
        criteria.add(Restrictions.eq("id", id));
        // 悲观锁
        criteria.setLockMode(LockMode.UPGRADE_NOWAIT);
        return (PojoAccFrozenTask) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PojoAccFrozenTask> getLockRecords(int fetchSize) {
        Criteria criteria = getSession().createCriteria(PojoAccFrozenTask.class);
        // 乐观锁
        criteria.add(Restrictions.eq("isLock", LockStatusType.UNLOCK));
        criteria.add(Restrictions.eq("status", FrozenStatusType.FREEZE));
        criteria.add(Restrictions.le("unfrozenTime", new Date()));
        criteria.setMaxResults(fetchSize);
        return criteria.list();
    }


    /**
     *
     * @param e
     * @return
     * @throws ParseException 
     */
    @Override
    protected Criteria buildCriteria(AccountAmountQuery e) {
        Criteria criteria = this.getSession().createCriteria(PojoAccFrozenTask.class);
        try {
        if(e!=null){
        if(StringUtil.isNotEmpty(e.getAccCode())){
                criteria.add(Restrictions.eq("acctCode", e.getAccCode()));
        } 
        if(StringUtil.isNotEmpty(e.getStatus())){
            criteria.add(Restrictions.eq("status", FrozenStatusType.fromValue(e.getStatus())));
        } 
        if(StringUtil.isNotEmpty(e.getStartFrozenSTime())){
            
            criteria.add(Restrictions.ge("frozenSTime",  DateUtil.convertToDate(e.getStartFrozenSTime(),DEFAULT_TIME_STAMP_FROMAT)));
        } 
        if(StringUtil.isNotEmpty(e.getEndFrozenSTime())){
            criteria.add(Restrictions.le("frozenSTime", DateUtil.convertToDate(e.getEndFrozenSTime(),DEFAULT_TIME_STAMP_FROMAT)));
            
        }
        if(StringUtil.isNotEmpty(e.getStartUnfrozenTime())){
            criteria.add(Restrictions.ge("unfrozenTime",DateUtil.convertToDate( e.getStartUnfrozenTime(),DEFAULT_TIME_STAMP_FROMAT)));
        }
        
        if(StringUtil.isNotEmpty(e.getEndUnfrozenTime())){
            criteria.add(Restrictions.le("unfrozenTime", DateUtil.convertToDate(e.getEndUnfrozenTime(),DEFAULT_TIME_STAMP_FROMAT)));
        } 
        if(StringUtil.isNotEmpty(e.getTxnseqno())){
            criteria.add(Restrictions.like("txnseqno","%"+e.getTxnseqno()+"%"));
        }
        }
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        criteria.addOrder(Order.desc("intime"));
        return criteria;
    }


    /**
     *
     * @param Id
     * @return
     */
    @Override
    public PojoAccFrozenTask getAccountByID(Long id) {
        
    return  (PojoAccFrozenTask)this.getSession().createCriteria(PojoAccFrozenTask.class)
     .add(Restrictions.eq("id", id)).uniqueResult();
       
    } 
}
