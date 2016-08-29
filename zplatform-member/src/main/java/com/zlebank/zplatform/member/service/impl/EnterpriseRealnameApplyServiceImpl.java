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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.EnterpriseRealNameBean;
import com.zlebank.zplatform.member.bean.EnterpriseRealNameQueryBean;
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
public class EnterpriseRealnameApplyServiceImpl extends AbstractBasePageService<EnterpriseRealNameQueryBean, EnterpriseRealNameBean> implements EnterpriseRealnameApplyService{

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

    /**
     *
     * @param example
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    protected long getTotal(EnterpriseRealNameQueryBean example) {
        enterpriseRealnameApplyDAO.count(example);
        return 0;
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    protected List<EnterpriseRealNameBean> getItem(int offset,
            int pageSize,
            EnterpriseRealNameQueryBean example) {
        List<PojoEnterpriseRealnameApply> pojoRealNames= enterpriseRealnameApplyDAO.getItem(offset,pageSize,example);
        List<EnterpriseRealNameBean> realNameBeans=new ArrayList<EnterpriseRealNameBean>();
        for (PojoEnterpriseRealnameApply pojoEnterpriseRealnameApply : pojoRealNames) {
            EnterpriseRealNameBean realnameBean=new EnterpriseRealNameBean();
            realnameBean=BeanCopyUtil.copyBean(EnterpriseRealNameBean.class, pojoEnterpriseRealnameApply);
            realNameBeans.add(realnameBean);
        }
        return realNameBeans;
    }

	
}
