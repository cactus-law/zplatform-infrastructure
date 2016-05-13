package com.zlebank.zplatform.acc.service.entry;

import java.math.BigDecimal;

import org.cheffo.jeplite.JEP;
import org.cheffo.jeplite.ParseException;
import org.cheffo.jeplite.util.DoubleStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.dao.AbstractSubjectDAO;
import com.zlebank.zplatform.acc.dao.AccEntryDAO;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectRuleConfigureDAO;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.ProcessLedgerService;

public abstract class AbstractEventHandler implements EntryEventHandler {

    @Autowired
    protected SubjectRuleConfigureDAO subjectRuleConfigureDAO;

    @Autowired
    protected AccEntryDAO accEntryDAO;
    @Autowired
    protected AccCodePlaceholderFactory accCodePlaceholderFactory;
    @Autowired
    protected AccountService accountService;
    @Autowired
    protected AccountDAO accountDAO;
    @Autowired
    protected AbstractSubjectDAO abstractSubjectDAO;
    @Autowired
    protected ProcessLedgerService processLedgerService;
    
    private static final String LITERAL_COMPUTE_FACTOR_PRINCIPAL = "A";// 本金
    private static final String LITERAL_COMPUTE_FACTOR_COMMISSION = "B";// 佣金
    private static final String LITERAL_COMPUTE_FACTOR_FEE = "C";// 商户手续费
    private static final String LITERAL_COMPUTE_FACTOR_D = "D";// 商户手续费
    private static final String LITERAL_COMPUTE_FACTOR_E = "E";// 商户手续费
    private static final String LITERAL_COMPUTE_FACTOR_CHANNEL_FEE = "T";//通道手续费

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void handle(TradeInfo tradeInfo, EntryEvent entryEvent)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {

        if (!isConditionStatified(tradeInfo,entryEvent)) {
            // TODO throw new exception
            throw new RuntimeException("记账条件不满足,见检查交易信息和分录事件.交易序列号:"+tradeInfo.getTxnseqno()+",分录事件:"+entryEvent);
        }

        realHandle(tradeInfo, entryEvent);
    }

    protected abstract void realHandle(TradeInfo tradeInfo,
            EntryEvent entryEvent) throws AccBussinessException,
            AbstractBusiAcctException, NumberFormatException;

    protected abstract boolean isConditionStatified(TradeInfo tradeInfo,EntryEvent entryEvent);
    
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
    protected BigDecimal getAccEntryAmount(String ordform, TradeInfo tradeInfo)
            throws AccBussinessException {
        ordform = ordform.toUpperCase();
        // 通道手续费未记账时为0，对账完成后更新值。
        JEP jep = new JEP();
        if (ordform.contains(LITERAL_COMPUTE_FACTOR_PRINCIPAL))
            jep.addVariable(LITERAL_COMPUTE_FACTOR_PRINCIPAL, tradeInfo
                    .getAmount().doubleValue());// 本金
        if (ordform.contains(LITERAL_COMPUTE_FACTOR_COMMISSION))
            jep.addVariable(LITERAL_COMPUTE_FACTOR_COMMISSION, tradeInfo
                    .getCommission().doubleValue());// 佣金
        if (ordform.contains(LITERAL_COMPUTE_FACTOR_FEE))
            jep.addVariable(LITERAL_COMPUTE_FACTOR_FEE, tradeInfo.getCharge()
                    .doubleValue());// 手续费
        if (ordform.contains(LITERAL_COMPUTE_FACTOR_D))
            jep.addVariable(LITERAL_COMPUTE_FACTOR_D, tradeInfo.getCharge()
                    .doubleValue());// 金额D
        if (ordform.contains(LITERAL_COMPUTE_FACTOR_E))
            jep.addVariable(LITERAL_COMPUTE_FACTOR_E, tradeInfo.getCharge()
                    .doubleValue());// 金额E
        if (ordform.contains(LITERAL_COMPUTE_FACTOR_CHANNEL_FEE))
            jep.addVariable(LITERAL_COMPUTE_FACTOR_CHANNEL_FEE, tradeInfo.getChannelFee()
                    .doubleValue());// 通道成本
        jep.parseExpression(ordform);
        DoubleStack stack = new DoubleStack();
        try {
            return new BigDecimal(jep.getValue(stack));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new AccBussinessException("E000010", new Object[]{ordform});
        }
    }
}
