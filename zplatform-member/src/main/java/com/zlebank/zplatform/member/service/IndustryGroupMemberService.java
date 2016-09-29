/* 
 * IndustryGroupMemberService.java  
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
import com.zlebank.zplatform.member.bean.InduGroupMemberBean;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;

/**
 * 行业群组<>会员关系Service
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:37:39
 * @since 
 */
public interface IndustryGroupMemberService extends IBasePageService<InduGroupMemberQuery, InduGroupMemberBean>{
       /**
        * 将会员加入行业群组
        * @param bean
        * @param openAcct 是否开户
        * @return uniqueTag
     * @throws AbstractBusiAcctException 
        */
       public String addMemberToGroup(InduGroupMemberBean bean,boolean openAcct) throws AbstractBusiAcctException;
       /**
        * 查询组成员
        * @param queryBean
        * @return
        */
       public List<InduGroupMemberBean> queryGroupMember(InduGroupMemberQuery queryBean);
}
