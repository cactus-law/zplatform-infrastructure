/* 
 * QuickpayCustDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.dao.QuickpayCustDAO;
import com.zlebank.zplatform.member.pojo.PojoQuickpayCust;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午7:16:06
 * @since 
 */
@Repository
public class QuickpayCustDAOImpl extends HibernateBaseDAOImpl<PojoQuickpayCust>implements QuickpayCustDAO {

    /**
     *通过会员ID和卡号得到绑卡对象
     * @param memberId
     * @param cardNo
     * @return
     */
    @Override
    public PojoQuickpayCust getQuickPayCard(String memberId, String cardNo) {
        Criteria crite=   this.getSession().createCriteria(PojoQuickpayCust.class);
        crite .add(Restrictions.eq("relatememberno", memberId));
        crite .add(Restrictions.eq("cardno", cardNo));
        crite .add(Restrictions.eq("status", "00"));
        PojoQuickpayCust card = (PojoQuickpayCust) crite.uniqueResult();
        return card;
    }

}
