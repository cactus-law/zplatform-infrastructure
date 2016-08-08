/* 
 * CheckAccount.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.service.IBasePageService;

/**
 * Class Description
 *账户查询 功能有
 *业务账户查询
 *业务账户余额查询
 *业务账户状态查询
 *分录流水查询
 * @author yangpeng
 * @version
 * @date 2015年9月7日 上午10:00:47
 * @since 
 */
public interface AccountQueryService extends IBasePageService<QueryAccount, BusiAcctQuery>{
    /**
     * 根据会员ID得到所有会计账户信息
     * @param memberId 
     * @return List<Account>
     */
     public List<Account> getAllAccountByMId(String memberId);
    
    /**
     * 根据业务账户号得到会计账户
     * @param accountId
     * @return Account
     */
    public BusiAcctQuery getMemberQueryByID(String busiAcctCode);
    
    /**
     * 根据业务账户号得到分录流水
     * @param accountId
     * @return List<AccEntry>
     */
    public  PagedResult<AccEntry>  getAccEntryByCode(int page,int pageSize, String busiAcctCode, Date startTime,Date endTime);
    
    /**
     * @param accountId
     * @return Account
     * 根据会计账户ID得到会计账户信息
     */
    public Account getAccountByID(long accountId);
    
    /**
     * 根据条件得到分录流水
     * @param accountId
     * @return List<AccEntry>
     */
    public  PagedResult<AccEntry>  getAccEntryByQuery(int page,int pageSize,AccEntryQuery eQuery);
        /**
         * 根据会员号得到业务账户
         * @param memberid
         * @return BusiAcct
         */
    public List<BusiAcct> getBusiACCByMid(String memberid);
        /**
         * 根据会员号得到所有账户信息
         * @param memberId
         * 
         * @return List<MemberQuery>
         */
    public List<BusiAcctQuery> getAllBusiByMId(String memberId);
    /**
     * 根据业务账户号得到账户信息
     * @param busiAcctCode
     * @return List<MemberQuery>
     */
    public BusiAcctQuery getBusiQueryBybCode(String busiAcctCode);

    /**
     * @param memberId
     * @param useage
     * @return
     */
    List<BusiAcctQuery> getTestAccountInfoByMidAndUsage(QueryAccount account);
        
}
