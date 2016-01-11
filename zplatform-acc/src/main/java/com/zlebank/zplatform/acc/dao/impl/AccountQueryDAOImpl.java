/* 
 * AccountQueryDAOImpl.java  
 * 
 * version
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.dao.AccountQueryDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月7日 上午11:44:51
 * @since
 */
@Repository
public class AccountQueryDAOImpl extends HibernateBaseDAOImpl<PojoAccount>
        implements
            AccountQueryDAO {

    /**
     *
     * @param accountId
     * @return
     */
    @Override
    public Account getAccountByID(long accountId) {
        // this.getSession().createCriteria(persistentClass)
        return null;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws AccBussinessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getAccount(QueryAccount qa,
            Integer firset,
            Integer max) throws AccBussinessException {
        SQLQuery query = this.getSQLquery(qa);
        if (firset == null || max == null) {
            throw new AccBussinessException("E100009");
        }
        query.setFirstResult(firset).setMaxResults(max);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getAllAccountByMId(String memberId,
            String busiAcctCode) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select acc.status, acc.acct_type ,acc.MEMBERID  ");
        sb.append(" ,acc.balance,acc.frozen_balance,acc.total_balance  ");
        sb.append(",bus.acct_id,bus.busiacct_code,bus.busiacct_name  ");
        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where   acc.status <>'99'   ");
        if (memberId != null) {
            sb.append(" and bus.member_id=:memeberId ");
        }
        if (StringUtil.isNotEmpty(busiAcctCode)) {
            sb.append(" and bus.BUSIACCT_CODE=:busiAcctCode");
        }
        String sql = sb.toString();
        SQLQuery query = this.getSession().createSQLQuery(sql);

        if (StringUtil.isNotEmpty(busiAcctCode)) {
            query.setParameter("busiAcctCode", busiAcctCode);
        }
        if (StringUtil.isNotEmpty(memberId)) {
            query.setParameter("memeberId", memberId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        /*
         * query.setResultTransformer(new SQLColumnToBean( MemberQuery.class));
         */
        // @SuppressWarnings("unchecked")
        // List<Map<String, String> > li=query.list();

        return query.list();

    }

    private SQLQuery getSQLquery(QueryAccount qa) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select acc.status, acc.acct_type ,acc.ACCT_CODE ,acc.MEMBERID ");
        sb.append(" ,acc.balance,acc.frozen_balance,acc.total_balance  ");
        sb.append(",bus.acct_id,bus.busiacct_code,bus.busiacct_name ,bus.USAGE  ");
        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where   acc.status <>'99'   ");
        if (qa != null) {
            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                sb.append(" and bus.member_id=:memeberId ");
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                sb.append(" and bus.BUSIACCT_CODE=:busiAcctCode");
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                sb.append(" and acc.STATUS=:accStatus");
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                sb.append(" and bus.USAGE=:usage");
            }
            // 会计账户号
            if (StringUtil.isNotEmpty(qa.getAccCode())) {
                sb.append(" and acc.ACCT_CODE=:accCode");
            }

            String sql = sb.toString();
            SQLQuery query = this.getSession().createSQLQuery(sql);

            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                query.setParameter("memeberId", qa.getMemberId());
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                query.setParameter("busiAcctCode", qa.getBusiCode());
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                query.setParameter("accStatus", qa.getAccStatus());
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                query.setParameter("usage", qa.getUsage());
            }
            // 会计账户号
            if (StringUtil.isNotEmpty(qa.getAccCode())) {
                query.setParameter("accCode", qa.getAccCode());
            }

            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query;
        } else {
            String sql = sb.toString();
            SQLQuery query = this.getSession().createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            return query;
        }

    }

    /**
     *
     * @param qa
     * @return
     */
    @Override
    public Long getcount(QueryAccount qa) {
        Long all = 0L;
        SQLQuery query = null;
        StringBuffer sb = new StringBuffer();
        sb.append(" select count(*) ");
        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where   acc.status <>'99'   ");
        if (qa != null) {
            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                sb.append(" and bus.member_id=:memeberId ");
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                sb.append(" and bus.BUSIACCT_CODE=:busiAcctCode");
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                sb.append(" and acc.STATUS=:accStatus");
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                sb.append(" and bus.USAGE=:usage");
            }
            // 会计账户号
            if (StringUtil.isNotEmpty(qa.getAccCode())) {
                sb.append(" and acc.ACCT_CODE=:accCode");
            }

            String sql = sb.toString();
            query = this.getSession().createSQLQuery(sql);

            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                query.setParameter("memeberId", qa.getMemberId());
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                query.setParameter("busiAcctCode", qa.getBusiCode());
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                query.setParameter("accStatus", qa.getAccStatus());
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                query.setParameter("usage", qa.getUsage());
            }
            // 会计账户号
            if (StringUtil.isNotEmpty(qa.getAccCode())) {
                query.setParameter("accCode", qa.getAccCode());
            }
            
            // query.setParameter("chentStatus", qa.getChectStatus());

        } else {

            String sql = sb.toString();
            query = this.getSession().createSQLQuery(sql);

        }
        BigDecimal size = (BigDecimal) query.uniqueResult();
        if (size != null) {
            all = size.longValue();

        }
        return all;

    }

}
