package com.zlebank.zlpatform.acc2.tools;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import org.junit.Before;

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
    
    
    public void importt() {
        try {
            List<String[]> list = excelReader.readExcle("交易类型事件映射");

            for (int i = 0; i < list.size(); i++) {
                String[] str = (String[]) list.get(i);
                PojoTradeEntryEventMapping tradeEntryEventMapping = new PojoTradeEntryEventMapping();
                int j = 0;
                tradeEntryEventMapping.setTradeType(TradeType.fromValue(str[j]));
                tradeEntryEventMapping.setEntryEvent(EntryEvent.fromValue(str[++j]));
                tradeEntryEventMapping.setImplClassName(str[++j]);
                tradeEntryEventMappingDAO.save(tradeEntryEventMapping);
            }
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
}
