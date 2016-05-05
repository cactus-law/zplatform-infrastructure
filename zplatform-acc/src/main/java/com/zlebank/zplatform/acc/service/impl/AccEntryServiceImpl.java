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
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cheffo.jeplite.JEP;
import org.cheffo.jeplite.ParseException;
import org.cheffo.jeplite.util.DoubleStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.AbstractSubjectDAO;
import com.zlebank.zplatform.acc.dao.AccEntryDAO;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectRuleConfigureDAO;
import com.zlebank.zplatform.acc.dao.TxnsSplitAccountDAO;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAccEntry;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.pojo.PojoSubjectRuleConfigure;
import com.zlebank.zplatform.acc.pojo.PojoTxnsSplitAccount;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.ProcessLedgerService;
import com.zlebank.zplatform.commons.dao.ChannelDAO;
import com.zlebank.zplatform.commons.dao.pojo.PojoChannel;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;

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
    private static final String CONTINUE = "continue";
    /** 【匿名交易】付款方为指定字符时，跳过此分录规则。 **/
    private static final String ANONYMOUS_FLAG = "999999999999999";
    /** 科目代号为 T001 时，为通道成本账户 **/
    private static final String T001 = "T001";
    /** 科目代号为 T002 时，为通道备付金账户 **/
    private static final String T002 = "T002";
    /** 科目代号为 T003 时，为通道应收款账户 **/
    private static final String T003 = "T003";

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
    /**
     *
     * @param tradeInfo
     * @throws AccBussinessException
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws BussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void accEntryProcess(TradeInfo tradeInfo)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException, AccBussinessException {
        if (log.isDebugEnabled()) {
            log.debug("********账务系统处理开始********");
            log.debug("【传入参数】：" + JSONObject.fromObject(tradeInfo));
        }

        BigDecimal balanceTest = BigDecimal.ZERO;

        /* 检查当前的【交易流水号】是否已经记录过分录流水 */
        List<PojoAccEntry> checkTxnNo = accEntryDAO.getByTxnNo(
                tradeInfo.getTxnseqno(), tradeInfo.getBusiCode());
        if (checkTxnNo != null && checkTxnNo.size() > 0) {
            throw new AccBussinessException("EASAC0008");
        }
        if (log.isDebugEnabled()) {
            log.debug("【分录流水是否已经记账检查】：通过");
        }

        /* 根据交易类型获取分录规则 */
        List<PojoSubjectRuleConfigure> list = subjectRuleConfigureDAO
                .getRulesByBusiCode(tradeInfo.getBusiCode());
        if (list == null || list.size() == 0)
            throw new AccBussinessException("E000012");

        /* 如果交易是分账,获取分账信息 */
        List<PojoTxnsSplitAccount> txnsSplitAccountList = new ArrayList<PojoTxnsSplitAccount>();
        if (tradeInfo.isSplit()) {// 如果有分账
            txnsSplitAccountList = txnsSplitAccountDAO.getByTxnseqno(tradeInfo
                    .getTxnseqno());
            if (txnsSplitAccountList == null
                    || txnsSplitAccountList.size() == 0) {
                throw new AccBussinessException("E000017");
            }
        }

        /* 循环分录规则，插入分录流水 */
        int rulesNo = 1;
        for (PojoSubjectRuleConfigure rule : list) {
            if (log.isDebugEnabled()) {
                log.debug("分录规则" + rulesNo++ + "："
                        + JSONObject.fromObject(rule));
            }
            String subject = getSubjectByFlag(rule.getFlag(), tradeInfo);
            // 匿名支付时跳过付款方的分录流水
            if (CONTINUE.equals(subject))
                continue;

            String firstChar = String.valueOf(rule.getFlag().charAt(0))
                    .toUpperCase();
            if (tradeInfo.isSplit() && "S".equals(firstChar)) {// 如果是收款方分账
                TradeInfo copyTradeInfo = BeanCopyUtil.copyBean(
                        TradeInfo.class, tradeInfo);
                for (PojoTxnsSplitAccount receiveMem : txnsSplitAccountList) {
                    copyTradeInfo.setPayToMemberId(receiveMem.getMemberid());
                    copyTradeInfo.setAmount(receiveMem.getAmount());
                    copyTradeInfo.setCommission(receiveMem.getCommamt());

                    balanceTest = insertEntry(copyTradeInfo, rule, balanceTest);
                }
            } else {
                balanceTest = insertEntry(tradeInfo, rule, balanceTest);
            }
        }

        /*试算平衡校验*/
        if (BigDecimal.ZERO.compareTo(balanceTest) != 0) {
            throw new AccBussinessException("E000011");
        }
        if (log.isDebugEnabled()) {
            log.debug("********账务系统处理结束********");
        }
    }
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
     * 分录金额计算
     * 
     * @param ordform
     *            分录金额计算规则 例：（A+B）
     * @param tradeInfo
     *            交易流水
     * @return
     * @throws AccBussinessException
     */
    private BigDecimal getAccEntryAmount(String ordform, TradeInfo tradeInfo)
            throws AccBussinessException {
        ordform = ordform.toUpperCase();
        // 通道手续费未记账时为0，对账完成后更新值。
        if (ordform.startsWith("T")) {
            return BigDecimal.ZERO;
        }
        JEP jep = new JEP();
        if (ordform.contains("A"))
            jep.addVariable("A", tradeInfo.getAmount().doubleValue());// 本金
        if (ordform.contains("B"))
            jep.addVariable("B", tradeInfo.getCommission().doubleValue());// 佣金
        if (ordform.contains("C"))
            jep.addVariable("C", tradeInfo.getCharge().doubleValue());// 手续费
        if (ordform.contains("D"))
            jep.addVariable("D", tradeInfo.getCharge().doubleValue());// 金额D
        if (ordform.contains("E"))
            jep.addVariable("E", tradeInfo.getCharge().doubleValue());// 金额E
        jep.parseExpression(ordform);
        DoubleStack stack = new DoubleStack();
        try {
            return new BigDecimal(jep.getValue(stack));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AccBussinessException("E000010", new Object[]{ordform});
        }
    }
    /**
     * 根据科目代码取得具体科目
     * 
     * @【S】代表收款方【F】代表付款方【Y】代表佣金方
     * @如果是具体科目，则原样返回。
     * @如果是科目标识，则处理后返回处理结果。
     * @param subject
     * @return 具体科目
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    private String getSubjectByFlag(String subject, TradeInfo tradeInfo)
            throws AbstractBusiAcctException, NumberFormatException,
            AccBussinessException {
        // 如果是具体科目，则原样返回。
        if (Character.isDigit(subject.charAt(0))) {
            return subject;
        }
        // 取【收/付】款账户
        String memberId = "";
        // 取得首字母
        String firstChar = String.valueOf(subject.charAt(0)).toUpperCase();
        if ("S".equals(firstChar)) {
            memberId = tradeInfo.getPayToMemberId();
        } else if ("F".equals(firstChar)) {
            // 如果是匿名交易的话，跳过付款方分录。
            if (ANONYMOUS_FLAG.equals(tradeInfo.getPayMemberId())) {
                return CONTINUE;
            }
            memberId = tradeInfo.getPayMemberId();
        } else if ("Y".equals(firstChar)) {
            // 根据收款方memberId找到取佣金账户
            memberId = tradeInfo.getPayToParentMemberId();
        } else if ("T".equals(firstChar)) {
            // 根据通道找到相应的成本账户
            PojoChannel channel = channelDAO.getByChnlCode(tradeInfo
                    .getChannelId());
            if (channel == null) {
                log.error("根据通道ID没找到相应的通道 ->通道ID：" + tradeInfo.getChannelId());
                throw new AccBussinessException("E000020",
                        new Object[]{tradeInfo.getChannelId()});
            }
            if (T001.equals(subject)) {
                // 返回 通道成本账户
                return channel.getAcctCodeCost();
            } else if (T002.equals(subject)) {
                // 返回 通道备付金账户
                return channel.getAcctCodeDeposit();
            } else if (T003.equals(subject)) {
                // 返回 通道应收款账户
                return channel.getAcctCodeReceivable();
            } else {
                throw new AccBussinessException("E000013",
                        new Object[]{subject});
            }
        }
        if (StringUtil.isEmpty(memberId))
            throw new AccBussinessException("E000013", new Object[]{subject});
        String usage = subject.substring(1);
        String account = busiAcctService.getAccount(Usage.fromValue(usage),
                memberId);
        return account;
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
     * 记录分录流水
     * 
     * @param tradeInfo
     * @param rule
     * @param balanceTest
     * @return
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    private BigDecimal insertEntry(TradeInfo tradeInfo,
            PojoSubjectRuleConfigure rule,
            BigDecimal balanceTest) throws AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {

        String subject = getSubjectByFlag(rule.getFlag(), tradeInfo);
      
        BigDecimal amount = getAccEntryAmount(rule.getEntryAlgorithm(),
                tradeInfo);
        
        /* 保存分录流水*/
        PojoAccEntry entry = new PojoAccEntry();
        entry.setAcctCode(subject);
        entry.setCrdr(rule.getCrdr());
        entry.setAmount(Money.valueOf(amount));
        entry.setTxnseqno(tradeInfo.getTxnseqno());
        entry.setPayordno(tradeInfo.getPayordno());// 支付订单号
        entry.setIsLock(LockStatusType.UNLOCK);
        
        /* 同步记账*/
        PojoAccount account = accountDAO.getByAcctCode(entry.getAcctCode());
        if (account == null) {
            throw new AccBussinessException("E000014",
                    new Object[]{entry.getAcctCode()});
        }
        if ("1".equals(rule.getSyncFlag())) {
             
            accountService.checkDAC(account);

            Money actualAmount = Money.valueOf(getUpdateAmount(amount, entry,
                    account));//实际发生额

            /*金额增加时，判断是否止入或冻结*/
            if (actualAmount.compareTo(Money.ZERO) >= 0
                    && (account.getStatus() == AcctStatusType.STOP_IN || account
                            .getStatus() == AcctStatusType.FREEZE)) {
                throw new AccBussinessException("E000015",
                        new Object[]{entry.getAcctCode()});
            }
            
            /*金额增加时，判断是否止入或冻结*/
            if (actualAmount.compareTo(Money.ZERO) < 0
                    && (account.getStatus() == AcctStatusType.STOP_OUT || account
                            .getStatus() == AcctStatusType.FREEZE)) {
                throw new AccBussinessException("E000016",
                        new Object[]{entry.getAcctCode()});
            }
            // 余额是否足够
            BigDecimal calcAmount = account.getBalance().plus(actualAmount)
                    .getAmount();
            if (calcAmount.compareTo(BigDecimal.ZERO) < 0) {
                throw new AccBussinessException("E000019");
            }

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
        } else {//非同步记账,设置状态为未记账
            entry.setStatus(AccEntryStatus.NOT_ACCOUNTED);
        }
        entry.setInTime(new Date());
        entry.setBusiCode(tradeInfo.getBusiCode());
        accEntryDAO.merge(entry);
        // 试算平衡
        if (CRDRType.CR == rule.getCrdr()) {
            balanceTest = balanceTest.add(amount);
        } else {
            balanceTest = balanceTest.subtract(amount);
        }
        return balanceTest;
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
}
