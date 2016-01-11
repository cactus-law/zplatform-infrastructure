/* 
 * MemberEntranceLinkDAO.java  
 * 
 * version v1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.dao;

import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:55:35
 * @since 
 */
public interface MemberEntranceLinkDAO extends BaseDAO<PojoMemberEntranceLink>{
    /**
     * 通过会员号取得接入信息
     * @param memId
     * @return
     */
    public PojoMemberEntranceLink getByMemberId(String memberId);
    /**
     * 通过对方的内部会员号取得接入信息
     * @param memId
     * @return
     */
    public List<PojoMemberEntranceLink> getByEntranceMemberId(String entranceMemberId);
}
