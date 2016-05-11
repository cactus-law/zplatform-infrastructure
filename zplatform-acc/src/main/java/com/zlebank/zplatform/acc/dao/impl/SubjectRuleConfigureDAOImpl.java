/* 
 * SubjectRuleConfigureDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年8月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.AccEntryRules;
import com.zlebank.zplatform.acc.bean.SQLColumnToBean;
import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.dao.SubjectRuleConfigureDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoSubjectRuleConfigure;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月30日 下午6:07:24
 * @since
 */
@Repository
public class SubjectRuleConfigureDAOImpl
        extends
            AbstractPagedQueryDAOImpl<PojoSubjectRuleConfigure, SubjectRule>
        implements
            SubjectRuleConfigureDAO {

    /**
     *
     * @param id
     * @return
     */
    @Override
    public PojoSubjectRuleConfigure getSubjectRule(long id) {
        return (PojoSubjectRuleConfigure) this.getSession().get(
                PojoSubjectRuleConfigure.class, id);
    }

    /**
     *
     * @param subject
     * @return
     */
    @Override
    public PojoSubjectRuleConfigure save(PojoSubjectRuleConfigure subject) {

        return this.merge(subject);
    }

    /**
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public PojoSubjectRuleConfigure getConfigure(long id, RuleStatusType status) {
        return (PojoSubjectRuleConfigure) this.getSession()
                .createCriteria(PojoSubjectRuleConfigure.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("status", status)).uniqueResult();

    }

    /**
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public PojoSubjectRuleConfigure getNeConfigure(long id) {
        return (PojoSubjectRuleConfigure) this.getSession()
                .createCriteria(PojoSubjectRuleConfigure.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.ne("status", RuleStatusType.LOGOUT))
                .uniqueResult();

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<AccEntryRules> getRulesByTxnInfo(TradeInfo info) {
        String busiCode = info.getBusiCode();
        String productId = info.getProductId();
        String channelId = info.getChannelId();
        if (isNull(busiCode) && isNull(productId) && isNull(channelId)) {
            return null;
        }
        String sql = "select t.id,t.txntype,t.acct_code,t.chnlcode,t.prdtcode,t.crdr,t.is_sync,t.orders,t.ordform,t.is_show   from T_ACC_ENTRY_RULE t  where nvl(trim(t.txntype), nvl(:busiCode,'&*')) = case when :busiCode is null then '&*' else :busiCode end    and nvl(trim(t.prdtcode), nvl(:productId,'&*')) = case when :productId is null then '&*' else :productId end    and nvl(trim(t.chnlcode), nvl(:channelId,'&*')) = case when :channelId is null then '&*' else :channelId end";
        SQLQuery query = getSession().createSQLQuery(sql.toString());
        query.setParameter("busiCode", busiCode == null ? "" : busiCode);
        query.setParameter("productId", productId == null ? "" : productId);
        query.setParameter("channelId", channelId == null ? "" : channelId);
        query.setResultTransformer(new SQLColumnToBean(AccEntryRules.class));
        @SuppressWarnings("unchecked")
        List<AccEntryRules> list = query.list();
        return list;
    }

    private boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    protected Criteria buildCriteria(SubjectRule e) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     * @param sar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getRuleBySar(SubjectAccountRule sar,
            Integer firset,
            Integer max) throws AccBussinessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select t.id as TID ,t.txntype,t.acct_code,t.crdr as TCR,  ");
        sb.append(" t.chnlcode,t.prdtcode,t.is_sync,t.Ordform, ");
        sb.append("  T.STATUS,T.INTIME,T.Inuser,t.uptime,t.upuser, ");
        sb.append(" t.notes,a.id,a.acct_code_name,a.crdr ");
        sb.append("from T_ACC_ENTRY_RULE t left join  t_acc_acct a on t.acct_code=a.acct_code ");
        sb.append("  where   t.status <>'99'  ");
        if (sar != null) {
            if (StringUtil.isNotEmpty(sar.getStatus())) {
                sb.append(" and t.status=:status");

            }
            if (StringUtil.isNotEmpty(sar.getId())) {
                sb.append(" and t.ID=:id");

            }
            if (StringUtil.isNotEmpty(sar.getCrdr())) {
                sb.append(" and t.crdr=:crdr");
            }
            if (StringUtil.isNotEmpty(sar.getIsSync())) {
                sb.append(" and t.is_sync=:sync");
            }
            if (StringUtil.isNotEmpty(sar.getTxnType())) {
                sb.append(" and txntype=:txntype ");
            }
            String sql = sb.toString();
            SQLQuery query = this.getSession().createSQLQuery(sql);

            if (StringUtil.isNotEmpty(sar.getStatus())) {
                query.setParameter("status", sar.getStatus());

            }
            if (StringUtil.isNotEmpty(sar.getCrdr())) {
                query.setParameter("crdr", sar.getCrdr());
            }
            if (StringUtil.isNotEmpty(sar.getIsSync())) {
                query.setParameter("sync", sar.getIsSync());
            }
            if (StringUtil.isNotEmpty(sar.getTxnType())) {
                query.setParameter("txntype", sar.getTxnType());
            }
            if (StringUtil.isNotEmpty(sar.getId())) {
                query.setParameter("id", sar.getId());

            }
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            if (firset == null || max == null) {
                throw new AccBussinessException("E100009");
            }
            query.setFirstResult(firset).setMaxResults(max);

            return query.list();
        } else {
            String sql = sb.toString();
            SQLQuery query = this.getSession().createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            if (firset == null || max == null) {
                throw new AccBussinessException("E100009");
            }
            query.setFirstResult(firset).setMaxResults(max);

            return query.list();
        }

    }

    public long getcount(SubjectAccountRule sar) {
        long all = 0L;
        SQLQuery query = null;
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) ");
        sb.append("from T_ACC_ENTRY_RULE t left join  t_acc_acct a on t.acct_code=a.acct_code ");
        sb.append("  where   t.status <>'99'  ");
        if (sar != null) {
            if (StringUtil.isNotEmpty(sar.getStatus())) {
                sb.append(" and t.status=:status");

            }
            if (StringUtil.isNotEmpty(sar.getId())) {
                sb.append(" and t.ID=:id");

            }
            if (StringUtil.isNotEmpty(sar.getCrdr())) {
                sb.append(" and t.crdr=:crdr");
            }
            if (StringUtil.isNotEmpty(sar.getIsSync())) {
                sb.append(" and t.is_sync=:sync");
            }
            if (StringUtil.isNotEmpty(sar.getTxnType())) {
                sb.append(" and txntype=:txntype ");
            }

            String sql = sb.toString();
            query = this.getSession().createSQLQuery(sql);

            if (StringUtil.isNotEmpty(sar.getStatus())) {
                query.setParameter("status", sar.getStatus());

            }
            if (StringUtil.isNotEmpty(sar.getId())) {
                query.setParameter("id", sar.getId());

            }
            if (StringUtil.isNotEmpty(sar.getCrdr())) {
                query.setParameter("crdr", sar.getCrdr());
            }
            if (StringUtil.isNotEmpty(sar.getIsSync())) {
                query.setParameter("sync", sar.getIsSync());
            }
            if (StringUtil.isNotEmpty(sar.getTxnType())) {
                query.setParameter("txntype", sar.getTxnType());
            }
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
     
    @SuppressWarnings("unchecked")
    @Override
    public List<PojoSubjectRuleConfigure> getRulesByBusiCode(String busiCode) {
        Criteria criteria = this.getSession().createCriteria(
                PojoSubjectRuleConfigure.class);
        criteria.add(Restrictions.eq("txntype", busiCode));
        criteria.add(Restrictions.eq("status", RuleStatusType.NORMAL));
        criteria.addOrder(Order.asc("ruleOder"));
        return criteria.list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<PojoSubjectRuleConfigure> getRulesByTradeAndEvent(String busiCode,
            EntryEvent entryEvent){
        Criteria criteria = this.getSession().createCriteria(
                PojoSubjectRuleConfigure.class);
        criteria.add(Restrictions.eq("txntype", busiCode));
        criteria.add(Restrictions.eq("entryEvent", entryEvent.getCode()));
        criteria.add(Restrictions.eq("status", RuleStatusType.NORMAL));
        criteria.addOrder(Order.asc("ruleOder"));
        return criteria.list();
    }
}
