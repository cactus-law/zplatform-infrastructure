package com.zlebank.zplatform.acc.service.entry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAccEntry;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.pojo.PojoSubjectRuleConfigure;

@Service("defaultEventHandler")
public class DefaultEventHandler extends AbstractEventHandler {

    private final static Log log = LogFactory.getLog(DefaultEventHandler.class);


    @Override
    final protected void realHandle(TradeInfo tradeInfo, EntryEvent entryEvent)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {

        /* 根据交易类型获取分录规则 */
        List<PojoSubjectRuleConfigure> entryRuleList = subjectRuleConfigureDAO
                .getRulesByTradeAndEvent(tradeInfo.getBusiCode(), entryEvent);
        if (entryRuleList == null || entryRuleList.isEmpty())
            throw new AccBussinessException("E000012");

        /* 循环分录规则，插入分录流水 。插入中同时处理同步记账 */
        BigDecimal balanceTest = BigDecimal.ZERO;
        for (PojoSubjectRuleConfigure entryRule : entryRuleList) { 
            AcctCodePlaceHolder acctCodePlaceHolder = accCodePlaceholderFactory
                    .getAcctCodePlaceholder(entryRule.getAcctCodeType(),
                            entryRule.getAcctCode(), tradeInfo);

            String accCode = acctCodePlaceHolder.getAccCode();
            
            BigDecimal accrual = getAccEntryAmount(
                    entryRule.getEntryAlgorithm(), tradeInfo);
            
            balanceTest = insertEntry(accCode, accrual, tradeInfo, entryRule,
                    entryEvent, balanceTest);
        }
        /* 试算平衡校验 */
        if (BigDecimal.ZERO.compareTo(balanceTest.setScale(0, RoundingMode.HALF_UP)) != 0) {
            throw new AccBussinessException("E000011");
        }
    }
    @Override
    final protected boolean isConditionStatified(TradeInfo tradeInfo,
            EntryEvent entryEvent) {
        /* 检查交易流水号是否有关联的分录流水 */
        List<PojoAccEntry> list = accEntryDAO.getByTxnNo(
                tradeInfo.getTxnseqno(), tradeInfo.getBusiCode(), entryEvent);
        if (!list.isEmpty()) {
            return false;
        }

        if (tradeInfo.isSplit()) {// 如果有分账
            // TODO 暂时先不支持分账
            // throw new NotsupportSplitException();
            return false;
        }
        return true;
    }

    /**
     * 将分录流水持久化，返回需要同步记账的分录流水
     * 
     * @param tradeInfo
     * @param rule
     * @param balanceTest
     * @return
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    private BigDecimal insertEntry(String accCode,
            BigDecimal accrual,
            TradeInfo tradeInfo,
            PojoSubjectRuleConfigure rule,
            EntryEvent entryEvent,
            BigDecimal balanceTest) throws AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {

        /* 保存分录流水 */
        PojoAccEntry entry = new PojoAccEntry();
        entry.setAcctCode(accCode);
        entry.setCrdr(rule.getCrdr());
        entry.setAmount(Money.valueOf(accrual));
        entry.setTxnseqno(tradeInfo.getTxnseqno());
        entry.setPayordno(tradeInfo.getPayordno());// 支付订单号
        entry.setIsLock(LockStatusType.UNLOCK);
        entry.setEntryEvent(entryEvent);
        entry.setInTime(new Date());
        entry.setBusiCode(tradeInfo.getBusiCode());
        if ("1".equals(rule.getSyncFlag())) {// 同步记账
            handleSynAccount(entry);
        } else {// 非同步记账,设置状态为未记账
            entry.setStatus(AccEntryStatus.WAIT_ACCOUNTED);
        }
        accEntryDAO.merge(entry);
        // 试算平衡
        if (CRDRType.CR == rule.getCrdr()) {
            balanceTest = balanceTest.add(accrual);
        } else {
            balanceTest = balanceTest.subtract(accrual);
        }
        return balanceTest;
    }
    /**
     * 处理同步记账
     * 
     * @throws AccBussinessException
     */
    private void handleSynAccount(PojoAccEntry entry)
            throws AccBussinessException {
        /* 同步记账 */
        PojoAccount account = accountDAO.getByAcctCode(entry.getAcctCode());
        if (account == null) {
            throw new AccBussinessException("E000014",
                    new Object[]{entry.getAcctCode()});
        }
        accountService.checkDAC(account);

        Money actualAmount = Money.valueOf(getUpdateAmount(entry.getAmount()
                .getAmount(), entry, account));// 实际发生额

        // 余额是否足够
        BigDecimal calcAmount = account.getBalance().plus(actualAmount)
                .getAmount();
        if (calcAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccBussinessException("E000019");
        }
        checkAccountStatus(account.getStatus(), actualAmount,
                entry.getAcctCode());
        // 更新账户
        PojoAccount updateAccount = new PojoAccount();
        updateAccount.setAcctCode(account.getAcctCode());
        updateAccount.setBalance(actualAmount);
        updateAccount.setTotalBanance(actualAmount);
        updateAccount.setFrozenBalance(Money.ZERO);

        int updateCount = abstractSubjectDAO.updateBySql(updateAccount);
        if (updateCount == 0) {
            if (log.isDebugEnabled()) {
                log.debug("通过SQL方式更新账户时发生错误：");
                log.debug("更新信息：" + JSONObject.fromObject(updateAccount));
            }
            throw new AccBussinessException("E000018");
        }

        // 更新总账
        PojoAccount total = new PojoAccount();
        total.setParentSubject(account.getParentSubject());
        total.setBalance(actualAmount);
        total.setTotalBanance(actualAmount);
        processLedgerService.processLedger(total);// 更新总账
        entry.setStatus(AccEntryStatus.ACCOUNTED);// 已记账
    }

    
    /**
     * <p>
     * 得到记账所需要更新的金额
     * <p/>
     * <p>
     * 如果分录规则的CRDR和详细科目的CRDR一致的话，则余额为增加<br/>
     * 如果分录规则的CRDR和详细科目的CRDR不一致的话，则余额为减少
     * <p/>
     * 
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
     * @param accStatus
     * @param actualAmount
     * @param acctCode
     * @throws AccBussinessException
     */
    private void checkAccountStatus(AcctStatusType accStatus,
            Money actualAmount,
            String acctCode) throws AccBussinessException {
        /* 金额增加时，判断是否止入或冻结 */
        if (actualAmount.compareTo(Money.ZERO) >= 0
                && (accStatus == AcctStatusType.STOP_IN || accStatus == AcctStatusType.FREEZE)) {
            throw new AccBussinessException("E000015", new Object[]{acctCode});
        }

        /* 金额增加时，判断是否止入或冻结 */
        if (actualAmount.compareTo(Money.ZERO) < 0
                && (accStatus == AcctStatusType.STOP_OUT || accStatus == AcctStatusType.FREEZE)) {
            throw new AccBussinessException("E000016", new Object[]{acctCode});
        }
    }
}
