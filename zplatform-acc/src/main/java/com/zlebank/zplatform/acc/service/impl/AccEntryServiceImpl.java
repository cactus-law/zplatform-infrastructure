/* 
 * AccEntryServiceImpl.java  
 * 
 * version v1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.dao.AbstractSubjectDAO;
import com.zlebank.zplatform.acc.dao.AccEntryDAO;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectRuleConfigureDAO;
import com.zlebank.zplatform.acc.dao.TxnsSplitAccountDAO;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.IllegalEntryRequestException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAccEntry;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.ProcessLedgerService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.acc.service.entry.EntryEventHandler;
import com.zlebank.zplatform.acc.service.entry.EntryEventHandlerFactory;
import com.zlebank.zplatform.commons.dao.ChannelDAO;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 上午11:33:54
 * @since
 */
@Service
public class AccEntryServiceImpl
        extends
            AbstractBasePageService<AccEntryQuery, AccEntry>
        implements
            AccEntryService {
    private static final Log log = LogFactory.getLog(AccEntryServiceImpl.class);
    /** 跳过指定分录的标志 **/

    @Autowired
    private AccEntryDAO accEntryDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private SubjectRuleConfigureDAO subjectRuleConfigureDAO;
    @Autowired
    private ProcessLedgerService processLedgerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BusiAcctService busiAcctService;
    @Autowired
    private ChannelDAO channelDAO;
    @Autowired
    private TxnsSplitAccountDAO txnsSplitAccountDAO;
    @Autowired
    private AbstractSubjectDAO abstractSubjectDAO;
    @Autowired
    private EntryEventHandlerFactory entryEventHandlerFactory;
    
    /**
     * 批处理记账【预处理】
     * 
     * @param fetchSize
     * @return
     * @throws AccBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<Long> accountBatchPre(int fetchSize)
            throws AccBussinessException {
        List<Long> accountRecords = new ArrayList<Long>();
        List<PojoAccEntry> records = accEntryDAO.getLockRecords(fetchSize);
        for (PojoAccEntry record : records) {
            accEntryDAO.getByIdForUpdate(fetchSize);
            record.setIsLock(LockStatusType.LOCK);
            accEntryDAO.update(record);
            accountRecords.add(record.getVoucherCode());
        }
        return accountRecords;
    }
    /**
     * 批处理记账【执行】
     * 
     * @param accEntry
     * @throws AccBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void accountBatch(List<Long> accEntry) throws AccBussinessException {
        for (Long id : accEntry) {
            // 得到分录流水
            PojoAccEntry entry = accEntryDAO.get(id);
            // 记账（更新账户 ）
            PojoAccount account = accountDAO.getByAcctCode(entry.getAcctCode());

            if (account == null) {
                throw new AccBussinessException("E000014",
                        new Object[]{entry.getAcctCode()});
            }

            // 验证DAC
            accountService.checkDAC(account);

            // 得到记账金额
            Money updateAmount = Money.valueOf(getUpdateAmount(entry
                    .getAmount().getAmount(), entry, account));

            PojoAccount updateAccount = new PojoAccount();
            updateAccount.setAcctCode(account.getAcctCode());
            updateAccount.setBalance(updateAmount);
            updateAccount.setTotalBanance(updateAmount);
            updateAccount.setFrozenBalance(Money.ZERO);
            int updateCount = abstractSubjectDAO.updateBySql(updateAccount);// 更新叶子科目
            if (updateCount == 0) {
                if (log.isDebugEnabled()) {
                    log.debug("通过SQL方式更新账户时发生错误：");
                    log.debug("更新信息：" + JSONObject.fromObject(updateAccount));
                }
                throw new AccBussinessException("E000018");
            }

            PojoAccount total = new PojoAccount();
            total.setParentSubject(account.getParentSubject());
            total.setBalance(updateAmount);
            total.setTotalBanance(updateAmount);
            processLedgerService.processLedger(total);// 更新总账

            entry.setStatus(AccEntryStatus.ACCOUNTED);// 已记账
            entry.setIsLock(LockStatusType.UNLOCK);// 设置为未锁定
            accEntryDAO.merge(entry);// 更新分录流水状态
        }
    }
     
    /**
     * <p>得到记账所需要更新的金额<p/>
     * 
     * <p>如果分录规则的CRDR和详细科目的CRDR一致的话，则余额为增加<br/>
     * 如果分录规则的CRDR和详细科目的CRDR不一致的话，则余额为减少<p/>
     * @param amount
     * @param entry
     * @param account
     * @return
     */
    private BigDecimal getUpdateAmount(BigDecimal amount,
            PojoAccEntry entry,
            PojoAccount account) {
        return entry.getCrdr().equals(account.getCrdr()) ? amount : amount
                .negate();
    }

    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(AccEntryQuery example) {
        return accEntryDAO.count(example);

    }
    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<AccEntry> getItem(int offset,
            int pageSize,
            AccEntryQuery example) {
        List<AccEntry> accVoLi = new ArrayList<AccEntry>();
        List<PojoAccEntry> list = accEntryDAO.getListByQuery(offset, pageSize,
                example);
        for (PojoAccEntry acc : list) {
            AccEntry accVo = BeanCopyUtil.copyBean(AccEntry.class, acc);
            accVoLi.add(accVo);
        }
        return accVoLi;
    }
    @Override
    public void accEntryProcess(TradeInfo tradeInfo,EntryEvent entryEvent) throws AccBussinessException, AbstractBusiAcctException, NumberFormatException, IllegalEntryRequestException{
        if (log.isDebugEnabled()) {
            log.debug("********账务系统处理开始********");
            log.debug("【传入参数】：" + JSONObject.fromObject(tradeInfo));
        }
        TradeType tradeType = TradeType.fromValue(tradeInfo.getBusiCode());
        EntryEventHandler eventHandler = entryEventHandlerFactory.getEvnetHandler(tradeType, entryEvent);
        
        if(eventHandler == null){
            log.warn("Not supported trade type and entry event mapping.Trade type:"+tradeType+",entry event:"+entryEvent);
            throw new RuntimeException("Not supported trade type and entry event association.Trade type:"+tradeType+",entry event:"+entryEvent);
        }
        
        eventHandler.handle(tradeInfo,entryEvent);
        if (log.isDebugEnabled()) {
            log.debug("********账务系统处理结束********");
        }
    }
}
