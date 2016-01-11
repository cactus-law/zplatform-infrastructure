/* 
 * MemberEntranceInfoDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.specification.dao.MemberEntranceInfoDAO;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceInfo;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:54:29
 * @since 
 */
@Repository
public class MemberEntranceInfoDAOImpl extends HibernateBaseDAOImpl<PojoMemberEntranceInfo> implements MemberEntranceInfoDAO {

    /**
     *通过内部会员号取得接入信息
     * @param memId
     * @return
     */
    @Override
    public PojoMemberEntranceInfo getByMemberId(String memberId) {
        Criteria crite= this.getSession().createCriteria(PojoMemberEntranceInfo.class);
        crite.add(Restrictions.eq("memberId", memberId));
        return (PojoMemberEntranceInfo) crite.uniqueResult();
    }
}
