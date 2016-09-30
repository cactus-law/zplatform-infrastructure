package com.zlebank.zlpatform.acc2.tools;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.zlebank.zlpatform.acc2.util.ApplicationContextAbled;
import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.dao.TradeEntryEventMappingDAO;
import com.zlebank.zplatform.acc.pojo.PojoTradeEntryEventMapping;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

public class ImportTradeEntryEventMapping extends ApplicationContextAbled{
    
    private ExcelReader excelReader = new ExcelReader();
    
    private TradeEntryEventMappingDAO tradeEntryEventMappingDAO;
    
    @Before
    public void init() {
        tradeEntryEventMappingDAO = context.getBean(TradeEntryEventMappingDAO.class);
    }
    @Test
    public void importt() {
        try {
            List<String[]> list = excelReader.readExcle("交易类型事件映射");

            for (int i = 51; i < list.size(); i++) {
                String[] str = (String[]) list.get(i);
                PojoTradeEntryEventMapping tradeEntryEventMapping = new PojoTradeEntryEventMapping();
                int j = 0;
                tradeEntryEventMapping.setTradeType(TradeType.fromValue(str[j]));
                tradeEntryEventMapping.setEntryEvent(EntryEvent.fromValue(str[++j]));
                tradeEntryEventMapping.setImplClassName(str[++j]);
                tradeEntryEventMappingDAO.save(tradeEntryEventMapping);
            }
        } catch(Exception e){
            Assert.fail();
            e.printStackTrace();
        }
    }
}
