/* 
 * IndustryGroupServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.member.bean.IndustryGroupBean;
import com.zlebank.zplatform.member.bean.IndustryGroupQuery;
import com.zlebank.zplatform.member.dao.IndustryGroupDAO;
import com.zlebank.zplatform.member.pojo.PojoIndustryGroup;
import com.zlebank.zplatform.member.service.IndustryGroupService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:42:47
 * @since 
 */
@Service
public class IndustryGroupServiceImpl extends AbstractBasePageService<IndustryGroupQuery,IndustryGroupBean> implements IndustryGroupService{
    @Autowired
    private IndustryGroupDAO industryGroupDao;
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(IndustryGroupQuery example) {
        // TODO Auto-generated method stub
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
    protected List<IndustryGroupBean> getItem(int offset,
            int pageSize,
            IndustryGroupQuery example) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     * @param groupBean
     * @return 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public String addGroup(IndustryGroupBean groupBean) {
        PojoIndustryGroup pojoInduGroup=new PojoIndustryGroup();
        pojoInduGroup=BeanCopyUtil.copyBean(PojoIndustryGroup.class, groupBean);
        pojoInduGroup.setInTime(new Date());
        pojoInduGroup.setUseable("0");
        pojoInduGroup.setGroupCode(generateGroupCode(groupBean));
        pojoInduGroup=industryGroupDao.merge(pojoInduGroup);
        return pojoInduGroup.getGroupCode();
    }
    
    private String generateGroupCode(IndustryGroupBean groupBean){
        String codePrefix=groupBean.getMemberId().substring(4);
        return codePrefix;
    }

    /**
     *
     * @param groupBean
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateGroup(IndustryGroupBean groupBean) {
        PojoIndustryGroup pojoInduGroup=industryGroupDao.getByCode(groupBean);
        pojoInduGroup.setDrawable(groupBean.getDrawable());
        pojoInduGroup.setNote(groupBean.getNote());
        pojoInduGroup.setGroupName(groupBean.getGroupName());
        pojoInduGroup.setUpTime(new Date());
        industryGroupDao.update(pojoInduGroup);
    }
}
