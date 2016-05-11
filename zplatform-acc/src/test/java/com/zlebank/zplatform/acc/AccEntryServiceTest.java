/* 
 * AccEntryServiceTest.java  
 * 
 * version v1.0
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 上午11:44:17
 * @since 
 */
public class AccEntryServiceTest {
    static ApplicationContext context = null;
    static AccEntryService service = null;
    static {
        context = ApplicationContextUtil.get();
        service =  (AccEntryService) context.getBean("accEntryServiceImpl");
    }
    /**
     * 充值动作
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
     
    public void accEntryProcessTest() throws AccBussinessException, AbstractBusiAcctException, NumberFormatException{
        TradeInfo entry = new TradeInfo();
        entry.setPayMemberId("10000000224");
        entry.setPayToParentMemberId("10000000227");
//        entry.setPayToMemberId("10000027");
//        entry.setChannelId("01");
//        entry.setProductId("10010");
        entry.setTxnseqno("100000000000001245");
        entry.setBusiCode(TradeType.RECHARGE.getCode());
        entry.setAmount(new BigDecimal(100));
        entry.setCommission(new BigDecimal(1));
        entry.setCharge(new BigDecimal(9));
        
        boolean success = true;
        try {
            service.accEntryProcess(entry,EntryEvent.TRADE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        assertEquals(true,success);
    }
    /**
     * 通过渠道充值动作
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    
    public void accEntryProcessChannleTest() throws AccBussinessException, AbstractBusiAcctException, NumberFormatException{
        TradeInfo entry = new TradeInfo();
        entry.setPayMemberId("10000000224");
//        entry.setPayToMemberId("10000027");
        entry.setChannelId("01");
//        entry.setProductId("10010");
        entry.setTxnseqno("100000000000001245");
        entry.setBusiCode(TradeType.RECHARGE.getCode());
        entry.setAmount(new BigDecimal(100));
        entry.setCommission(new BigDecimal(1));
        entry.setCharge(new BigDecimal(9));
        
        boolean success = true;
        try {
            service.accEntryProcess(entry,EntryEvent.TRADE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        assertEquals(true,success);
    }
    /**
     * 转账动作
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
     
    public void accEntryProcessTransfer() throws AccBussinessException, AbstractBusiAcctException, NumberFormatException{
        TradeInfo entry = new TradeInfo();
        entry.setPayMemberId("10000000227");
        entry.setPayToMemberId("10000000224");
        entry.setTxnseqno("100000000000001245");
        entry.setBusiCode(TradeType.TRANSFER.getCode());
        entry.setAmount(new BigDecimal(100));
        entry.setCommission(new BigDecimal(1));
        entry.setCharge(new BigDecimal(9));

        boolean success = true;
        try {
            service.accEntryProcess(entry,EntryEvent.TRADE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        assertEquals(true,success);
    }
    
    /**
     * 测试时间任务是否正常
     */
    @Ignore
    @Test
    public void jobTest() {
        try   
       {     
            // 手动停止
            Thread.sleep(24*60*60*1000);
        }   
        catch (Exception e)   
       {     
           e.printStackTrace();     
        }     
    }
}
