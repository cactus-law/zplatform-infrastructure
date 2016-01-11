/* 
 * NotifyTaskDAOImpl.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
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
import com.zlebank.zplatform.specification.bean.enums.SendStatus;
import com.zlebank.zplatform.specification.dao.NotifyTaskDAO;
import com.zlebank.zplatform.specification.pojo.PojoNotifyTask;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午7:51:23
 * @since 
 */
@Repository
public class NotifyTaskDAOImpl extends HibernateBaseDAOImpl<PojoNotifyTask> implements NotifyTaskDAO{
    
    @SuppressWarnings("unchecked")
    public List<PojoNotifyTask> loadSendNotifyTask(String localIP){
        Criteria criteria = getSession().createCriteria(PojoNotifyTask.class);
        criteria.add(Restrictions.eq("loadIP", localIP));
        criteria.add(Restrictions.in("sendStatus", new SendStatus[]{SendStatus.INIT,SendStatus.OVERTIME}));
        criteria.add(Restrictions.sqlRestriction("send_times <max_send_times"));
        return criteria.list();
    }
}
