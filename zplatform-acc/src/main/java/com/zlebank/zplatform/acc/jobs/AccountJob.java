/* 
 * AccountJob.java  
 * 
 * version TODO
 *
 * 2015年9月10日 
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

import com.zlebank.zplatform.acc.service.AccEntryService;

/**
 * 异步记账
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月10日 下午3:09:15
 * @since
 */
public class AccountJob {
    private static final Log logger = LogFactory.getLog(AccountJob.class);
    @Autowired
    AccEntryService service;
    /**
     * 异步记账定时任务
     * 
     * @param arg0
     * @throws JobExecutionException
     */
    public void execute() {
        ResourceBundle RESOURCE = ResourceBundle.getBundle("job-setting");
        logger.info("asy account entry task start");
        List<Long> asyAccountEntryRecords = null;
        @SuppressWarnings("unused")
        boolean success = true;
        while (true) {
            try {
                asyAccountEntryRecords = service.accountBatchPre(Integer
                        .parseInt(RESOURCE.getString("account_process_num")));
                service.accountBatch(asyAccountEntryRecords);
            } catch (Exception e) {
                logger.error(e.getMessage(),e); 
                e.printStackTrace();
                success = false;
                continue;
            }
            if (asyAccountEntryRecords == null
                    || asyAccountEntryRecords.size() == 0) {
                break;
            }
            logger.info("asy account entry task num"
                    + asyAccountEntryRecords.size());
        }

        logger.info("asy account entry task end");
    }
}
