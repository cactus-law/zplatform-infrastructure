package com.zlebank.zplatform.acc.service.entry;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;

/**
 * 
 * TODO
 *
 * @author yangying
 * @version
 * @date 2016年5月9日 下午5:28:08
 * @since
 */
public interface EntryEventHandler {
    /**
     * TODO
     */
    void handle(TradeInfo tradeInfo, EntryEvent entryEvent)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException;
}
