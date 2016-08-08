/* 
 * AccountQueryServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.AccountQueryDAO;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.mock.AccEntryTest;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcct;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.AccountQueryService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月7日 下午2:15:09
 * @since 
 */
@Service
public class AccountQueryServiceImpl extends AbstractBasePageService<QueryAccount, BusiAcctQuery> implements AccountQueryService {
    @Autowired
    private AccountQueryDAO accountQueryDAO;
    @Autowired
    private AccountDAO accountDao;
    @Autowired
    private AccEntryService  entry;
    @Autowired
    private BusiAcctDAO busiDao;
    /**
     *
     * @param memberId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Account> getAllAccountByMId(String memberId) {
        List<PojoAccount> li = accountDao.getAllAccountByMId(memberId);
        List<Account> accountLi = new ArrayList<Account>();
        for (PojoAccount account : li) {
            Account accountVo = BeanCopyUtil.copyBean(Account.class, account);
            if (account.getParentSubject() != null) {
                accountVo.setParentSubject(BeanCopyUtil.copyBean(Subject.class,
                        account.getParentSubject()));
            }
            accountLi.add(accountVo);
        }
        return accountLi;
    }
    
    /**
     *
     * @param accountId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Account getAccountByID(long accountId) {
   PojoAccount accountPo=     accountDao.getByid(accountId);
   Account accountVo =    BeanCopyUtil.copyBean(Account.class, accountPo);
   if (accountPo.getParentSubject() != null) {
       accountVo.setParentSubject(BeanCopyUtil.copyBean(Subject.class,
               accountPo.getParentSubject()));
  
   }
   return accountVo;
    }

    /**
     *
     * @param busiAcctCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BusiAcctQuery getMemberQueryByID(String busiAcctCode) {
      PojoBusiAcct busipo= busiDao.getByBusiAcctCode(busiAcctCode);
      if(busipo==null)
          return null;
     PojoAccount accountpo=  accountDao.getByid(busipo.getAccountId());
      BusiAcctQuery mQuery=     BeanCopyUtil.copyBean(BusiAcctQuery.class, accountpo);
        return mQuery;
    }

    /**
     *
     * @param busiAcctCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public  PagedResult<AccEntry>  getAccEntryByCode(int page,int pageSize, String busiAcctCode, Date startTime,Date endTime) {
       BusiAcctQuery mq= this.getMemberQueryByID(busiAcctCode);
        AccEntryQuery eQuery=new AccEntryQuery();
        eQuery.setAcctCode(mq.getAcctCode());
        eQuery.setEndTime(endTime);
        eQuery.setStartTime(startTime);
     PagedResult<AccEntry> entrys= entry.queryPaged(page, pageSize, eQuery);
        return entrys;
    }

    /**
     *
     * @param page
     * @param pageSize
     * @param eQuery
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PagedResult<AccEntry> getAccEntryByQuery(int page,
            int pageSize,
            AccEntryQuery eQuery) {
        PagedResult<AccEntry> entrys= entry.queryPaged(page, pageSize, eQuery);
        return entrys;
    }
   
    /**
     *
     * @param memberid
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BusiAcct> getBusiACCByMid(String memberid) {
    List<PojoBusiAcct> busi=  busiDao.getAllbusiByMid(memberid);
    List<BusiAcct> busiLi=new ArrayList<BusiAcct>();    
    for(PojoBusiAcct busipo:busi){
       busiLi.add(BeanCopyUtil.copyBean(BusiAcct.class, busipo));
        
    }
    return busiLi;
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<BusiAcctQuery> getAllBusiByMId(String memberId) {
    List<Map<String, Object>>li=   accountQueryDAO.getAllAccountByMId(memberId, null);
    List<BusiAcctQuery> listMem=new ArrayList<BusiAcctQuery>();
    for(Map<String, Object> map:li){
        BusiAcctQuery mq=new BusiAcctQuery();
        mq.setAcctCode(map.get("ACCT_CODE")+"");
        mq.setStatus(AcctStatusType.fromValue(map.get("STATUS")+""));   
        mq.setAcctType(AcctType.fromValue(map.get("ACCT_TYPE")+"")); 
        mq.setBalance(Money.valueOf( Double.valueOf((map.get("BALANCE")==null?0.0:map.get("BALANCE"))+"")));
        mq.setFronzenBalance(Money.valueOf(Double.valueOf((map.get("FROZEN_BALANCE")==null?0.0:map.get("FROZEN_BALANCE"))+"")));
        mq.setTotalBalance(Money.valueOf( Double.valueOf((map.get("TOTAL_BALANCE")==null?0.0:map.get("TOTAL_BALANCE"))+"")));
        mq.setAcctId( map.get("ACCT_ID")+"");
         mq.setBusiAcctCode(map.get("BUSIACCT_CODE")+"");
         mq.setBusiAcctName(map.get("BUSIACCT_NAME")+"");
         mq.setUsage(Usage.fromValue(map.get("USAGE")+""));
         mq.setMemberID(map.get("BUSINESS_ACTOR_ID")+"");
         listMem.add(mq);
      }
    return listMem;
    }

    /**
     *
     * @param busiAcctCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BusiAcctQuery getBusiQueryBybCode(String busiAcctCode) {
        List<Map<String, Object>>li=    accountQueryDAO.getAllAccountByMId(null, busiAcctCode);
        if(li.size()<=0){
            return null;
        }
        BusiAcctQuery mq=new BusiAcctQuery();
        for(Map<String, Object> map:li){
             mq.setAcctCode(map.get("ACCT_CODE")+"");
             mq.setStatus(AcctStatusType.fromValue(map.get("STATUS")+""));   
             mq.setAcctType(AcctType.fromValue(map.get("ACCT_TYPE")+"")); 
             mq.setBalance(Money.valueOf( Double.valueOf((map.get("BALANCE")==null?0.0:map.get("BALANCE"))+"")));
             mq.setFronzenBalance(Money.valueOf(Double.valueOf((map.get("FROZEN_BALANCE")==null?0.0:map.get("FROZEN_BALANCE"))+"")));
             mq.setTotalBalance(Money.valueOf( Double.valueOf((map.get("TOTAL_BALANCE")==null?0.0:map.get("TOTAL_BALANCE"))+"")));
             mq.setAcctId( map.get("ACCT_ID")+"");
              mq.setBusiAcctCode(map.get("BUSIACCT_CODE")+"");
              mq.setBusiAcctName(map.get("BUSIACCT_NAME")+"");
              mq.setUsage(Usage.fromValue((map.get("USAGE")+"")));
              mq.setMemberID(map.get("BUSINESS_ACTOR_ID")+"");
    }
        return mq;
 
    }

    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(QueryAccount example) {
     return accountQueryDAO.getcount(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<BusiAcctQuery> getItem(int offset,
            int pageSize,
            QueryAccount example) {
     
       
        List<Map<String, Object>> li;
        List<BusiAcctQuery> listMem=new ArrayList<BusiAcctQuery>();
        try {
            li = accountQueryDAO.getAccount(example, offset,pageSize);
         
            for(Map<String, Object> map:li){
                BusiAcctQuery mq=new BusiAcctQuery();
                mq.setAcctCode(map.get("ACCT_CODE")+"");
                mq.setStatus(AcctStatusType.fromValue(map.get("STATUS")+""));   
                mq.setAcctType(AcctType.fromValue(map.get("ACCT_TYPE")+"")); 
                mq.setBalance(Money.valueOf( Double.valueOf((map.get("BALANCE")==null?0.0:map.get("BALANCE"))+"")));
                mq.setFronzenBalance(Money.valueOf(Double.valueOf((map.get("FROZEN_BALANCE")==null?0.0:map.get("FROZEN_BALANCE"))+"")));
                mq.setTotalBalance(Money.valueOf( Double.valueOf((map.get("TOTAL_BALANCE")==null?0.0:map.get("TOTAL_BALANCE"))+"")));
                mq.setAcctId( map.get("ACCT_ID")+"");
                mq.setBusiAcctCode(map.get("BUSIACCT_CODE")+"");
                mq.setBusiAcctName(map.get("BUSIACCT_NAME")+"");
                mq.setUsage(Usage.fromValue(map.get("USAGE")+""));
                mq.setMemberID(map.get("BUSINESS_ACTOR_ID")+"");
                listMem.add(mq);
            }  } catch (AccBussinessException e) {
           
           return null;
        }
         
        return listMem;
    }

    
    
    
    
    
    
    
    
    
    
    /**
     * -----------------------------------------------------------Following is test-----------
     */
    /**
     *
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public List<BusiAcctQuery> getTestAccountInfoByMidAndUsage(QueryAccount account) {
       /* List<Map<String, Object>>li=   accountQueryDAO.TestAccountInfoByMidAndUsage(account);*/
        List<BusiAcctQuery> listMem=new ArrayList<BusiAcctQuery>();
       /* for(Map<String, Object> map:li){
            BusiAcctQuery mq=new BusiAcctQuery();
            mq.setAcctCode(map.get("ACCT_CODE")+"");
            mq.setStatus(AcctStatusType.fromValue(map.get("STATUS")+""));   
            mq.setAcctType(AcctType.fromValue(map.get("ACCT_TYPE")+"")); 
            mq.setBalance(Money.valueOf( Double.valueOf((map.get("BALANCE")==null?0.0:map.get("BALANCE"))+"")));
            mq.setFronzenBalance(Money.valueOf(Double.valueOf((map.get("FROZEN_BALANCE")==null?0.0:map.get("FROZEN_BALANCE"))+"")));
            mq.setTotalBalance(Money.valueOf( Double.valueOf((map.get("TOTAL_BALANCE")==null?0.0:map.get("TOTAL_BALANCE"))+"")));
            mq.setAcctId( map.get("ACCT_ID")+"");
             mq.setBusiAcctCode(map.get("BUSIACCT_CODE")+"");
             mq.setBusiAcctName(map.get("BUSIACCT_NAME")+"");
             mq.setUsage(Usage.fromValue(map.get("USAGE")+""));
             mq.setMemberID(map.get("BUSINESS_ACTOR_ID")+"");
             listMem.add(mq);
        }*/
        return listMem;
    }
    
    @Transactional
    public List<AccEntryTest> getTestItem(int page,int pageSize,AccEntryQuery eQuery){
        return entry.getTestItem(page, pageSize, eQuery);
    }
}
