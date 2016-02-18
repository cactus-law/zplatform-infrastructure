/* 
 * MemberEntranceLinkDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;


/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:56:17
 * @since 
 */
@Repository
public class MemberEntranceLinkDAOImpl extends HibernateBaseDAOImpl<PojoMemberEntranceLink> implements MemberEntranceLinkDAO {
    /**
     *通过会员号取得信息
     * @param memberId
     * @return
     */
    @Override
    public PojoMemberEntranceLink getByMemberId(String memberId) {
        Criteria crite= this.getSession().createCriteria(PojoMemberEntranceLink.class);
        crite.add(Restrictions.eq("memberId", memberId));
        return (PojoMemberEntranceLink) crite.uniqueResult();
    }

    /**
     * 通过对方的内部会员号取得接入信息
     * @param entranceMemberId
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<PojoMemberEntranceLink> getByEntranceMemberId(String entranceMemberId) {
        Criteria crite= this.getSession().createCriteria(PojoMemberEntranceLink.class);
        crite.add(Restrictions.eq("entranceMemberId", entranceMemberId.trim()));
        return  crite.list();
    }
}
