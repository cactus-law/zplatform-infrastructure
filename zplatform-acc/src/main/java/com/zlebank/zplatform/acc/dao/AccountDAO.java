/* 
 * AccountDAO.java  
 * 
 * version 1.0
 *
 * 2015年8月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;

import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author luxiaoshuai
 * @version
 * @date 2015年8月20日 下午4:42:33
 * @since 
 */
public interface AccountDAO extends BaseDAO<PojoAccount>{
    /**
     * 根据id得到账户
     * @param id
     * @return
     */
    public PojoAccount get(long id);
    /**
     * 保存账户
     * @param account
     * @return
     */
    public PojoAccount save(PojoAccount account);
    /**
     * 根据账户号得到账户
     * @param accCode
     * @return
     */
    public PojoAccount getByAcctCode(String accCode);
    /**
     * 根据id得到账户并加锁（ForUpdate）
     * @param id
     * @return
     */
    public PojoAccount getByIdForUpdate(long id);
    /**
     * 通过会员ID得到该会员所有账户信息
     * @param memberId
     * @return List<PojoAccount>
     */
    public List<PojoAccount> getAllAccountByMId(String memberId);
    /**
     * 会计账户id得到该账户为未注销的会计账户
     * @param acctid
     * @return PojoAccount
     */
    public PojoAccount getByid(long acctid);
    
    /**
     * 得到指定序列
     * @param sequences
     * @return
     */
    public long getSequence(String sequences);
}
