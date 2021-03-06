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
import com.zlebank.zplatform.member.bean.InduGroupMemberCreateBean;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;
import com.zlebank.zplatform.member.exception.ExistedDataException;
import com.zlebank.zplatform.member.exception.NotFoundDataException;

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
        * 根据会员号和群组代码获取行业群组会员信息（只获取群组内行业专户，无法取得授信账户）
        * @param memberId 会员号
        * @param groupCode 群组号
        * @return
        */
       public InduGroupMemberBean getGroupMemberByMemberIdAndGroupCode(String memberId,String groupCode);
      
       /**
        * 将会员账户加入行业群组
        * @param bean
        * @param openAcct 是否开户
        * @param busiActorType 会员类型
        * @return uniqueTag
        * @return
        * @throws AbstractBusiAcctException
        * @throws ExistedDataException
        * @throws NotFoundDataException
        */
       public String addMemberToGroup(InduGroupMemberCreateBean bean,boolean openAcct,String busiActorType) throws AbstractBusiAcctException, ExistedDataException, NotFoundDataException;
       /**
        * 查询组成员
        * @param queryBean
        * @return
        */
       public List<InduGroupMemberBean> queryGroupMember(InduGroupMemberQuery queryBean);
       /**
        * 查询会员某一账户是否已加入群组
        * @param groupCode 
        * @param memberId
        * @param usage 用途
        * @return
        */
       public InduGroupMemberBean queryGroupMemberExist(String groupCode,String memberId,String usage);
}
