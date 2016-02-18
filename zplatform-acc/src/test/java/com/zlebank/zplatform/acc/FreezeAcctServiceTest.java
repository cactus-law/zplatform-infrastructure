/* 
 * FreezeAcctServiceTest.java  
 * 
 * version TODO
 *
 * 2015年8月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import static org.junit.Assert.assertEquals;

import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.service.FreezeAcctService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月21日 下午2:57:18
 * @since 
 */
public class FreezeAcctServiceTest {
    static ApplicationContext context = null;
    static FreezeAcctService service = null;
    static {
        context = ApplicationContextUtil.get();
        service =  (FreezeAcctService) context.getBean("freezeAcctServiceImpl");
    }
     
    public void freezeAcctTest(){
        // 正常冻结
        Account freeze = new Account();
        freeze.setId(10003L);
        boolean success=true;try {service.freezeAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：输入的账户不存在
        freeze = new Account();
        freeze.setId(99999L);
        success=true;try {service.freezeAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }
    
     
    public void unFreezeAcctTest(){
        // 正常解冻
        Account freeze = new Account();
        freeze.setId(10003L);
        boolean success=true;try {service.unFreezeAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：输入的账户不存在
        freeze = new Account();
        freeze.setId(99999L);
        success=true;try {service.unFreezeAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }
     
    public void stopInAcctTest(){
        // 止入
        Account freeze = new Account();
        freeze.setId(10003L);
        boolean success=true;try {service.stopInAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：输入的账户不存在
        freeze = new Account();
        freeze.setId(99999L);
        success=true;try {service.stopInAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }
    
    public void reopenInAcctTest(){
        // 解除止入
        Account freeze = new Account();
        freeze.setId(10003L);
        boolean success=true;try {service.reopenInAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：输入的账户不存在
        freeze = new Account();
        freeze.setId(99999L);
        success=true;try {service.reopenInAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }
     
    public void stopOutAcctTest(){
        // 止出
        Account freeze = new Account();
        freeze.setId(10003L);
        boolean success=true;try {service.stopOutAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：输入的账户不存在
        freeze = new Account();
        freeze.setId(99999L);
        success=true;try {service.stopOutAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }
     
    public void reopenOutAcctTest(){
        // 解除止出
        Account freeze = new Account();
        freeze.setId(10003L);
        boolean success=true;try {service.reopenOutAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：输入的账户不存在
        freeze = new Account();
        freeze.setId(99999L);
        success=true;try {service.reopenOutAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }
    
     
    public void logOutAcctTest(){
        // 销户
        Account freeze = new Account();
        freeze.setId(10006L);
        boolean success=true;try {service.logOutAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(true,success);

        // 异常：销户的账户id不存在
        freeze = new Account();
        freeze.setId(30003L);
        success=true;try {service.logOutAcct(freeze);}catch (Exception e) {e.printStackTrace();success=false;}
        assertEquals(false,success);
    }

    static {
//      PojoAccount pojo = new PojoAccount();
//      pojo.setAcctCode("0009");
//      pojo.setAcctType("00");
//      pojo.setCrdr("1");
//      pojo.setAcctElement("01");
//      pojo.setBalance(100L);
//      pojo.setFrozenBalance(0L);
//      pojo.setCreditBalance(0L);
//      pojo.setDebitBalance(0L);
//      pojo.setTotalBanance(0L);
//      pojo.setParentAcctId(1L);
//      pojo.setStatus("00");
//      pojo.setInTime(new Date());
//      pojo.setInUser(45L);
//      accountDAO.save(pojo);
    }
}
