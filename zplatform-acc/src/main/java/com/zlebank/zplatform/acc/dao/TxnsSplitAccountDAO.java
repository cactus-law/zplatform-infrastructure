/* 
 * BankInfoDAO.java  
 * 
 * version v1.0
 *
 * 2015年9月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;

import com.zlebank.zplatform.acc.pojo.PojoTxnsSplitAccount;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * 分账信息
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月18日 下午3:48:31
 * @since 
 */
public interface TxnsSplitAccountDAO extends BaseDAO<PojoTxnsSplitAccount> {
        /**
         * 通过交易序列号得到分账信息
         * @param txnno
         * @return PojoBankInfo
         */
    public List<PojoTxnsSplitAccount> getByTxnseqno(String txnseqno);
}
