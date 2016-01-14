package com.zlebank.zplatform.member.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;

@Repository
public class CoopInstiDAOImpl extends HibernateBaseDAOImpl<PojoCoopInsti>
        implements
            CoopInstiDAO {

    
    @Override
    public boolean isNameExist(String instiName) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(PojoCoopInsti.class);
        criteria.add(Restrictions.eq("instiName", instiName.trim()));
        @SuppressWarnings("unchecked")
        List<PojoCoopInsti> result = criteria.list();
        if(result==null||result.size()==0){
            return false;
        }
        return true;
    }

}
