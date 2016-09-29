/* 
 * IndustryGroupService.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import java.util.List;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.member.bean.IndustryGroupBean;
import com.zlebank.zplatform.member.bean.IndustryGroupCreatBean;
import com.zlebank.zplatform.member.bean.IndustryGroupQuery;

/**
 * 行业群组service
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:36:51
 * @since 
 */
public interface IndustryGroupService extends IBasePageService<IndustryGroupQuery,IndustryGroupBean>{
       /**
        * 添加行业群组
        * @param groupBean
     * @throws AbstractBusiAcctException 
        */
       public String addGroup(IndustryGroupCreatBean groupBean) throws AbstractBusiAcctException;
       /**
        * 修改行业群组
        * @param groupBean
        */
       public void updateGroup(IndustryGroupBean groupBean);
       /**
        * 查询群组
        * @param queryBean
        * @return
        */
       public IndustryGroupBean queryGroup(IndustryGroupQuery queryBean);
}
