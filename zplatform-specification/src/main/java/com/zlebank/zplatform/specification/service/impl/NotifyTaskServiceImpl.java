/* 
 * NotifyServiceImpl.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.specification.Notify;
import com.zlebank.zplatform.specification.NotifyFactory;
import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.dao.NotifyTaskDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Request;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.notify.OpenMinorMerchantNotify;
import com.zlebank.zplatform.specification.pojo.PojoNotifyTask;
import com.zlebank.zplatform.specification.service.NotifyTaskService;
import com.zlebank.zplatform.specification.utils.NetWorkUtils;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午5:12:10
 * @since
 */
@Aspect
@Service
public class NotifyTaskServiceImpl extends SpringApplicationObjectSupport implements NotifyTaskService{
    @SuppressWarnings("unused")
    private static final Log log = LogFactory
            .getLog(OpenMinorMerchantNotify.class);

    @Autowired
    private NotifyFactory notifyFactory;
    
    @Autowired
    private NotifyTaskDAO notifyTaskDAOImpl;

    @Pointcut("execution(* com.zlebank.zplatform.specification.processor.RawAbstractProcessor.process(..))")
    public void addNotifyAfterOpen() {

    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public List<PojoNotifyTask> loadSendNotifyTask() throws UnknownHostException{
        List<PojoNotifyTask> list = notifyTaskDAOImpl.loadSendNotifyTask(NetWorkUtils.getMac());
        return list;
    }

    @Around("addNotifyAfterOpen()&&args(request)")
    public Response addNotifyTask(ProceedingJoinPoint joinpoint, Message request) {

        Response response = null;
        fetchApplicationContext();
        try {
            response = (Response) joinpoint.proceed();

            Request _request = (Request) request;
            RequestType notifyRequestType;
            switch (_request.getRequestType()) {
                case SYCINDIVIDUAL :
                    notifyRequestType = RequestType.SYCINDIVIDUALNOTIFY;
                    break;
                case SYCMINORMERCH :
                    notifyRequestType = RequestType.SYCMERCHNOTIFY;
                    break;
                default :
                    return response;
            }

            if (response.getResCode() == null
                    || !response.getResCode().equals("00")) {
                return response;
            }
            Notify notify = notifyFactory.getNotify(applicationContext,
                    notifyRequestType);

            notify.addNotify(notifyRequestType, _request.getVersion(), response);

        } catch (Throwable e) {
            // TODO
            e.printStackTrace();
        }
        return response;
    }
}
