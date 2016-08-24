/* 
 * FinanceProductAccountServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年8月24日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.QueryBusiCodeInfo;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.FinanceProductAccountBean;
import com.zlebank.zplatform.member.bean.FinanceProductQueryBean;
import com.zlebank.zplatform.member.bean.MemberAccountBean;
import com.zlebank.zplatform.member.bean.MemberBalanceDetailBean;
import com.zlebank.zplatform.member.bean.MemberBean;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.GetAccountFailedException;
import com.zlebank.zplatform.member.service.FinanceProductAccountService;
import com.zlebank.zplatform.member.service.MemberService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年8月24日 下午4:03:10
 * @since 
 */
@Service
public class FinanceProductAccountServiceImpl implements FinanceProductAccountService {
    private Log log = LogFactory.getLog(MemberOperationServiceImpl.class);

    @Autowired
    private BusiAcctService busiAcctService;
    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberService memberService;
    /**
     *
     * @param memberType
     * @param member
     * @param usage
     * @return
     * @throws DataCheckFailedException
     * @throws GetAccountFailedException
     */
    @Override
    public FinanceProductAccountBean queryBalance(FinanceProductQueryBean bean,
            Usage usage) throws DataCheckFailedException,
            GetAccountFailedException {
        if(log.isDebugEnabled()){
            log.debug("参数1："+bean);
        }
        if (StringUtil.isEmpty(bean.getProductCode())) {
            throw new DataCheckFailedException("产品号不可为空");
        }
        try {
            // 取相关的业务信息
            QueryBusiCodeInfo busiInfo = busiAcctService.getBusiCodeByMemberId(usage,bean.getProductCode());
            // 取会计账户信息
            Account accountBalanceById = accountService.getAccountBalanceById(busiInfo.getAcctId());
            // 准备返回结果
            FinanceProductAccountBean mbb = new FinanceProductAccountBean();
            mbb.setBalance(accountBalanceById.getBalance().getAmount());
            mbb.setFrozenBalance(accountBalanceById.getFronzenBalance().getAmount());
            mbb.setTotalBalance(accountBalanceById.getTotalBalance().getAmount());
            mbb.setStatus(accountBalanceById.getStatus());
            mbb.setBusiCode(busiInfo.getBusiCode());
            mbb.setUsage(usage);
            return mbb;
        } catch (AbstractBusiAcctException e) {
            log.error(e.getMessage(), e);
            throw new GetAccountFailedException(e.getMessage());
        }
    }
}
