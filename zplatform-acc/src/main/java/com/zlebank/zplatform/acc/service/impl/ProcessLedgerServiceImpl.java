/* 
 * ProcessLedgerService.java  
 * 
 * version v1.0
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.dao.AbstractSubjectDAO;
import com.zlebank.zplatform.acc.dao.AccEntryDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAbstractSubject;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.ProcessLedgerService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月27日 下午5:56:04
 * @since 
 */
@Service
public class ProcessLedgerServiceImpl  implements ProcessLedgerService {
    private static final Log log = LogFactory.getLog(ProcessLedgerServiceImpl.class);
    @Autowired
    private AbstractSubjectDAO abstractSubjectDAO;
    @Autowired
    AccountService accountService;
    @Autowired
    AccEntryDAO accEntryDAO;

    /**
     * 总账处理
     * @param account
     * @throws AccBussinessException 
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void processLedger(PojoAccount account) throws AccBussinessException  {
        // 业务检查，如果不是叶子科目，返回异常。
        if (AcctType.SUBJECT==account.getAcctType()) throw new AccBussinessException("E000009", new Object[]{account.getAcctCode()});
        // 取上一级科目的记录
        PojoAbstractSubject pojo=account.getParentSubject();
        boolean firstFlg = true;
        do {
            if (pojo.getParentSubject() != null
                    && pojo.getId() == pojo.getParentSubject().getId()) 
                break;// 防止死循环
            if (firstFlg)
                firstFlg=false;//第一次跳过
            else 
                pojo = pojo.getParentSubject();// 取得父级记录
            //验证DAC
            accountService.checkDAC(pojo);

            // 更新账户
            PojoAccount updateAccount = new PojoAccount();
            updateAccount.setAcctCode(pojo.getAcctCode());
            updateAccount.setBalance(account.getBalance());
            updateAccount.setTotalBanance(account.getTotalBanance());
            updateAccount.setFrozenBalance(Money.ZERO);
            updateAccount.setCreditBalance(Money.ZERO);
            updateAccount.setDebitBalance(Money.ZERO);

            int updateCount = abstractSubjectDAO.updateBySql(updateAccount);
            if (updateCount == 0) {
                if (log.isDebugEnabled()) {
                    log.debug("通过SQL方式更新中间科目时发生错误：");
                    log.debug("更新信息：" + JSONObject.fromObject(updateAccount) );
                }
                throw new AccBussinessException("E000018");
            }
        } while (pojo.getParentSubject() != null);// 一直更新到一级科目

    }
    public Money nullConvert(Money balance) {
        return balance == null ? Money.valueOf(BigDecimal.ZERO) : balance;
    }
}
