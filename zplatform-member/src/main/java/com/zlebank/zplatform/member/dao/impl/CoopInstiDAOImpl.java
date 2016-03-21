package com.zlebank.zplatform.member.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.dao.CoopInstiDAO;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;
import com.zlebank.zplatform.member.pojo.PojoInstiMK;

@Repository
public class CoopInstiDAOImpl extends HibernateBaseDAOImpl<PojoCoopInsti>
        implements
            CoopInstiDAO {

    @Override
    public boolean isNameExist(String instiName) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(PojoCoopInsti.class);
        criteria.add(Restrictions.eq("instiName", instiName));
        @SuppressWarnings("unchecked")
        List<PojoCoopInsti> result = criteria.list();
        if (result == null || result.size() == 0) {
            return false;
        }
        return true;
    }
    
    @Override
    public PojoInstiMK getMKByInstiCode(String instiCode,
            TerminalAccessType terminalAccessType) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(PojoInstiMK.class);
        criteria.add(Restrictions.eq("terminalAccessType",terminalAccessType));
        criteria.createCriteria("coopInsti").add(
                Restrictions.eq("instiCode", instiCode.trim()));
        @SuppressWarnings("unchecked")
        List<PojoInstiMK> result = criteria.list();
        if (result == null || result.size() == 0) {
            return null;
        }
        return result.get(0);
    }
    
    @Override
    public PojoCoopInsti get(long id){
        Session session = getSession();
        return (PojoCoopInsti)session.get(PojoCoopInsti.class, id);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<PojoCoopInsti> getCoopInstiList(){
    	 Session session = getSession();
         Criteria criteria = session.createCriteria(PojoCoopInsti.class);
         return (List<PojoCoopInsti>)criteria.list();
    }
    @Override
    public PojoCoopInsti getByInstiCode(String instiCode){
    	Session session = getSession();
        Criteria criteria = session.createCriteria(PojoCoopInsti.class);
        criteria.add(Restrictions.eq("instiCode", instiCode));
        return (PojoCoopInsti)criteria.uniqueResult();
    }
    @Override
    public PojoCoopInsti getEager(long id){
    	 Session session = getSession();
    	 Criteria criteria =  session.createCriteria(PojoCoopInsti.class).setFetchMode("products", FetchMode.JOIN);
    	 criteria.add(Restrictions.eq("id", id));
         return (PojoCoopInsti)criteria.uniqueResult();
    }
}
