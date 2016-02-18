/* 
 * FreezeAmountService.java  
 * 
 * version v1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service;

import java.util.List;

import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.bean.AccountAmountQuery;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.service.IBasePageService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午7:06:45
 * @since 
 */
public interface FreezeAmountService  extends IBasePageService<AccountAmountQuery,AccountAmount>{

    /**
     * 冻结指定账户的指定金额（有可能是订单）
     * @AccountAmount必需的值包括：accId，frozenTime，frozenBalance，inuser，upuser
     * @AccountAmount可选的值包括：txnseqno（不填默认为空），frozenSTime（不填默认为系统时间），notes（不填默认为空）
     * @param account
     * @return
     */
    public AccountAmount freezeAmount(AccountAmount account)  throws AccBussinessException;
    /**
     * 解冻指定的冻结ID
     * @AccountAmount必需的值包括：id
     * @param account
     * @param isBatch（是不是批处理操作）
     * @return
     */
    public AccountAmount unFreezeAmount(AccountAmount account, boolean isBatch)  throws AccBussinessException;
    /**
     * 批处理解冻【预处理】
     * @ 注意：如果预计处理成功而执行失败的话，数据库（T_ACC_FROZEN_TASK）的的数据将会被锁定，此时需要人工去解锁。
     * @param fetchSize 每次批处理从数据库里取的条数
     * @return
     */
    public List<Long> unFreezeAmountBatchPre(int fetchSize)  throws AccBussinessException;
    /**
     * 批处理解冻【执行】
     * @param accountAmount 【预处理】中返回的值
     * @return
     */
    public void unFreezeAmountBatch(List<Long> accountAmount)  throws AccBussinessException;

    /**
     * 通过Id得到冻结记录
     * @param Id
     * @return
     */
    public AccountAmount getAccountByID(Long id);


}
