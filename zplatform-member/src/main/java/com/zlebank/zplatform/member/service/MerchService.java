/* 
 * MenchService.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.member.bean.MerchantBean;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;

/**
 * Class Description
 * 商户service
 * @author yangpeng
 * @version
 * @date 2015年9月11日 上午9:32:49
 * @since 
 */
public interface MerchService {
            /**
             * 二级商户开户
             * @param mb
             * @return
             */
    public String createMinorMerchant(MerchantBean mb,Long userId) throws MemberBussinessException, AbstractBusiAcctException ;

    /**
     * 根据父级ID得到父级商户
     * @param merchId
     * @return
     */
    public PojoMerchDeta getParentMerch(String memberId);
    /**
     * 通过税务登记号得到商户
     * @param taxno
     * @return PojoMerchDeta
     */
    public PojoMerchDeta getMerchByTaxno(String taxno);
       /**
        * 通过营业执照得到商户
        * @param licenceNo
        * @return
        */
    public PojoMerchDeta getMerchByLicenceNo(String licenceNo);
        /**
         * 通过email得到商户
         * @param email
         * @return
         */
    public PojoMerchDeta getMerchByEmail(String email);
        /**
         * 通过电话号码得到商户
         * @param phone
         * @return
         */
    public PojoMerchDeta getMerchByPhone(String phone);
    
    /**
     * 根据memberId得到商户
     * @param memberId
     * @return
     */
   public  PojoMerchDeta  getMerchBymemberId(String memberId);
}
