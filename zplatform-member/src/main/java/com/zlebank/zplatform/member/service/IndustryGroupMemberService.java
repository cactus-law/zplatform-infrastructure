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
        * @return uniqueTag
        */
       public String addMemberToGroup(InduGroupMemberBean bean);
       
       /**
        * 根据会员号和群组代码获取行业群组会员信息
        * @param memberId 会员号
        * @param groupCode 群组号
        * @return
        */
       public InduGroupMemberBean getGroupMemberByMemberIdAndGroupCode(String memberId,String groupCode);
}
