/* 
 * BusinessServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.bean.enums.BusiType;
import com.zlebank.zplatform.acc.dao.BusinessDAO;
import com.zlebank.zplatform.acc.pojo.PojoBusiness;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午2:48:39
 * @since
 */
@Service
public class BusinessServiceImpl implements BusinessServiec {
    @Autowired
    private BusinessDAO business;
    /**
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<Business> getAllBusiness() {
      List<PojoBusiness> li= business.getAllBussiness();
      List<Business> bus=new ArrayList<Business>();
      for(PojoBusiness business:li){
         bus.add(BeanCopyUtil.copyBean(Business.class, business));
      }  
      return bus;
    }
    /**
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<Business> getBusinessByType(BusiType busiType) {
        List<PojoBusiness> li= business.getBusinessByType(busiType.getCode());
        List<Business> bus=new ArrayList<Business>();
        for(PojoBusiness business:li){
           bus.add(BeanCopyUtil.copyBean(Business.class, business));
        }  
        return bus;
    }


    
}
