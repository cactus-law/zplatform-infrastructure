package com.zlebank.zplatform.acc.service;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.exception.AccBussinessException;

/**
 * 
 * 账户冻结
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月21日 上午11:49:06
 * @since
 */
public interface FreezeAcctService {
    /**
     * 冻结账户
     * @Account必需的值包括：账户的id
     * @return
     */
    public void freezeAcct(Account para) throws AccBussinessException;
    /**
     * 解冻账户
     * @Account必需的值包括：账户的id
     * @param para
     * @return
     */
    public void unFreezeAcct(Account para) throws AccBussinessException;
    /**
     * 止入账户
     * @Account必需的值包括：账户的id
     * @param para
     * @return
     */
    public void stopInAcct(Account para) throws AccBussinessException;
    /**
     * 解除止入账户
     * @Account必需的值包括：账户的id
     * @param para
     * @return
     */
    public void reopenInAcct(Account para) throws AccBussinessException;
    /**
     * 止出账户
     * @Account必需的值包括：账户的id
     * @param para
     * @return
     */
    public void stopOutAcct(Account para) throws AccBussinessException;
    /**
     * 解除止出账户
     * @Account必需的值包括：账户的id
     * @param para
     * @return
     */
    public void reopenOutAcct(Account para) throws AccBussinessException;
    /**注销账户
     * @Account必需的值包括：账户的id
     * @param freeze
     * @return
     */
    public void logOutAcct(Account freeze) throws AccBussinessException;
    /**
     * 检查状态
     */
    public boolean  getStatus(Account para)  throws AccBussinessException;
    
}
