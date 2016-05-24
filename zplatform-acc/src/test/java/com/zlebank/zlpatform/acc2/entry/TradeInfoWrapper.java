package com.zlebank.zlpatform.acc2.entry;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

public class TradeInfoWrapper {
    private EntryEvent entryEvent;
    private TradeType tradeType;
    private TradeInfo tradeInfo;

    public TradeInfoWrapper(EntryEvent entryEvent, TradeType tradeType) {
        this.entryEvent = entryEvent;
        this.tradeType = tradeType;
    }

    public TradeType getTradeType() {
        return tradeType;
    }
    public EntryEvent getEntryEvent() {
        return entryEvent;
    }

    public TradeInfo getTradeInfo() {
        return tradeInfo;
    }

    public void setTradeInfo(TradeInfo tradeInfo) {
        this.tradeInfo = tradeInfo;
    }
}
