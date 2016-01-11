/* 
 * MemberBaseDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.dao.MemberBaseDAO;
import com.zlebank.zplatform.member.pojo.PojoMemberBase;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午8:42:54
 * @since 
 */
@Repository
public class MemberBaseDAOImpl extends HibernateBaseDAOImpl<PojoMemberBase>
        implements
            MemberBaseDAO {

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    public PojoMemberBase getMember(String memberId) {
        
        return (PojoMemberBase)this.getSession().get(PojoMemberBase.class, memberId);
    }

    /**
     *
     * @return
     */
    @Override
    public Session getCurrentSession() {
        // TODO Auto-generated method stub
        return this.getSession();
    }


}
