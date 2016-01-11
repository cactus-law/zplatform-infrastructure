/* 
 * AccountQueryDAO.java  
 * 
 * version TODO
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * 根据会员号得到所有业务账户信息
 * 根据业务账户得到会计账户信息
 * 根据会计账户得到会计账户信息
 * 根据会计账户得到分录流水
 * 根据业务账户得到分录流水
 * 
 * Class Description
 *账户查询DAO
 * @author yangpeng
 * @version
 * @date 2015年9月7日 上午11:38:50
 * @since 
 */
public interface AccountQueryDAO extends BaseDAO<PojoAccount>{
    /**
     * 通过会员ID得到该会员所有账户信息
     * @param memberId
     * @return List<PojoAccount>
     */
    public List<Map<String,Object>> getAllAccountByMId(String memberId,String busiAcctCode);
    /**
     * 通过条件得到会员信息
     * @param memberId
     * @return List<PojoAccount>
     */
    public List<Map<String,Object>> getAccount(QueryAccount qa,Integer page,Integer pageSize)throws AccBussinessException;
    
    
    
    /**
     * 根据业务账户ID得到会计账户
     * @param accountId
     * @return Account
     */
    public Account getAccountByID(long accountId);
    
    
    public Long getcount(QueryAccount qa);
    

}
