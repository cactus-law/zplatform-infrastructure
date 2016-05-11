package com.zlebank.zlpatform.acc2.entry;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.ApplicationContextUtil;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.dao.TradeEntryEventMappingDAO;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoTradeEntryEventMapping;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

public class SyncEntryTest {
    
    private ApplicationContext context = ApplicationContextUtil.get();
    private AccEntryService accEntryService;
    private List<TradeInfo> tradeInfos = new ArrayList<TradeInfo>();
    
    @Before
    public void init(){
        accEntryService = (AccEntryService)context.getBean("accEntryServiceImpl");
        TradeEntryEventMappingDAO tradeEntryEventMappingDAO = (TradeEntryEventMappingDAO)context.getBean("tradeEntryEventMappingDAOImpl");
        List<PojoTradeEntryEventMapping> tradeEntryEventMappingList = tradeEntryEventMappingDAO.queryAll();
        TradeType[] tradeTypes = TradeType.values();
        for(TradeType tradeType : tradeTypes){
            TradeInfo tradeInfo = newTrandeInfo(tradeType);
            tradeInfos.add(tradeInfo);
        }
    }
    
    @Test
    public void test(){
        for(TradeInfo tradeInfo:tradeInfos){
            try {
                accEntryService.accEntryProcess(tradeInfo,EntryEvent.TRADE_SUCCESS);
            } catch (AccBussinessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (AbstractBusiAcctException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    private class TradeInfoWrapper{
        private EntryEvent entryEvent;
        private TradeInfo tradeInfo;
        private int order;
        public EntryEvent getEntryEvent() {
            return entryEvent;
        }
        public void setEntryEvent(EntryEvent entryEvent) {
            this.entryEvent = entryEvent;
        }
        public TradeInfo getTradeInfo() {
            return tradeInfo;
        }
        public void setTradeInfo(TradeInfo tradeInfo) {
            this.tradeInfo = tradeInfo;
        }
        public int getOrder() {
            return order;
        }
        public void setOrder(int order) {
            this.order = order;
        }
    }
    
    public TradeInfo newTrandeInfo(TradeType tradeType){
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setSplit(false);
        //TODO init tradeInfo
        return tradeInfo;
    }
}
