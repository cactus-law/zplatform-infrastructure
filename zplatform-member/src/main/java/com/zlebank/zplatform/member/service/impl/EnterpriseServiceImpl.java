/* 
 * CoopInstiMKServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年1月25日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.EnterpriseBean;
import com.zlebank.zplatform.member.dao.EnterpriseDAO;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseDeta;
import com.zlebank.zplatform.member.service.EnterpriseService;

/**
 * 合作机构密钥服务类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月25日 上午10:08:24
 * @since 
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    
    @Autowired
    EnterpriseDAO enterpriseDAO;
    
    /**
     * 根据memberId得到企业信息
     * @param memberId 
     * @return 企业信息
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public EnterpriseBean getEnterpriseByMemberId(String memberId) {
        PojoEnterpriseDeta pojo = enterpriseDAO.getEnterpriseByMemberId(memberId);
        EnterpriseBean bean = BeanCopyUtil.copyBean(EnterpriseBean.class, pojo);
        return bean;
    }
}
