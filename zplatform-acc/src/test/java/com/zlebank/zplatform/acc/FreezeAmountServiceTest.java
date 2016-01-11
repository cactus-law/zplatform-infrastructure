/* 
 * FreezeAmountServiceTest.java  
 * 
 * version TODO
 *
 * 2015年8月26日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.FreezeAmountService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月26日 上午9:53:40
 * @since 
 */
public class FreezeAmountServiceTest {
    private final static ResourceBundle  RESOURCE = ResourceBundle.getBundle("job-setting");
    static ApplicationContext context = null;
    static FreezeAmountService service = null;
    static {
        context = ApplicationContextUtil.get();
        service =  (FreezeAmountService) context.getBean("freezeAmountServiceImpl");
    }
    /**
     * 冻结指定账户的指定金额（有可能是订单）
     * @param account
     * @return
     */
     
    public void freezeAmountTest(){
        AccountAmount aa = new AccountAmount();
        aa.setTxnseqno("10000000086");//冻结订单号
        aa.setAccId(10003L);// 账户号
        aa.setFrozenBalance(Money.valueOf(new BigDecimal(100)));// 冻结金额
        aa.setFrozenTime(1L);//冻结时间范围
        aa.setFrozenSTime(new Date());// 冻结开始时间
        aa.setNotes("退货");
        aa.setInuser(45L);
        aa.setUpuser(45L);
        boolean success=true;
        try { service.freezeAmount(aa); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(true,success);
        
        // 异常系：账户不存在
        aa.setAccId(0L);// 账户号
        try { service.freezeAmount(aa); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(false,success);
        
        aa.setAccId(10003L);// 账户号
        // 异常系：冻结金额是负数
        aa.setFrozenBalance(Money.valueOf(new BigDecimal(-100)));// 冻结金额
        try { service.freezeAmount(aa); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(false,success);
        
        // 异常系：冻结金额比可用余额还大
        aa.setFrozenBalance(Money.valueOf(new BigDecimal(999999999)));// 冻结金额
        try { service.freezeAmount(aa); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(false,success);
        
        // 异常系：用户为空
        aa.setFrozenBalance(Money.valueOf(new BigDecimal(100)));// 冻结金额
        aa.setInuser(null);
        try { service.freezeAmount(aa); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(false,success);
        
        // 异常系：冻结时间不符合规范
        aa.setInuser(45L);
        aa.setFrozenTime(-10L);
        try { service.freezeAmount(aa); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(false,success);
        
    }
    /**
     * 解冻指定的冻结ID
     * @param account
     * @return
     */
     
    public void unFreezeAmountTest(){ 
        AccountAmount aa = new AccountAmount();
        aa.setId(66L);
        boolean success=true;
        try { service.unFreezeAmount(aa,false); } catch (Exception e) { success=false; e.printStackTrace(); }
        assertEquals(true,success);
    }
    /**
     * 批处理解冻【预处理】【执行】
     * @param account
     * @return
     */
     
    public void unFreezeAmountBatchPre(){
        int count=0;
        List<Long> frozenRecords = null;
        boolean success=true;
        while(true) {
            try {
                frozenRecords= service.unFreezeAmountBatchPre(Integer.parseInt(RESOURCE.getString("frozen_process_num")));
                service.unFreezeAmountBatch(frozenRecords);
            } catch (Exception e) {
                e.printStackTrace();
                success=false;
                continue;
            }
            System.out.println("---------本次处理了："+frozenRecords.size()+"条-----------");
            count+=frozenRecords.size();
            if (frozenRecords==null || frozenRecords.size()==0) {
                break;
            }
        }
        assertEquals(true,success);
        System.out.println("---------一共处理了："+count+"条-----------");
    }
    
    /**
     * 测试时间任务是否正常
     */
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
