/* 
 * Main.java  
 * 
 * version 1.0
 *
 * 2015年8月28日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import jxl.read.biff.BiffException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.FreezeAcctService;
import com.zlebank.zplatform.acc.service.FreezeAmountService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
         *********************************************************************************************
         1:开户               2:销户               3:冻结               4:止入               5：止出
         10:新增科目         11:修改科目         12:新增叶子科目                    
         20:新增分录规则     21:修改分录规则            
         30:资金冻结         31:资金解冻                
         40:分录处理                        
         *********************************************************************************************
 * @author yangying
 * @version
 * @date 2015年8月28日 下午4:01:38
 * @since 
 */
public class Main {
    static AccEntryService service = null;// 分录流水用
    static FreezeAcctService freezeAcctService = null;// 账户冻结，解冻，止入，止出用
    static FreezeAmountService freezeAmountService = null;
    static {
        @SuppressWarnings("resource")
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:AccountContextTest.xml");
        service =  (AccEntryService) context.getBean("accEntryServiceImpl");
        freezeAcctService =  (FreezeAcctService) context.getBean("freezeAcctServiceImpl");
        freezeAmountService =  (FreezeAmountService) context.getBean("freezeAmountServiceImpl");
    }
    /**
     * 程序主入口
     * @第一个参数是功能ID
     * @第二个参数是执行程序时用的id
     * @param args
     * @throws IOException
     * @throws AccBussinessException 
     */
    public static void main(String args[]) throws IOException, AccBussinessException{
        System.out.println("----------Main   Start-----------");
        
        if (args.length<=0) return;

        Main m= new Main();
        if ("1".equals(args[0])) {
            // 开户
            MainSubject ms =  new MainSubject();
            try {
                ms.testAddBusi();
            } catch (BiffException e) {
                System.out.println(e.getMessage());
            }
        }
        if ("2".equals(args[0]) ||
            "3".equals(args[0]) ||
            "4".equals(args[0]) ||
            "5".equals(args[0]) ||
            "6".equals(args[0]) ||
            "7".equals(args[0]) ||
            "8".equals(args[0])
            ) {
            // 1:开户               2:销户               3:账户冻结               4:账户止入               5：账户止出
            // 6:账户解冻           7:账户解除止入       8:账户解除止出                                        
            m.accountProcess(args);
        }
        if ("10".equals(args[0])) {
            // 新增科目 
            MainSubject ms =  new MainSubject();
            ms.testAddSubject();
        }
        if ("11".equals(args[0])) {
            // 修改科目 
            MainSubject ms =  new MainSubject();
            try {
                ms.testUpdateSubject();
            } catch (BiffException e) {
                System.out.println(e.getMessage());
            }
        }
        if ("12".equals(args[0])) {
            // 新增叶子科目 
            MainSubject ms =  new MainSubject();
            try {
                ms.testAddLeafSub();
            } catch (BiffException e) {
                System.out.println(e.getMessage());
            } catch (AbstractAccException e) {
                System.out.println(e.getMessage());
            }
        }
        if ("20".equals(args[0])) {
            // 新增分录规则
            MainSubject ms =  new MainSubject();
            try {
                ms.testAddRule();
            } catch (BiffException e) {
                System.out.println(e.getMessage());
            }
        }
        if ("21".equals(args[0])) {
            // 修改分录规则
            MainSubject ms =  new MainSubject();
            try {
                ms.testUpdateRule();
            } catch (BiffException e) {
                System.out.println(e.getMessage());
            }
        }
        if ("30".equals(args[0])) {
            // 资金冻结
            m.freezeAmount();
        }
        if ("31".equals(args[0])) {
            // 资金解冻
            m.unfreezeAmount(args);
        }
        if ("40".equals(args[0])) {
            // 分录处理
            m.accEntry();
        }
        System.out.println("----------Main   End-----------");
    }
    
    /**
     * 分录处理
     * @throws IOException 
     */
    private void accEntry() throws IOException {
        String proFilePath = System.getProperty("user.dir") + "\\demo\\分录处理\\para.properties";  
        InputStream in = new BufferedInputStream(new FileInputStream(proFilePath));  
        ResourceBundle  RESOURCE = new PropertyResourceBundle(in);
        TradeInfo entry = new TradeInfo();
        entry.setPayMemberId(RESOURCE.getString("payMemberId"));
        entry.setPayToMemberId(RESOURCE.getString("payToMemberId"));
        entry.setPayToParentMemberId(RESOURCE.getString("payToParentMemberId"));
        entry.setTxnseqno(RESOURCE.getString("txnseqno"));
        entry.setBusiCode(RESOURCE.getString("busiCode"));
        entry.setAmount(getAmount(RESOURCE.getString("amount")));
        entry.setCommission(getAmount(RESOURCE.getString("commission")));
        entry.setCharge(getAmount(RESOURCE.getString("charge")));
        entry.setAmountD(getAmount(RESOURCE.getString("amountD")));
        entry.setAmountE(getAmount(RESOURCE.getString("amountE")));
        entry.setChannelId(RESOURCE.getString("channelId"));
        if ("true".equals(RESOURCE.getString("isSplit"))) {
            entry.setSplit(true);
        } else {
            entry.setSplit(false);
        }
        
        try {
            service.accEntryProcess(entry,EntryEvent.TRADE_SUCCESS);
        } catch (AccBussinessException e) {
            System.out.println(e.getMessage());
        } catch (AbstractBusiAcctException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * 账户相关
     * 2:销户               3:账户冻结               4:账户止入               5：账户止出
     * 6:账户解冻           7:账户解除止入       8:账户解除止出                                        
     * @param args
     * @throws AccBussinessException
     */
    private void accountProcess(String args[]) throws AccBussinessException {
        try {
            Account freeze = new Account();
            freeze.setId(Long.parseLong(args[1]));
            if ("2".equals(args[0]))
                freezeAcctService.logOutAcct(freeze);
            if ("3".equals(args[0]))
                freezeAcctService.freezeAcct(freeze);
            if ("4".equals(args[0]))
                freezeAcctService.stopInAcct(freeze);
            if ("5".equals(args[0]))
                freezeAcctService.stopOutAcct(freeze);
            if ("6".equals(args[0]))
                freezeAcctService.unFreezeAcct(freeze);
            if ("7".equals(args[0]))
                freezeAcctService.reopenInAcct(freeze);
            if ("8".equals(args[0]))
                freezeAcctService.reopenOutAcct(freeze);
        } catch (AccBussinessException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * 资金冻结
     */
    private void freezeAmount(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            String proFilePath = System.getProperty("user.dir") + "\\demo\\资金冻结\\para.properties";  
            InputStream in;
            in = new BufferedInputStream(new FileInputStream(proFilePath));
            ResourceBundle  RESOURCE = new PropertyResourceBundle(in);
            AccountAmount aa = new AccountAmount();
            aa.setTxnseqno(RESOURCE.getString("txnseqno"));//冻结订单号
            aa.setAccId(Long.parseLong(RESOURCE.getString("accId")));// 账户号
            aa.setFrozenBalance(Money.valueOf(getAmount(RESOURCE.getString("frozenBalance"))));// 冻结金额
            aa.setFrozenTime(Long.parseLong(RESOURCE.getString("frozenTime")));//冻结时间范围
            if (!StringUtil.isEmpty(RESOURCE.getString("frozenSTime")))
                aa.setFrozenSTime(format.parse(RESOURCE.getString("frozenSTime")));// 冻结开始时间
            else 
                aa.setFrozenSTime(new Date());
            aa.setNotes(RESOURCE.getString("notes"));
            aa.setInuser(Long.parseLong(RESOURCE.getString("inuser")));
            aa.setUpuser(Long.parseLong(RESOURCE.getString("upuser")));
            freezeAmountService.freezeAmount(aa);
        } catch (AccBussinessException e) {
             System.out.println(e.getMessage());
        } catch (FileNotFoundException e1) {
            System.out.println(e1.getMessage());
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        } catch (ParseException e3) {
            System.out.println(e3.getMessage());
            e3.printStackTrace();
        }  
    }
    /**
     * 资金解冻
     * @param args
     */
    private void unfreezeAmount(String args[]){
        AccountAmount aa = new AccountAmount();
        try {
            aa.setId(Long.parseLong(args[1]));
            freezeAmountService.unFreezeAmount(aa,false);
        } catch (AccBussinessException e) {
            System.out.println(e.getMessage());
        }
    }
    private BigDecimal getAmount(String amount) {
        return amount == null || amount.trim().length() == 0
                ? BigDecimal.ZERO
                : new BigDecimal(amount);
    }
}
