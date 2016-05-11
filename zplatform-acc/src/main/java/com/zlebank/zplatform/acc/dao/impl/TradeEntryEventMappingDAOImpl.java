package com.zlebank.zplatform.acc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.dao.TradeEntryEventMappingDAO;
import com.zlebank.zplatform.acc.pojo.PojoTradeEntryEventMapping;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
@Repository
public class TradeEntryEventMappingDAOImpl
        extends
            HibernateBaseDAOImpl<PojoTradeEntryEventMapping>
        implements
            TradeEntryEventMappingDAO {
    @SuppressWarnings("unchecked")
    @Override
    public List<PojoTradeEntryEventMapping> queryAll() {
        Criteria criteria = getSession().createCriteria(
                PojoTradeEntryEventMapping.class);
        return (List<PojoTradeEntryEventMapping>) criteria.list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<PojoTradeEntryEventMapping> queryAllSorted() {
        Criteria criteria = getSession().createCriteria(
                PojoTradeEntryEventMapping.class);
        criteria.addOrder(Order.asc("tradeType"));
        criteria.addOrder(Order.asc("entryEvent"));
        return (List<PojoTradeEntryEventMapping>) criteria.list();
    }
}
