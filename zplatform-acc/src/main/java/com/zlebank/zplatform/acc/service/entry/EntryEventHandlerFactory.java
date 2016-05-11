package com.zlebank.zplatform.acc.service.entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.dao.TradeEntryEventMappingDAO;
import com.zlebank.zplatform.acc.pojo.PojoTradeEntryEventMapping;
import com.zlebank.zplatform.acc.util.SpringApplicationObjectSupport;

/**
 * 
 * TODO
 *
 * @author yangying
 * @version
 * @date 2016年5月9日 下午4:56:16
 * @since
 */
@Service
public class EntryEventHandlerFactory extends SpringApplicationObjectSupport{
    private static final Log log = LogFactory
            .getLog(EntryEventHandlerFactory.class);
    @Autowired
    private TradeEntryEventMappingDAO tradeEntryEventMappingDAO;

    private final static Map<String, EntryEventHandler> hanlderImplCachedMap = new HashMap<String, EntryEventHandler>();

    private AtomicBoolean isInit = new AtomicBoolean(false);

    /**
     * TODO
     */
    public EntryEventHandler getEvnetHandler(TradeType tradeType,
            EntryEvent entryEvent) {
        synchronized (this) {
            if (!isInit.get()) {
                refresh();
            }
        }

        String compositKey = tradeType.getCode() + entryEvent.getCode();

        if (!hanlderImplCachedMap.containsKey(compositKey)) {
            // could not found handler from cachedMap
            // TODO throw new exception
        }
        return hanlderImplCachedMap.get(compositKey);
    }

    private void refresh() {
        fetchApplicationContext();
        List<PojoTradeEntryEventMapping> tradeEntryEventMappingList = tradeEntryEventMappingDAO
                .queryAll();
        for (PojoTradeEntryEventMapping tradeEntryEventMapping : tradeEntryEventMappingList) {
            String compositKey = tradeEntryEventMapping.getTradeType()
                    .getCode()
                    + tradeEntryEventMapping.getEntryEvent().getCode();
            EntryEventHandler eventHandler;
            String eventHandlerClassName = null;
            try {
                eventHandlerClassName = tradeEntryEventMapping
                        .getImplClassName();
                eventHandler = (EntryEventHandler) applicationContext.getBean(Class.forName(eventHandlerClassName));
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("实例化分录事件处理器发生错误[compositKey:");
                sb.append(" EventHandler:");
                sb.append(eventHandlerClassName);
                sb.append("],请排查原因.Caused by :");
                sb.append(e.getMessage());
                log.error(sb.toString());
                e.printStackTrace();
                continue;
            }
            hanlderImplCachedMap.put(compositKey, eventHandler);
        }
        isInit.getAndSet(true);
    }
}
