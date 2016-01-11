/* 
 * NotifyTaskService.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.service;

import java.net.UnknownHostException;
import java.util.List;

import com.zlebank.zplatform.specification.pojo.PojoNotifyTask;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午9:27:54
 * @since 
 */
public interface NotifyTaskService {
    
    public List<PojoNotifyTask> loadSendNotifyTask() throws UnknownHostException;

}
