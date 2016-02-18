/* 
 * ApiLogServiceImpl.java  
 * 
 * version 1.0
 *
 * 2015年9月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.service.impl;

import java.util.Date;

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

import com.zlebank.zplatform.specification.dao.EntranceAPILogDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Request;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.pojo.PojoEntranceAPILog;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月18日 上午9:07:20
 * @since
 */
@Aspect
@Service
public class ApiLogServiceImpl  {

    private static final Log log = LogFactory.getLog(ApiLogServiceImpl.class);

    @Autowired
    EntranceAPILogDAO entranceAPILogDAOImpl;

    @Pointcut("execution(* com.zlebank.zplatform.specification.processor.RawAbstractProcessor.process(..))")
    public void processMessage() {

    }

    @Around("processMessage()&&args(request)") 
    @Transactional(propagation=Propagation.REQUIRED)
    public Response handleLog(ProceedingJoinPoint joinpoint, Message request) { 
        
        PojoEntranceAPILog pojoEntranceApiLog = new PojoEntranceAPILog();
        Request _request = (Request)request;
        Date date = new Date();
        pojoEntranceApiLog.setMac(_request.getString("mac"));
        pojoEntranceApiLog.setMemberId(_request.getString("merchNo"));
        pojoEntranceApiLog.setRequestTime(date);
        pojoEntranceApiLog.setRequestType(_request.getRequestType());
        pojoEntranceApiLog.setVersion(_request.getVersion()); 
        pojoEntranceApiLog.setSeqNo(String.format("%1$ty%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", date));
       
        pojoEntranceApiLog = entranceAPILogDAOImpl.merge(pojoEntranceApiLog); 
       
        
        Response response = null;
        try {
            response = (Response) joinpoint.proceed();
            pojoEntranceApiLog.setResCode(response.getResCode());
            pojoEntranceApiLog.setResDes(response.getResDes());
            pojoEntranceApiLog.setResponseTime(new Date()); 
            pojoEntranceApiLog.setSessionId(_request.getString("sessionId"));
            
            pojoEntranceApiLog = entranceAPILogDAOImpl.merge(pojoEntranceApiLog); 
             
        } catch (Throwable e) { 
            log.warn("api log error");
            e.printStackTrace();
        }
        return response;
    }
}
