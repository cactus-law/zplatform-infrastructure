/* 
 * OpenMinorMerchantNotify.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.notify;

import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.member.dao.MerchDAO;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.specification.Notify;
import com.zlebank.zplatform.specification.RequestType;
import com.zlebank.zplatform.specification.bean.enums.SendStatus;
import com.zlebank.zplatform.specification.dao.NotifyTaskDAO;
import com.zlebank.zplatform.specification.message.Head;
import com.zlebank.zplatform.specification.message.Request;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.parser.SpecificationParser;
import com.zlebank.zplatform.specification.pojo.PojoNotifyTask;
import com.zlebank.zplatform.specification.utils.NetWorkUtils;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午4:47:54
 * @since 
 */
@Service
public class OpenMinorMerchantNotify implements Notify{
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OpenMinorMerchantNotify.class);
    @Autowired
    private NotifyTaskDAO notifyTaskDAOImpl;
    
    @Autowired
    MerchDAO merchDAOImpl; 
    
    private final int maxSendTiems = 3;
    
    @Autowired
    private SpecificationParser specificationParser;
    /**
     *
     * @param response
     * @throws UnknownHostException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void addNotify(RequestType notifyRequestType,String version ,Response response) throws UnknownHostException {
         PojoNotifyTask notifyTask = new PojoNotifyTask();
         String memberId = response.getString("merchNo");
         String minorMerchMemberId = response.getString("minorMerchNo");
         notifyTask.setMemberId(memberId);
         notifyTask.setSrcSessionId(response.getString("sessionId"));
         notifyTask.setLoadIP(NetWorkUtils.getMac());
         notifyTask.setMaxSendTimes(maxSendTiems);
         notifyTask.setSendStatus(SendStatus.INIT);
         
         PojoMerchDeta merchDeta = merchDAOImpl.getMerchBymemberId(minorMerchMemberId);
         
         Request request = new Request();
         Head head = new Head();
         head.setRequestType(notifyRequestType);
         head.setVersion(version);
         
         
         request.setObject("merchNo", response.getString("merchNo"));
         request.setObject("srcSessionId", response.getString("sessionId"));
         request.setObject("minorMerchNo", minorMerchMemberId);
         request.setObject("status", merchDeta.getStatus().getCode());
         request.setObject("mac", "");
         request.setHead(head); 
         String data = specificationParser.parse(request).toString();
         
         notifyTask.setData(data);
         notifyTaskDAOImpl.merge(notifyTask);
    }
    
   
}
