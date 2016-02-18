/* 
 * CoopInstiMKService.java  
 * 
 * version TODO
 *
 * 2016年1月25日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;

/**
 * 合作机构密钥服务类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月25日 上午10:07:02
 * @since 
 */
public interface CoopInstiMKService {
    /**
     * 公钥验签
     * 
     * 根据合作机构号进行验签【使用合作机构公钥】【T_COOP_INSTI_MK】
     * @param instiCode 合作机构号【T_COOP_INSTI_MK】
     * @param signedData 被加签的数据
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return boolean 验签是否成功
     */
    boolean verify(String instiCode, String signedData, String originData,TerminalAccessType terminalAccessType) ;
    
    /**
     * 私钥加签
     * 
     * 根据合作机构号进行加签【使用平台私钥】【T_COOP_INSTI_MK】
     * @param memberId 合作机构号
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return
     */
    String sign(String instiCode, String originData,TerminalAccessType terminalAccessType);
    
    /**
     * 私钥解密
     * 
     * 根据合作机构号进行私钥加密【使用平台私钥】【T_COOP_INSTI_MK】
     * originData->Base64解码->平台私钥解密
     * 
     * @param memberId 合作机构号
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return
     */
    String decryptByPrivateKey(String instiCode, String originData,TerminalAccessType terminalAccessType);
    
    /**
     * 公钥加密
     * 
     * 根据合作机构号进行私钥加密【使用合作机构公钥】【T_COOP_INSTI_MK】
     * originData->公钥加密->Base64转码
     * @param memberId 合作机构号
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return
     */
    String encryptByPublicKey(String instiCode, String originData,TerminalAccessType terminalAccessType);
}
