/* 
 * EnterpriseRealnameApplyServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年8月22日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.member.dao.EnterpriseRealnameApplyDAO;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseRealnameApply;
import com.zlebank.zplatform.member.service.EnterpriseRealnameApplyService;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年8月22日 上午10:47:00
 * @since 
 */
@Service
public class EnterpriseRealnameApplyServiceImpl implements EnterpriseRealnameApplyService{

	@Autowired
	private EnterpriseRealnameApplyDAO enterpriseRealnameApplyDAO;

	/**
	 *
	 * @param enterpriseRealnameApply
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveEnterpriseRealnameApply(
			PojoEnterpriseRealnameApply enterpriseRealnameApply) {
		// TODO Auto-generated method stub
		enterpriseRealnameApplyDAO.saveA(enterpriseRealnameApply);
	}

	/**
	 *
	 * @param tn
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public PojoEnterpriseRealnameApply getDetaByTN(String tn) {
		// TODO Auto-generated method stub
		return enterpriseRealnameApplyDAO.getDetaByTN(tn);
	}

	/**
	 *
	 * @param enterpriseRealnameApply
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateApplyStatus(
			PojoEnterpriseRealnameApply enterpriseRealnameApply) {
		// TODO Auto-generated method stub
		enterpriseRealnameApplyDAO.update(enterpriseRealnameApply);
	}

	/**
	 *
	 * @param tid
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public PojoEnterpriseRealnameApply get(Long tid) {
		return enterpriseRealnameApplyDAO.getById(tid);
	}
	
	
}
