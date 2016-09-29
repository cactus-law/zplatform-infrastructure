/* 
 * IndustryGroupMemberDAO.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao;

import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;
import com.zlebank.zplatform.member.pojo.PojoIndustryGroupMember;

/**
 * 行业群组<>会员关系
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:29:35
 * @since 
 */
public interface IndustryGroupMemberDAO extends BaseDAO<PojoIndustryGroupMember> {


	/**
	 * 根据会员号和群组代码获取行业群组会员信息
	 * @param memberId 会员号
	 * @param groupCode 群组号
	 * @return
	 */
	 public PojoIndustryGroupMember getGroupMemberByMemberIdAndGroupCode(String memberId,String groupCode);

    /**
     * 查询组成员
     * @param queryBean
     * @return
     */
    List<PojoIndustryGroupMember> queryGroupMember(InduGroupMemberQuery queryBean);

    /**
     * @param offset
     * @param pageSize
     * @param queryBean
     * @return
     */
    List<PojoIndustryGroupMember> getItem(int offset,
            int pageSize,
            InduGroupMemberQuery queryBean);

    /**
     * @param queryBean
     * @return
     */
    long count(InduGroupMemberQuery queryBean);
}
