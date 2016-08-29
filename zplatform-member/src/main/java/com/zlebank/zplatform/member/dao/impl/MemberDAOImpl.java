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
import com.zlebank.zplatform.member.bean.enums.MemberType;
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
    public PojoMember getMemberByMemberId(String memberId,MemberType type) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
            crite .add(Restrictions.eq("memberId", memberId));
        if (type != null) {
           crite .add(Restrictions.eq("memberType", type)); 
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

    /**
     *
     * @param loginName
     * @param instiCode
     * @return
     */
    @Override
    public PojoMember getMemberByLoginNameAndCoopInsti(String loginName, long instiId) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
        crite .add(Restrictions.eq("loginName", loginName));
        crite .add(Restrictions.eq("instiId", instiId));
        PojoMember member = (PojoMember) crite.uniqueResult();
        return member;
    }

    /**
     *
     * @param phone
     * @param instiCode
     * @return
     */
    @Override
    public PojoMember getMemberByPhoneAndCoopInsti(String phone,
            long instiCode) {
        Criteria crite=   this.getSession().createCriteria(PojoMember.class);
        crite .add(Restrictions.eq("phone", phone));
        crite .add(Restrictions.eq("instiId", instiCode));
        PojoMember member = (PojoMember) crite.uniqueResult();
        return member;
    }
}
