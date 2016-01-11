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
     * 
     * @param usage
     * @param memberId
     * @return account id
     * @throws BusiAcctNotExistException If not exist
     */
    public long getAccount(Usage usage,String memberId)throws BusiAcctNotExistException;
    
    public List<PojoBusiAcct> getAllbusiByMid(String memberId);
    
    }
