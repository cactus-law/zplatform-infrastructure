/* 
 * BusiAcctDAO.java  
 * 
 * version 1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.exception.BusiAcctNotExistException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcct;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月31日 下午12:06:01
 * @since 
 */
public interface BusiAcctDAO extends BaseDAO<PojoBusiAcct>{
    /**
     * 
     * @param busiAcctCode
     * @return null if not exist
     */
    public PojoBusiAcct getByBusiAcctCode(String busiAcctCode);
    /**
     * 通过 用途和参与方id得到会计账户id
     * @param usage
     * @param memberId
     * @return account id
     * @throws BusiAcctNotExistException If not exist
     */
    public long getAccount(Usage usage,String businessActorId)throws BusiAcctNotExistException;
    
    public List<PojoBusiAcct> getAllbusiByMid(String memberId);
    
    /**
     *  通过会员ID 和 用途 得到业务账户
     * @param usage
     * @param memberId
     * @return
     * @throws BusiAcctNotExistException 
     */
    public PojoBusiAcct getBusiCode(Usage usage, String memberId) throws BusiAcctNotExistException;
    /**
     * 通过 用途和参与方id得到会计账户号
     * @param usage
     * @param businessActorId
     * @return
     */
    public String getAccCodeByUsageAndBusiActorId(Usage usage,String businessActorId);
    }
