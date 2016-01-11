/* 
 * BusinessDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;

import java.util.List;







import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.enums.BusinessEntryStatus;
import com.zlebank.zplatform.acc.dao.BusinessDAO;
import com.zlebank.zplatform.acc.pojo.PojoBusiness;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午2:31:32
 * @since 
 */
@Repository
public class BusinessDAOImpl extends HibernateBaseDAOImpl<PojoBusiness> implements BusinessDAO {
    
    
    public List<PojoBusiness> getAllBussiness(){
      Criteria crite=  this.getSession().createCriteria(PojoBusiness.class);
      crite.add(Restrictions.eq("status", BusinessEntryStatus.NORMAL));
      return crite.list();
    }

    /**
     *
     * @param busiType
     * @return
     */
    @Override
    public List<PojoBusiness> getBusinessByType(String busiType) {
        Criteria crite=  this.getSession().createCriteria(PojoBusiness.class);
        crite.add(Restrictions.eq("status", BusinessEntryStatus.NORMAL));
        crite.add(Restrictions.eq("busiType", busiType));
        return crite.list();
    };

    
    
    }
