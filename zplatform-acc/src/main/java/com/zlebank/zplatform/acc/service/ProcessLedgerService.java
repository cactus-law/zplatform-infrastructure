/* 
 * ProcessLedgerService.java  
 * 
 * version v1.0
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoAccount;

/**
 * 总账处理
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月27日 下午5:56:34
 * @since 
 */
public interface ProcessLedgerService {

    /**
     * @接受类型PojoAccount
     * @必须的值：parentSubject
     * @可选的值：balance，frozenBalance，creditBalance，debitBalance，totalBanance（默认为零）（如果想执行减法操作，请传入负数）
     * @金额增加的例子：PojoAccount.setFrozenBalance(taskPojo.getFrozenBalance());
     * @金额减少的例子：PojoAccount.setFrozenBalance(taskPojo.getFrozenBalance().negate());【用negate()方法可将正数转为负数】
     * @param account
     * @throws Exception
     */
    public void processLedger(PojoAccount account) throws AccBussinessException ;

}
