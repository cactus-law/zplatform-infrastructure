/* 
 * UnfrozenJob.java  
 * 
 * version v1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.jobs;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.service.FreezeAmountService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午1:59:39
 * @since
 */
public class UnfrozenJob {
    private static final Log logger = LogFactory.getLog(UnfrozenJob.class);
    @Autowired
    FreezeAmountService service;
    /**
     * 解冻定时任务
     * 
     * @param arg0
     * @throws JobExecutionException
     */
    public void execute() {
        ResourceBundle  RESOURCE = ResourceBundle.getBundle("job-setting");
        //logger.info("unfrozen task start"); 
        List<Long> frozenRecords = null;
        @SuppressWarnings("unused")
        boolean success = true;
        while (true) {
            try {
                frozenRecords = service.unFreezeAmountBatchPre(Integer
                        .parseInt(RESOURCE.getString("frozen_process_num")));
                
                service.unFreezeAmountBatch(frozenRecords);
            } catch (Exception e) {
                e.printStackTrace();
                success = false;
                continue;
            }
            if (frozenRecords == null || frozenRecords.size() == 0) {
                break;
            }
           // logger.info("unfrozen task num:"+frozenRecords.size()); 
        }
        //logger.info("unfrozen task end");
    }

}
