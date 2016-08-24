/* 
 * MemberAccountService.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.member.bean.FinanceProductAccountBean;
import com.zlebank.zplatform.member.bean.FinanceProductQueryBean;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.GetAccountFailedException;

/**
 * 会员账户相关服务
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午2:51:54
 * @since 
 */
public interface FinanceProductAccountService {
    /**
     * 查询余额
     * @param memberType
     * @param member
     * @return
     * @throws DataCheckFailedException 
     * @throws GetAccountFailedException 
     */
    public FinanceProductAccountBean queryBalance (FinanceProductQueryBean bean, Usage usage) throws DataCheckFailedException, GetAccountFailedException ;
    /**
     * 查询收支明细
     * @param memberType
     * @param member
     * @param page
     * @param pageSize
     * @return
     * @throws GetAccountFailedException 
     */
    //public PagedResult<MemberBalanceDetailBean> queryBalanceDetail (MemberType memberType, MemberBean member, int page, int pageSize) throws GetAccountFailedException ;
}
