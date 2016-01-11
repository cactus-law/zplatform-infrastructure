/* 
 * NotifyTaskDAO.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.dao;

import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.specification.pojo.PojoNotifyTask;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午7:50:33
 * @since 
 */
public interface NotifyTaskDAO extends BaseDAO<PojoNotifyTask>{
    /**
     * 
     * @param localIP
     * @return
     */
    List<PojoNotifyTask> loadSendNotifyTask(String localIP);
}
