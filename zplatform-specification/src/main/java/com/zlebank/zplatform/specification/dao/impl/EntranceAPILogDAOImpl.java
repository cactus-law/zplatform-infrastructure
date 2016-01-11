/* 
 * EntranceAPILogDAOImpl.java  
 * 
 * version v1.0
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.specification.dao.EntranceAPILogDAO;
import com.zlebank.zplatform.specification.pojo.PojoEntranceAPILog;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:52:07
 * @since 
 */
@Repository
public class EntranceAPILogDAOImpl extends HibernateBaseDAOImpl<PojoEntranceAPILog> implements EntranceAPILogDAO {

    /**
     * 通过会员号和事务ID取得相应的记录
     * @param memberId
     * @param sessionId
     * @return
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public PojoEntranceAPILog getByMemberIdSessionId(String memberId,  String sessionId) {
        Criteria crite= this.getSession().createCriteria(PojoEntranceAPILog.class);
        crite.add(Restrictions.eq("memberId", memberId));
        crite.add(Restrictions.eq("sessionId", sessionId));
        @SuppressWarnings("rawtypes")
        List  list = crite.list();
        return list.size()==0?null:(PojoEntranceAPILog)list.get(0); 
    }

}
