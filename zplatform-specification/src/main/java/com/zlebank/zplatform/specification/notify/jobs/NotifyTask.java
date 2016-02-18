/* 
 * NotifyTask.java  
 * 
 * version 1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.notify.jobs;

import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.member.bean.MerchMK;
import com.zlebank.zplatform.member.service.MerchMKService;
import com.zlebank.zplatform.specification.bean.enums.SendStatus;
import com.zlebank.zplatform.specification.dao.MemberEntranceInfoDAO;
import com.zlebank.zplatform.specification.exception.HttpRequestErrResCodeException;
import com.zlebank.zplatform.specification.http.HttpProxy;
import com.zlebank.zplatform.specification.http.bean.HttpContentType;
import com.zlebank.zplatform.specification.http.bean.HttpMethodType;
import com.zlebank.zplatform.specification.http.impl.HttpProxyImpl;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceInfo;
import com.zlebank.zplatform.specification.pojo.PojoNotifyTask;
import com.zlebank.zplatform.specification.service.NotifyTaskService;
import com.zlebank.zplatform.specification.utils.RSAUtils;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月19日 下午4:44:37
 * @since
 */
public class NotifyTask {
    private static final Log log = LogFactory.getLog(NotifyTask.class);

    @Autowired
    private NotifyTaskService notifyTaskServiceImpl;

    @Autowired
    private MerchMKService merchMKServiceImpl;

    @Autowired
    private MemberEntranceInfoDAO memberEntranceInfoDAOImpl;

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute() {
        List<PojoNotifyTask> taskList = new ArrayList<PojoNotifyTask>();
        try {

            taskList = notifyTaskServiceImpl.loadSendNotifyTask();
           // log.info("task num:" + taskList.size());
            for (PojoNotifyTask notifyTask : taskList) {
                String memberId = notifyTask.getMemberId();
                MerchMK merchMK = merchMKServiceImpl.get(memberId);
                PojoMemberEntranceInfo memberEntranceInfo = memberEntranceInfoDAOImpl
                        .getByMemberId(memberId);
                String requestStr = notifyTask.getData();
                JSONObject requestJson = JSONObject.fromObject(requestStr);
                log.info("request origin mac str:" + requestJson.toString());
                String mac = RSAUtils.sign(requestJson.toString().getBytes(),
                        merchMK.getLocalPriKey());

                requestJson.getJSONObject("content").put("mac", mac);

                requestStr = requestJson.toString();
                notifyTask.setSendStatus(SendStatus.SENDING);
                notifyTask.setSendTimes(notifyTask.getSendTimes()+1);
                String resMsg = null;
                try {
                    HttpProxy httpProxy = new HttpProxyImpl();
                    httpProxy
                            .setUrl(new URL(memberEntranceInfo.getNotifyUrl()));
                    httpProxy.setContentType(HttpContentType.FORM);
                    httpProxy.setMethod(HttpMethodType.POST);

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("data", requestStr);
                    resMsg = httpProxy.sendHttp(params, true);
                } catch (HttpRequestErrResCodeException e) {
                    notifyTask.setHttpResCode(String.valueOf(e.getHttpResCode()));
                    notifyTask.setSendStatus(SendStatus.FAIL);
                    return;
                } catch(ConnectException e){
                    e.printStackTrace(); 
                    notifyTask.setSendStatus(SendStatus.OVERTIME);
                    return;
                }catch (Exception e) {
                    notifyTask.setSendStatus(SendStatus.FAIL);
                    e.printStackTrace();
                    return;
                } 
                JSONObject jsonObject = JSONObject.fromObject(resMsg)
                        .getJSONObject("content");
                String resCode = jsonObject.getString("rescode");
                if (resCode != null && resCode.equals("00")) { 
                    notifyTask.setSendStatus(SendStatus.CONFIRMED);
                } else { 
                    notifyTask.setSendStatus(SendStatus.UNCONFIRMED);
                }
            }
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
