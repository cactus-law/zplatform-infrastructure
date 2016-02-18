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
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.member.bean.MemberAccountBean;
import com.zlebank.zplatform.member.bean.MemberBalanceDetailBean;
import com.zlebank.zplatform.member.bean.MemberBean;
import com.zlebank.zplatform.member.bean.enums.MemberType;
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
public interface MemberAccountService {
    /**
     * 查询余额
     * @param memberType
     * @param member
     * @return
     * @throws DataCheckFailedException 
     * @throws GetAccountFailedException 
     */
    public MemberAccountBean queryBalance (MemberType memberType, MemberBean member, Usage usage) throws DataCheckFailedException, GetAccountFailedException ;
    /**
     * 查询收支明细
     * @param memberType
     * @param member
     * @param page
     * @param pageSize
     * @return
     * @throws GetAccountFailedException 
     */
    public PagedResult<MemberBalanceDetailBean> queryBalanceDetail (MemberType memberType, MemberBean member, int page, int pageSize) throws GetAccountFailedException ;
}
