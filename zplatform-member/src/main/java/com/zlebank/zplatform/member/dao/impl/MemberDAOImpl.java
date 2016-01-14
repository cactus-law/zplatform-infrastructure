/* 
 * MemeberDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.pojo.PojoMember;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月13日 下午6:30:07
 * @since 
 */
@Repository
public class MemberDAOImpl extends HibernateBaseDAOImpl<PojoMember>
        implements
           MemberDAO {

    /**
     *
     * @param phone
     * @return
     */
    @Override
    public PojoMember getMemberByphone(String phone) {
      return (PojoMember) this.getSession().createCriteria(PojoMember.class)
        .add(Restrictions.eq("phone", phone)).uniqueResult();
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public PojoMember getMemberByEmail(String email) {
        return (PojoMember) this.getSession().createCriteria(PojoMember.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override 
    public PojoMember getMbmberByMemberId(String memberId,BusinessActorType type) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
            crite .add(Restrictions.eq("memberid", memberId));
        if (type != null) {
           crite .add(Restrictions.eq("membertype", type)); 
       }
        PojoMember member = (PojoMember) crite.uniqueResult();
        if (member != null)
            getSession().evict(member);
        return member;
    }

    /**
     * 通过登陆名得到会员信息
     * @param loginName
     * @return
     */
    @Override
    public PojoMember getMemberByLoginName(String loginName) {
        return (PojoMember) this.getSession().createCriteria(PojoMember.class)
                .add(Restrictions.eq("loginame", loginName)).uniqueResult();
    }
}
