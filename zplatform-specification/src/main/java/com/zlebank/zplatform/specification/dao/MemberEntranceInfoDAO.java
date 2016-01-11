/* 
 * MemberEntranceInfoDAO.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.dao;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceInfo;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:53:47
 * @since 
 */
public interface MemberEntranceInfoDAO  extends BaseDAO<PojoMemberEntranceInfo>{

    /**
     * 通过内部会员号取得接入信息
     * @param memId
     * @return
     */
    public PojoMemberEntranceInfo getByMemberId(String memberId);
    
}
