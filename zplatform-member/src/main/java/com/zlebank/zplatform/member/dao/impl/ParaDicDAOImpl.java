package com.zlebank.zplatform.member.dao.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoParaDic;

/* 
 * PrimayKeyDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:14:10
 * @since 
 */
@Repository
public class ParaDicDAOImpl extends HibernateBaseDAOImpl<PojoParaDic>
        implements
            ParaDicDAO {

    /**
     * 得到memberId头
     * @return
     * @throws MemberBussinessException
     */
    @Override
    public PojoParaDic getPrimay(String paraType)
            throws MemberBussinessException {
        Criteria crite = getSession().createCriteria(PojoParaDic.class);
        try {
            PojoParaDic primay = (PojoParaDic) crite.add(
                    Restrictions.eq("paraType", paraType)).uniqueResult();
            return primay;
        } catch (Exception e) {
            throw new MemberBussinessException("M100003");
        }
    }
    /**
     * 得到序列
     *
     * @return
     */
     @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getSeq(String sequences){
         return  this.getSession().createSQLQuery(" SELECT "+sequences+".NEXTVAL FROM DUAL")
           .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
       }
}
