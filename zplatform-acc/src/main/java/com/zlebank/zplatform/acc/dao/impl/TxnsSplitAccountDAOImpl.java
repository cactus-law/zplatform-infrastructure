/* 
 * BankInfoDAOImpl.java  
 * 
 * version v1.0
 *
 * 2015年9月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.dao.TxnsSplitAccountDAO;
import com.zlebank.zplatform.acc.pojo.PojoTxnsSplitAccount;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月18日 下午3:48:40
 * @since 
 */
@Repository
public class TxnsSplitAccountDAOImpl   extends HibernateBaseDAOImpl<PojoTxnsSplitAccount>implements TxnsSplitAccountDAO {

    /**
     * 通过交易序列号得到分账信息
     * @param txnseqno
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PojoTxnsSplitAccount> getByTxnseqno(String txnseqno) {
        Criteria crite= getSession().createCriteria(PojoTxnsSplitAccount.class);
        crite.add(Restrictions.eq("txnseqno", txnseqno));
        return crite.list();
    }
}
