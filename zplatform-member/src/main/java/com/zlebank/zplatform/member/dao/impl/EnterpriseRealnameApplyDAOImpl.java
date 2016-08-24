/* 
 * EnterpriseRealnameApplyDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年8月22日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.dao.EnterpriseRealnameApplyDAO;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseDeta;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseDetaApply;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseRealnameApply;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年8月22日 上午10:42:22
 * @since
 */
@Repository("enterpriseRealnameApplyDAO")
public class EnterpriseRealnameApplyDAOImpl extends
		HibernateBaseDAOImpl<PojoEnterpriseRealnameApply> implements
		EnterpriseRealnameApplyDAO {
	/**
	 *
	 * @param tn
	 * @return
	 */
	@Override
	public PojoEnterpriseRealnameApply getDetaByTN(String tn) {
		Criteria crite = this.getSession().createCriteria(
				PojoEnterpriseRealnameApply.class);
		crite.add(Restrictions.eq("tn", tn));
		PojoEnterpriseRealnameApply apply = (PojoEnterpriseRealnameApply) crite
				.uniqueResult();
		return apply;

	}

	/**
	 *
	 * @param tid
	 * @return
	 */
	@Override
	public PojoEnterpriseRealnameApply getById(Long tid) {
		Criteria crite = this.getSession().createCriteria(
				PojoEnterpriseRealnameApply.class);
		crite.add(Restrictions.eq("tid", tid));
		PojoEnterpriseRealnameApply apply = (PojoEnterpriseRealnameApply) crite
				.uniqueResult();
		return apply;
	}
}
