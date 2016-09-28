/* 
 * IndustryGroupMemberServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.member.bean.InduGroupMemberBean;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;
import com.zlebank.zplatform.member.dao.IndustryGroupMemberDAO;
import com.zlebank.zplatform.member.service.IndustryGroupMemberService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:43:40
 * @since 
 */
@Service
public class IndustryGroupMemberServiceImpl extends AbstractBasePageService<InduGroupMemberQuery, InduGroupMemberBean> implements IndustryGroupMemberService {
    @Autowired
    private IndustryGroupMemberDAO induGroupMemberDao;
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(InduGroupMemberQuery example) {
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
    protected List<InduGroupMemberBean> getItem(int offset,
            int pageSize,
            InduGroupMemberQuery example) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     * @param bean
     */
    @Override
    public void addMemberToGroup(InduGroupMemberBean bean) {
        // TODO Auto-generated method stub
        
    }

}
