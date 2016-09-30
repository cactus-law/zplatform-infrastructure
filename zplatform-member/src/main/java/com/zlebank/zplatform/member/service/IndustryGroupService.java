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


import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.member.bean.IndustryGroupBean;
import com.zlebank.zplatform.member.bean.IndustryGroupCreatBean;
import com.zlebank.zplatform.member.bean.IndustryGroupQuery;
import com.zlebank.zplatform.member.exception.ExistedDataException;
import com.zlebank.zplatform.member.exception.NotFoundDataException;

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
        * @throws NotFoundDataException 
        * @throws ExistedDataException 
        */
       public String addGroup(IndustryGroupCreatBean groupBean) throws AbstractBusiAcctException, ExistedDataException, NotFoundDataException;
       /**
        * 修改行业群组
        * @param groupBean
        * @throws NotFoundDataException 
        */
       public void updateGroup(IndustryGroupBean groupBean) throws NotFoundDataException;
       /**
        * 查询会员是否有可用群组
        * @param queryBean
        * @return
        */
       public IndustryGroupBean queryGroupExist(String memberId,String instiCode);
       
       /**
        * 根据id或代码查询群组信息
        * @param groupId
        * @param groupCode
        * @return
        */
       public IndustryGroupBean queryGroupByCodeOrId(long groupId,String groupCode);
}
