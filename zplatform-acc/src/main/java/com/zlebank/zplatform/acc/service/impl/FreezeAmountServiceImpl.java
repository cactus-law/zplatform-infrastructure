/* 
 * FreezeAmountServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.bean.AccountAmountQuery;
import com.zlebank.zplatform.acc.bean.enums.FrozenStatusType;
import com.zlebank.zplatform.acc.bean.enums.LockStatusType;
import com.zlebank.zplatform.acc.dao.AbstractSubjectDAO;
import com.zlebank.zplatform.acc.dao.AccFrozenTaskDAO;
import com.zlebank.zplatform.acc.dao.AccountDAO;
import com.zlebank.zplatform.acc.dao.SubjectDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoAccFrozenTask;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.FreezeAmountService;
import com.zlebank.zplatform.acc.service.ProcessLedgerService;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月25日 下午7:06:16
 * @since
 */
@Service
public class FreezeAmountServiceImpl extends AbstractBasePageService<AccountAmountQuery,AccountAmount> implements FreezeAmountService {

    @Autowired
    private AccFrozenTaskDAO accFrozenTaskDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private AbstractSubjectDAO abstractSubjectDAO;
    
    @Autowired
    private ProcessLedgerService processLedgerService;

    @Autowired
    private AccountService accountService;

    /**
     *冻结指定账户的指定金额（有可能是订单）
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public AccountAmount freezeAmount(AccountAmount account) throws AccBussinessException {
        // 锁相应的记录（如果存在的话）for update
        PojoAccount record = accountDAO.getByIdForUpdate(account.getAccId());
        if (record == null)
            throw new AccBussinessException("EASAC0002", new Object[]{String.valueOf(account.getAccId())});
        //验证DAC
        accountService.checkDAC(record);
        // 冻结金额是否合法(是否是正数)
        if (!account.getFrozenBalance().equalsPositiveNumber()) {
            throw new AccBussinessException("EASAC0004", new Object[]{account.getFrozenBalance().getAmount().toPlainString()});
        }
        // 冻结金额是否比可用余额大
        if (account.getFrozenBalance().compareTo(record.getBalance())>0) {
            throw new AccBussinessException("EASAC0005", new Object[]{account.getFrozenBalance().getAmount().toPlainString(), record.getBalance().getAmount().toPlainString()});
        }
        // 冻结时间是否合法
        if (account.getFrozenTime()==null || account.getFrozenTime()<=0) {
            throw new AccBussinessException("E000008", new Object[]{account.getFrozenBalance().getAmount().toPlainString(), record.getBalance().getAmount().toPlainString()});
        }
        

        // 当前日期
        Date currentDate = new Date();
        PojoAccFrozenTask task = new PojoAccFrozenTask();
        task.setAccId(record);//账户id
        task.setAcctCode(record.getAcctCode());//账户代码
        task.setFrozenBalance(account.getFrozenBalance());//冻结金额
        task.setFrozenSTime(account.getFrozenSTime() == null
                ? currentDate
                : account.getFrozenSTime());// 冻结开始时间（当前系统时间或传入时间）
        task.setFrozenTime(account.getFrozenTime());//冻结时间范围
        task.setTxnseqno(account.getTxnseqno());//交易流水号
        task.setUnfrozenTime(DateUtil.addMin(currentDate, account.getFrozenTime()));// 解冻时间
        task.setIntime(currentDate);
        task.setUptime(currentDate);
        task.setInuser(account.getInuser());
        task.setUpuser(account.getUpuser());
        task.setNotes(account.getNotes());
        task.setStatus(FrozenStatusType.FREEZE);
        task.setIsLock(LockStatusType.UNLOCK);
        // 保存冻结数据
        accFrozenTaskDAO.merge(task);
        // 更新账户数据
        record.setBalance(record.getBalance().minus(task.getFrozenBalance()));//更新可用余额=原可用余额-冻结金额
        record.setFrozenBalance(record.getFrozenBalance().plus(task.getFrozenBalance()));//更新冻结余额=原冻结余额+冻结金额
        accountDAO.update(record);
        // 更新总账
        PojoAccount total = new PojoAccount();
        total.setParentSubject(record.getParentSubject());
        total.setFrozenBalance(account.getFrozenBalance());
        total.setBalance(account.getFrozenBalance().negate());
        total.setTotalBanance(Money.valueOf(BigDecimal.ZERO));
        processLedgerService.processLedger(total);
        return account;
    }

    /**
     * 解冻指定的冻结ID
     * @param account
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public AccountAmount unFreezeAmount(AccountAmount account, boolean isBatch) throws AccBussinessException {
        //  锁相应的冻结明细记录（如果存在的话）for update
        PojoAccFrozenTask taskPojo = accFrozenTaskDAO.get(account.getId());
        if (taskPojo == null) 
            throw new AccBussinessException("E000006", new Object[]{String.valueOf(account.getId())});
        if (taskPojo.getStatus() == FrozenStatusType.NORMAL) 
            throw new AccBussinessException("E000007", new Object[]{String.valueOf(account.getId())});
        // 锁相应的账户记录（如果存在的话）for update
        PojoAccount record = accountDAO.getByIdForUpdate(taskPojo.getAccId().getId());
        //验证DAC
        accountService.checkDAC(record);
        if (record == null)
            throw new AccBussinessException("EASAC0002", new Object[]{String.valueOf(account.getAccId())});
        // 更新冻结明细记录的状态
        taskPojo.setStatus(FrozenStatusType.NORMAL);
        if (isBatch) {
            taskPojo.setIsLock(LockStatusType.UNLOCK);
        }
        // 更新账户的可用余额，冻结余额
        record.setBalance(record.getBalance().plus(taskPojo.getFrozenBalance()));//更新可用余额=原可用余额-冻结金额
        record.setFrozenBalance(record.getFrozenBalance().minus(taskPojo.getFrozenBalance()));//更新冻结余额=原冻结余额+冻结金额
        // 更新账户
        accountDAO.update(record);
        // 更新冻结明细
        accFrozenTaskDAO.update(taskPojo);
        // 更新总账
        PojoAccount total = new PojoAccount();
        total.setParentSubject(record.getParentSubject());
        total.setFrozenBalance(taskPojo.getFrozenBalance().negate());
        total.setBalance(taskPojo.getFrozenBalance());
        total.setTotalBanance(Money.valueOf(BigDecimal.ZERO));
        processLedgerService.processLedger(total);
        return account;
    }

    /**
     * 批处理解冻【预处理】
     * @param fetchSize
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<Long> unFreezeAmountBatchPre(int fetchSize)  throws AccBussinessException {
        List<Long> frozenRecords = new ArrayList<Long>();
        List<PojoAccFrozenTask> records = accFrozenTaskDAO.getLockRecords(fetchSize);
        for (PojoAccFrozenTask record : records) {
            accFrozenTaskDAO.getByIdForUpdate(fetchSize);
            record.setIsLock(LockStatusType.LOCK);
            accFrozenTaskDAO.update(record);
            frozenRecords.add(record.getId());
        }
        return frozenRecords;
    }

    /**
     *批处理解冻【执行】
     * @param accountAmount
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void unFreezeAmountBatch(List<Long> accountAmount)  throws AccBussinessException {
        AccountAmount para = new AccountAmount();
        for (Long id : accountAmount) {
            para.setId(id);
            unFreezeAmount(para,true);
        }
    }



    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(AccountAmountQuery example) {
        return    accFrozenTaskDAO.count(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<AccountAmount> getItem(int offset,
            int pageSize,
            AccountAmountQuery example) {
        List<PojoAccFrozenTask> li= accFrozenTaskDAO.getListByQuery(offset, pageSize, example);
        List<AccountAmount> accountAm=new ArrayList<AccountAmount>();
        for(PojoAccFrozenTask aft:li){
           AccountAmount accounta= BeanCopyUtil.copyBean(AccountAmount.class, aft);
           accounta.setId(aft.getId());
           accounta.setAccId(aft.getAccId().getId());
           accountAm.add(accounta);
        }
        return accountAm;
    }

    /**
     *
     * @param Id
     * @return
     */
    @Override
    public AccountAmount getAccountByID(Long id) {
     PojoAccFrozenTask paft=   accFrozenTaskDAO.getAccountByID(id);
     if(paft!=null)
         return BeanCopyUtil.copyBean(AccountAmount.class, paft);
         return null;
    
     
    }


}
