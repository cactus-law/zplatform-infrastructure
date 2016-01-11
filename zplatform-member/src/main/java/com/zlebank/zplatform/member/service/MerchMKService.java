/* 
 * MerchMKService.java  
 * 
 * version 1.0
 *
 * 2015年9月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.MerchMK;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月17日 上午9:40:12
 * @since
 */
public interface MerchMKService {
    /**
     * 
     * @param memberId
     * @return
     */
    MerchMK get(String memberId);
    
    /**
     * 根据memberId进行验签【使用商户公钥】
     * @param memberId 会员号【T_MERCH_MK】
     * @param signedData 被加签的数据
     * @param originData 原数据
     * @return boolean 验签是否成功
     */
    boolean verify(String memberId, String signedData, String originData) ;
    
    /**
     * 根据memberId进行加签【使用收银台私钥】
     * @param memberId 会员号【T_MERCH_MK】
     * @param originData 原数据
     * @return
     */
    String sign(String memberId, String originData);
}
