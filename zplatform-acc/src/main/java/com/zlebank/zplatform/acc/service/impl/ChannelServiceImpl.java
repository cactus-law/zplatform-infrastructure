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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.ChannelBean;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectDAO;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.InvalidChannelData;
import com.zlebank.zplatform.acc.exception.SaveChannelDataException;
import com.zlebank.zplatform.acc.pojo.PojoSubject;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.ChannelService;
import com.zlebank.zplatform.commons.dao.ChannelDAO;
import com.zlebank.zplatform.commons.dao.pojo.PojoChannel;
import com.zlebank.zplatform.commons.enums.ChannelStatusType;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * 
 * 通道服务类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月12日 下午1:15:39
 * @since
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    private final static String DEPOSIT = "【备付金】";
    private final static String COST = "【成本】";
    private final static String RECEIVABLE = "【应收款】";
    
    private Log log = LogFactory.getLog(ChannelServiceImpl.class);
    
    @Autowired
    private AccountService account;;
    @Autowired
    private ChannelDAO channelDAO;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private AccountDAO accountDAO;

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void addChannel(ChannelBean channel) throws InvalidChannelData, SaveChannelDataException {
        // 通道数据校验
        String checkResult = check(channel);
        if (StringUtil.isNotEmpty(checkResult)) {
            throw new InvalidChannelData(checkResult);
        }
        try {
            // 通道开户
            openChannelAcct(channel);
            // 通道数据存储
            saveChannelData(channel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SaveChannelDataException();
        }
    }

    /**
     * 通道开户
     * @param channel
     * @throws AbstractAccException 
     */
    private void openChannelAcct(ChannelBean channel) throws AbstractAccException {
        long seq = accountDAO.getSequence("SEQ_T_ACC_ACCT_CHNL");
        String accCodePostfix = String.format("%04d", seq);
        // 备付金账户【1002】
        channel.setAcctCodeDeposit(openLeaveAccount(channel, "1002", accCodePostfix, DEPOSIT));
        // 应收款账户【1221】
        channel.setAcctCodeReceivable(openLeaveAccount(channel, "1221", accCodePostfix, RECEIVABLE));
        // 成本账户【6401】
        channel.setAcctCodeCost(openLeaveAccount(channel, "6401", accCodePostfix, COST));
    }

    private String openLeaveAccount(ChannelBean channel, String parentCode, String accCodePostfix, String chnlName) throws AbstractAccException {
        PojoSubject subject = subjectDAO.subjectByCode(parentCode);// 根据国标取相应的ID
        long parentId = subject.getId();
        String channelAcctCode = subject.getAcctCode()+accCodePostfix;
        String channelAcctName = channel.getChnlname() + chnlName;
        long parentSubjectId=parentId;
        String acctCode=channelAcctCode;
        String acctName=channelAcctName;
        long userId=channel.getUserId();
        account.addAcct(parentSubjectId, acctCode, acctName, userId);
        return acctCode;
    }

    /**
     * 通道数据存储
     * @param channel
     */
    private void saveChannelData(ChannelBean channel) {
//        long seq = accountDAO.getSequence("SEQ_T_CHANNEL_CODE");// 从sequence取通道code
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
        channelDAO.saveA(pojoChannel );
    }

    /**
     * 通道数据校验
     * @param channel
     * @return
     */
    private String check(ChannelBean channel) {
        String rtn = null;
        if (StringUtil.isEmpty(channel.getChnlcode())) {
            return  "通道代码不可为空";
        }
        if (channelDAO.getByChnlCode(channel.getChnlcode()) != null) {
            return  "通道代码已经存在";
        }
        if (StringUtil.isEmpty(channel.getChnlname())) {
            return  "通道名称不可为空";
        }
        return rtn;
    }

}
