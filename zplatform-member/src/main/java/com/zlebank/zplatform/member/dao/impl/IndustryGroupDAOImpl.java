/* 
 * IndustryGroupDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.bean.IndustryGroupBean;
import com.zlebank.zplatform.member.dao.IndustryGroupDAO;
import com.zlebank.zplatform.member.pojo.PojoIndustryGroup;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:32:44
 * @since 
 */
@Repository
public class IndustryGroupDAOImpl extends HibernateBaseDAOImpl<PojoIndustryGroup> implements IndustryGroupDAO {

    /**
     *
     * @param groupBean
     * @return
     */
    @Override
    public PojoIndustryGroup getByCode(IndustryGroupBean groupBean) {
        Criteria criteria=getSession().createCriteria(PojoIndustryGroup.class);
        criteria.add(Restrictions.eq("groupCode", groupBean.getGroupCode()));
        return (PojoIndustryGroup) criteria.uniqueResult();
    }

   

}
