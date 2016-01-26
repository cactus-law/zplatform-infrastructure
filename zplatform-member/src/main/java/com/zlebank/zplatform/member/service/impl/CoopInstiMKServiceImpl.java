/* 
 * CoopInstiMKServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年1月25日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.Base64Utils;
import com.zlebank.zplatform.commons.utils.RSAUtils;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.CoopInstiMK;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.service.CoopInstiMKService;
import com.zlebank.zplatform.member.service.CoopInstiService;

/**
 * 合作机构密钥服务类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月25日 上午10:08:24
 * @since 
 */
@Service
public class CoopInstiMKServiceImpl implements CoopInstiMKService {
    
    private final static Log log = LogFactory.getLog(CoopInstiMKServiceImpl.class);

    @Autowired
    private CoopInstiService coopInstiService;;
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
    @Override
    public boolean verify(String instiCode,
            String signedData,
            String originData,
            TerminalAccessType terminalAccessType) {
        if (log.isDebugEnabled()) {
            log.debug("【参数1】"+instiCode);
            log.debug("【参数2】"+originData);
            log.debug("【参数3】"+terminalAccessType);
        }
        CoopInstiMK coopInstiMK = coopInstiService.getCoopInstiMK(instiCode, terminalAccessType);
        if (coopInstiMK == null) {
            log.error("校验加签数据错误，此商户的秘钥对不存在。");
            return false;
        }

        if (log.isDebugEnabled()) {
            log.info("原数据=" + originData + ", 被加签的数据=" + signedData);
        }
        boolean isOK = false;

        try {
            isOK = RSAUtils.verify(originData.getBytes(), coopInstiMK.getInstiPubKey().trim(), signedData);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return isOK;
    }

    /**
     * 私钥加签
     * 
     * 根据合作机构号进行加签【使用平台私钥】【T_COOP_INSTI_MK】
     * @param memberId 合作机构号
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return
     */
    @Override
    public String sign(String instiCode,
            String originData,
            TerminalAccessType terminalAccessType) {
        if (log.isDebugEnabled()) {
            log.debug("【参数1】"+instiCode);
            log.debug("【参数2】"+originData);
            log.debug("【参数3】"+terminalAccessType);
        }
        CoopInstiMK coopInstiMK = coopInstiService.getCoopInstiMK(instiCode, terminalAccessType);
        if (coopInstiMK == null) {
            log.error("校验加签数据错误，此商户的秘钥对不存在。");
            return null;
        }
        if (log.isDebugEnabled()) {
            log.info("原数据= " + originData);
        }
        try {
            return RSAUtils.sign(originData.getBytes(), coopInstiMK.getZplatformPriKey().trim());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 私钥解密
     * 
     * 根据合作机构号进行私钥加密【使用平台私钥】【T_COOP_INSTI_MK】
     * originData->Base64解码->平台私钥解密(String)
     * 
     * @param memberId 合作机构号
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return String 【UTF-8】
     */
    @Override
    public String decryptByPrivateKey(String instiCode,
            String originData,
            TerminalAccessType terminalAccessType) {
        if (log.isDebugEnabled()) {
            log.debug("【参数1】"+instiCode);
            log.debug("【参数2】"+originData);
            log.debug("【参数3】"+terminalAccessType);
        }
        try {
            CoopInstiMK coopInstiMK = coopInstiService.getCoopInstiMK(instiCode, terminalAccessType);
            if (coopInstiMK == null) {
                log.error("解密数据错误，此合作机构的秘钥对不存在。");
                return null;
            }
            if (log.isDebugEnabled()) {
                log.info("原数据= " + originData);
            }
            // 报文解码（Base64）
            byte[] decodeData = Base64Utils.decode(originData);
            // 报文解密（平台私钥解密）
            byte[] decodedData = RSAUtils.decryptByPrivateKey(decodeData, coopInstiMK.getZplatformPriKey());
            return StringUtil.getUTF8(decodedData);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 公钥加密
     * 
     * 根据合作机构号进行私钥加密【使用合作机构公钥】【T_COOP_INSTI_MK】
     * originData->公钥加密->Base64转码
     * @param memberId 合作机构号
     * @param originData 原数据
     * @param terminalAccessType 接入类型
     * @return String 【Base64编码】
     */
    @Override
    public String encryptByPublicKey(String instiCode,
            String originData,
            TerminalAccessType terminalAccessType) {
        if (log.isDebugEnabled()) {
            log.debug("【参数1】"+instiCode);
            log.debug("【参数2】"+originData);
            log.debug("【参数3】"+terminalAccessType);
        }
        // 商户公钥加密
        try {
            CoopInstiMK coopInstiMK = coopInstiService.getCoopInstiMK(instiCode, terminalAccessType);
            byte[] decodedData = RSAUtils.encryptByPublicKey(originData.getBytes(), coopInstiMK.getInstiPubKey());
            return Base64Utils.encode(decodedData);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
}
