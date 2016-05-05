/* 
 * CheckAccount.java  
 * 
 * version v.1.2
 *
 * 2016年1月12日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.ChannelBean;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.InvalidChannelData;
import com.zlebank.zplatform.acc.exception.SaveChannelDataException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcctSubjectMapping;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.ChannelService;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.commons.dao.ChannelDAO;
import com.zlebank.zplatform.commons.dao.pojo.PojoChannel;
import com.zlebank.zplatform.commons.enums.ChannelStatusType;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
/**
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月12日 下午1:15:39
 * @since
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    private Log log = LogFactory.getLog(ChannelServiceImpl.class);

    @Autowired
    private BusiAcctService busiAcctService;
    @Autowired
    private ChannelDAO channelDAO;
    @Autowired
    private SubjectSelector subjectSelector;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public long addChannel(ChannelBean channel) throws InvalidChannelData,
            SaveChannelDataException {
        // 通道数据校验
        String checkResult = check(channel);
        if (StringUtil.isNotEmpty(checkResult)) {
            throw new InvalidChannelData(checkResult);
        }
        try {
            // 通道开户
            openChannelAcct(channel);
            // 通道数据存储
            long channleId = saveChannelData(channel);
            return channleId;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SaveChannelDataException();
        }
    }

    /**
     * 通道开户
     * 
     * @param channel
     * @throws AbstractAccException
     */
    private void openChannelAcct(final ChannelBean channel)
            throws AbstractAccException, AbstractBusiAcctException {
        
        List<PojoBusiAcctSubjectMapping> busiAcctSubjectMappings = subjectSelector
                .getDefaultList(BusinessActorType.CHANNEL);
        BusiAcct busiAcct;
        for (PojoBusiAcctSubjectMapping busiAcctSubjectMappin : busiAcctSubjectMappings) {
            busiAcct = new BusiAcct();
            busiAcct.setBusiAcctName(channel.getChnlname());
            busiAcct.setUsage(busiAcctSubjectMappin.getUsage());
            busiAcctService.openBusiAcct(new BusinessActor() {
                @Override
                public BusinessActorType getBusinessActorType() {
                    return BusinessActorType.CHANNEL;
                }
                
                @Override
                public String getBusinessActorId() {
                    return channel.getChnlcode();
                }
            }, busiAcct, channel.getUserId());
        }
    } 

    /**
     * 通道数据存储
     * 
     * @param channel
     */
    private long saveChannelData(ChannelBean channel) {
        // long seq = accountDAO.getSequence("SEQ_T_CHANNEL_CODE");//
        // 从sequence取通道code
        PojoChannel pojoChannel = new PojoChannel();
        pojoChannel.setChnlcode(channel.getChnlcode());
        pojoChannel.setChnlname(channel.getChnlname());
        pojoChannel.setInsticode(channel.getInsticode());
        pojoChannel.setChnltype(channel.getChnltype());
        pojoChannel.setSubtype(channel.getSubtype());
        pojoChannel.setStatus(ChannelStatusType.NORMAL);
        pojoChannel.setAppset(channel.getAppset());
        pojoChannel.setNotes(channel.getNotes());
        pojoChannel.setRemarks(channel.getRemarks());
        pojoChannel.setAcctCodeCost(channel.getAcctCodeCost());
        pojoChannel.setAcctCodeDeposit(channel.getAcctCodeDeposit());
        pojoChannel.setAcctCodeReceivable(channel.getAcctCodeReceivable());
        channelDAO.saveA(pojoChannel);
        return pojoChannel.getChnlid();
    }

    /**
     * 通道数据校验<br/>
     * test channel is legal,return null if channel is check pass.
     * 
     * @param channel
     * @return null if channel is legal,else return a string
     */
    private String check(ChannelBean channel) {
        String rtn = null;
        if (StringUtil.isEmpty(channel.getChnlcode())) {
            return "通道代码不可为空";
        }
        if (channelDAO.getByChnlCode(channel.getChnlcode()) != null) {
            return "通道代码已经存在";
        }
        if (StringUtil.isEmpty(channel.getChnlname())) {
            return "通道名称不可为空";
        }
        return rtn;
    }

    @Override
    public List<Account> getAccounts(long channelId) {
        // TODO Auto-generated method stub
        return null;
    }
}
