package com.zlebank.zplatform.acc.service.entry;

import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public void handle(TradeInfo tradeInfo, EntryEvent entryEvent)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {

        if (!isConditionStatified(tradeInfo)) {
            // TODO throw new exception
            return;
        }

        realHandle(tradeInfo, entryEvent);

        /* 试算平衡校验 */
    }

    protected abstract void realHandle(TradeInfo tradeInfo,
            EntryEvent entryEvent) throws AccBussinessException,
            AbstractBusiAcctException, NumberFormatException;

    protected abstract boolean isConditionStatified(TradeInfo tradeInfo);
}
