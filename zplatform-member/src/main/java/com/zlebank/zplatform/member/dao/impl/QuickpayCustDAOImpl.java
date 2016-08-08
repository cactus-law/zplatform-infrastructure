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

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.QuickpayCustBean;
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
public class QuickpayCustDAOImpl extends AbstractPagedQueryDAOImpl<PojoQuickpayCust,QuickpayCustBean>implements QuickpayCustDAO {

    @Override
    public PojoQuickpayCust getQuickPayCard(String memberId, String cardNo,String idnum,String accname,String phone) {
        Criteria crite=   this.getSession().createCriteria(PojoQuickpayCust.class);
        crite .add(Restrictions.eq("relatememberno", memberId));
        crite .add(Restrictions.eq("cardno", cardNo));
        crite.add(Restrictions.eq("idnum", idnum));
        crite.add(Restrictions.eq("accname", accname));
        crite.add(Restrictions.eq("phone", phone));
        crite .add(Restrictions.eq("status", "00"));
        PojoQuickpayCust card = (PojoQuickpayCust) crite.uniqueResult();
        return card;
    }

    @Override
    protected Criteria buildCriteria(QuickpayCustBean e) {
        Criteria crite=   this.getSession().createCriteria(PojoQuickpayCust.class);
        crite .add(Restrictions.eq("relatememberno", e.getRelatememberno()));
        if (StringUtil.isNotEmpty(e.getCardtype()) && !"0".equals(e.getCardtype()) ) {
            crite .add(Restrictions.eq("cardtype", e.getCardtype()));
        }
        if (StringUtil.isNotEmpty(e.getDevId()) ) {
            crite .add(Restrictions.eq("devId", e.getDevId()));
        }
        crite .add(Restrictions.eq("status", "00"));
        return crite;
    }
 
    @Override
    public PojoQuickpayCust getById(long id) {
        return (PojoQuickpayCust) getSession().get(PojoQuickpayCust.class, id);
    }

    /**
     *
     * @param bean
     * @return
     */
    @Override
    public PojoQuickpayCust getQuickPayCard(QuickpayCustBean bean) {
        Criteria crite=   this.getSession().createCriteria(PojoQuickpayCust.class);
        if(bean!=null){
            if(StringUtil.isNotEmpty(bean.getRelatememberno())){
                crite .add(Restrictions.eq("relatememberno",bean.getRelatememberno() ));
            }
            if(StringUtil.isNotEmpty(bean.getCardno())){
                crite .add(Restrictions.eq("cardno", bean.getCardno()));
            }
            if(StringUtil.isNotEmpty(bean.getIdnum())){
                crite.add(Restrictions.eq("idnum", bean.getIdnum())); 
            }
            
            if(StringUtil.isNotEmpty(bean.getAccname())){
                crite.add(Restrictions.eq("accname", bean.getAccname()));
            }
           
            if(StringUtil.isNotEmpty(bean.getPhone())){
                crite.add(Restrictions.eq("phone", bean.getPhone()));
            }
            
            if(StringUtil.isNotEmpty(bean.getDevId())){
                crite.add(Restrictions.eq("devId", bean.getDevId()));
            }
        }
        crite .add(Restrictions.eq("status", "00"));
        PojoQuickpayCust card = (PojoQuickpayCust) crite.uniqueResult();
        return card;
    }

}
