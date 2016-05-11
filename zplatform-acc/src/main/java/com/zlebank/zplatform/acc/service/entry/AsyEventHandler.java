package com.zlebank.zplatform.acc.service.entry;

import com.zlebank.zplatform.acc.bean.TradeInfo;

public class AsyEventHandler extends AbstractEventHandler {

    @Override
    final protected void realHandle(TradeInfo tradeInfo,EntryEvent entryEvent) {
        /* 根据该交易流水号,分录事件有关联的分录流水 */

        /* 循环分录流水 ，将分录状态改为02-可记账 */

    }
    
    @Override
    final protected boolean isConditionStatified(TradeInfo tradeInfo) {
        /* 检查交易流水号是否有关联的分录流水 */

        if (tradeInfo.isSplit()) {// 如果有分账
            // TODO 暂时先不支持分账
            // throw new NotsupportSplitException();
        }
        
        return true;
    }
}
