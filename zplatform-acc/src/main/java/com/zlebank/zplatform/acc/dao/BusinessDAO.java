/* 
 * BusinessDAO.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;

import java.util.List;

import com.zlebank.zplatform.acc.pojo.PojoBusiness;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * 交易类型DAO
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午2:28:52
 * @since 
 */
public interface BusinessDAO extends BaseDAO<PojoBusiness> {
    /**
     * 得到全部的交易类型
     * @return
     */
    public List<PojoBusiness> getAllBussiness();
      /**
       * 根据业务类型得到交易类型
       * @param busiType
       * @return
       */
    public List<PojoBusiness> getBusinessByType(String  busiType);
}
