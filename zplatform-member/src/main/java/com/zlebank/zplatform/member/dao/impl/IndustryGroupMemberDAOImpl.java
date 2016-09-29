/* 
 * IndustryGroupMemberDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;
import com.zlebank.zplatform.member.dao.IndustryGroupMemberDAO;
import com.zlebank.zplatform.member.pojo.PojoIndustryGroupMember;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:35:11
 * @since 
 */
@Repository
public class IndustryGroupMemberDAOImpl extends HibernateBaseDAOImpl<PojoIndustryGroupMember> implements  IndustryGroupMemberDAO {

    private Criteria buildCriteria(InduGroupMemberQuery queryBean){
        Criteria criteria=getSession().createCriteria(PojoIndustryGroupMember.class);
        if (StringUtil.isNotEmpty(queryBean.getGroupCode())) {
            criteria.add(Restrictions.eq("groupCode", queryBean.getGroupCode()));
        }
        if (StringUtil.isNotEmpty(queryBean.getMemberId())) {
            criteria.add(Restrictions.eq("memberId", queryBean.getMemberId()));
        }
        if (queryBean.getUsage()!=null) {
            criteria.add(Restrictions.eq("usage", queryBean.getUsage()));
        }
        if (queryBean.getStatus()!=null) {
            criteria.add(Restrictions.eq("usage",queryBean.getStatus()));
        }
        return criteria;
    }
    /**
     *
     * @param queryBean
     * @return
     */
    @Override
    public List<PojoIndustryGroupMember> queryGroupMember(InduGroupMemberQuery queryBean) {
        Criteria cri=buildCriteria(queryBean);
        cri.addOrder(Order.desc("inTime"));
        return cri.list();
        
    }
    /**
     *
     * @param offset
     * @param pageSize
     * @param queryBean
     * @return
     */
    @Override
    public List<PojoIndustryGroupMember> getItem(int offset,
            int pageSize,
            InduGroupMemberQuery queryBean) {
        Criteria cri=buildCriteria(queryBean);
        cri.setFirstResult(offset).setMaxResults(pageSize);
        cri.addOrder(Order.desc("inTime"));
        return cri.list();
    }
    /**
     *
     * @param queryBean
     * @return
     */
    @Override
    public long count(InduGroupMemberQuery queryBean) {
        Criteria cri=buildCriteria(queryBean);
        return cri.list().size();
    }

    

}
