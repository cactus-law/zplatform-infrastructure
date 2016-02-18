/* 
 * AccEntryDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.dao.AccEntryDAO;
import com.zlebank.zplatform.acc.pojo.PojoAccEntry;
import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 下午5:12:54
 * @since 
 */
@Repository
public class AccEntryDAOImpl  extends AbstractPagedQueryDAOImpl<PojoAccEntry,AccEntryQuery> implements AccEntryDAO {

    /**
     *
     * @param e
     * @return
     */
    @Override
    protected Criteria buildCriteria(AccEntryQuery e) {
       Criteria crite= this.getSession().createCriteria(PojoAccEntry.class);
       //科目号
       if(StringUtil.isNotEmpty(e.getAcctCode())){
           
           crite.add(Restrictions.eq("acctCode", e.getAcctCode()));
       }
       //交易类型代码
       if(e.getBusiCode()!=null){
           crite.add(Restrictions.eq("busiCode", e.getBusiCode()));
       }
       //时间
       if(e.getEndTime()!=null){
           
        
           crite.add(Restrictions.le("inTime",e.getEndTime())); 
       }
       if(e.getStartTime()!=null){
           crite.add(Restrictions.ge("inTime",e.getStartTime())); 
       }
       //状态
       if(e.getStatus()!=null){
           crite.add(Restrictions.eq("status", e.getStatus()));
           
       }
       //交易流水号
       if(StringUtil.isNotEmpty(e.getTxnseqno())){
         crite.add(Restrictions.eqOrIsNull("txnseqno", e.getTxnseqno()))  ;
           
       }
       //凭证号
       if(e.getVoucherCode()!=null){
         crite.add(Restrictions.eqOrIsNull("voucherCode", e.getVoucherCode()))  ;
           
       }
       //支付订单号
       if(StringUtil.isNotEmpty(e.getPayordno())){
         crite.add(Restrictions.eqOrIsNull("payordno", e.getPayordno()))  ;
           
       }
       crite.addOrder(Order.desc("inTime"));
       return crite;
          
    }
	/**
	 * 
	 * @param id
	 * @return
	 */
    public PojoAccEntry get(long id) {
        return (PojoAccEntry) getSession().get(PojoAccEntry.class, id);
    }
	/**
	 *
	 * @param fetchSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
    @Override
	public List<PojoAccEntry> getLockRecords(int fetchSize) {
		Criteria criteria = getSession().createCriteria(PojoAccEntry.class);
        // 乐观锁
        criteria.add(Restrictions.eq("isLock", LockStatusType.UNLOCK));
        criteria.add(Restrictions.eq("status", AccEntryStatus.WAIT_ACCOUNTED));
        criteria.setMaxResults(fetchSize);
        return criteria.list();
	}

	/**
	 *
	 * @param id
	 */
	@Override
	public PojoAccEntry getByIdForUpdate(long voucherCode) {
		Criteria criteria = getSession().createCriteria(PojoAccEntry.class);
        criteria.add(Restrictions.eq("voucherCode", voucherCode));
        // 悲观锁
        criteria.setLockMode(LockMode.UPGRADE_NOWAIT);
        return (PojoAccEntry) criteria.uniqueResult();
		
	}
    /**
     * 根据交易流水号得到分录流水
     * @param txnseqno
     * @param busiCode
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PojoAccEntry> getByTxnNo(String txnseqno, String busiCode) {
        Criteria criteria = getSession().createCriteria(PojoAccEntry.class);
        criteria.add(Restrictions.eq("txnseqno", txnseqno));
        criteria.add(Restrictions.eq("busiCode", busiCode));
        return criteria.list();
    }
    
}
