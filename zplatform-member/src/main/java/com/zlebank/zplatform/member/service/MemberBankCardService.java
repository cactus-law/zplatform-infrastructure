/* 
 * MemberBankCardService.java  
 * 
 * version TODO
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.member.bean.QuickpayCustBean;
import com.zlebank.zplatform.member.bean.RealNameBean;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.UnbindBankFailedException;

/**
 * 会员银行卡相关服务
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午2:50:27
 * @since 
 */
public interface MemberBankCardService {
    /**
     * 保存实名认证信息
     * @param bean
     */
    public void saveRealNameInfo(RealNameBean bean);

    /**
     * 查询实名认证信息【根据会员ID】
     * @param bean
     */
    public RealNameBean queryRealNameInfo(RealNameBean bean);
    
    /**
     * 保存银行卡绑卡信息
     * @param bean
     * @ Return long 绑卡ID
     */
    public long saveQuickPayCust(QuickpayCustBean bean);
    
    /**
     * 银行卡解绑
     * @param bean
     * @throws DataCheckFailedException 
     * @throws UnbindBankFailedException 
     */
    public void unbindQuickPayCust(QuickpayCustBean bean) throws DataCheckFailedException, UnbindBankFailedException;
    
    /**
     * 查询签约银行卡信息（会员）
     * @param memberId 会员号
     * @param cardType 卡类型 
     *          0：借记卡+贷记卡
     *          1：借记卡
     *          2：贷记卡
     * @return
     */
    public PagedResult<QuickpayCustBean> queryMemberBankCard(String memberId, String cardType, String devId,int offset,  int pageSize);
    
    /**
     * 保存银行卡绑卡信息
     * @param bean
     * @ Return long 绑卡ID
     */
    public long saveQuickPayCustExt(QuickpayCustBean bean);
}
