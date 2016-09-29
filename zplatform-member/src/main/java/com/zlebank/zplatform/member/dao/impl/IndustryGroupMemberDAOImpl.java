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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
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

	/**
	 *
	 * @param memberId
	 * @param groupCode
	 * @return
	 */
	@Override
	public PojoIndustryGroupMember getGroupMemberByMemberIdAndGroupCode(String memberId, String groupCode) {
		Criteria criteria = getSession().createCriteria(PojoIndustryGroupMember.class);
		criteria.add(Restrictions.eq("memberId", memberId));
		criteria.add(Restrictions.eq("groupCode", groupCode));
		criteria.add(Restrictions.not(Restrictions.eq("usage", Usage.GRANTCREDIT)));
		return (PojoIndustryGroupMember) criteria.uniqueResult();
	}

    

}
