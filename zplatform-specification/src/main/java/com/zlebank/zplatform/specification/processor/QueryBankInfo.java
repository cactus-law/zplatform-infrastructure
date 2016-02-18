/* 
 * QueryBankInfo.java  
 * 
 * version v1.0
 *
 * 2015年9月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.BankInfoDAO;
import com.zlebank.zplatform.commons.dao.pojo.PojoBankInfo;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.message.content.BankInfo;

/**
 * 查询银行行号信息
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月21日 下午4:01:21
 * @since 
 */
@Service
public class QueryBankInfo  extends RawAbstractProcessor{
    private static final String SUCCESS_CODE = "00";
    private static final String SUCCESS_DES = "查询银行行号查询成功";
    private static final String FAIL_CODE = "30";
    private static final String FAIL_DES = "返回结果条数太多，请输入具体支行名称。";
    
    @Autowired
    private BankInfoDAO bankInfoDAO;
    /**
     * 查询银行行号信息
     * @param request
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Response realProcess(Message request) {
        // 取得配置文件（返回最大的记录数）
        ResourceBundle  RESOURCE = ResourceBundle.getBundle("specification");
        // 可返回的最大的记录数
        int maxSize = Integer.parseInt(RESOURCE.getString("bank.query.max"));
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId"));
        List<BankInfo> banks = new ArrayList<BankInfo> ();
        
        // 传入查询条件
        String paraBankName =request.getString("bankName"); 
        long recordSize = bankInfoDAO.getByBankNameCount(paraBankName);
        if (recordSize>maxSize) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES+"（结果允许最大记录数："+maxSize+"条）");
            return res;
        } 
        List<PojoBankInfo> bankInfos = bankInfoDAO.getByBankName(paraBankName, maxSize);
        for (PojoBankInfo bank : bankInfos) {
            BankInfo info = new BankInfo();
            info.setBankNode(bank.getBankNode()); // 开户行号
            info.setAcctName(bank.getBankCode());// 清算行号
            info.setBankName(bank.getBankName());// 开户行名称
            info.setBankAdress(bank.getBankAddress());// 开户行地址
            banks.add(info);
        }
        // 返回报文【应答码】
        res.setResCode(SUCCESS_CODE);
        // 返回报文【应答码描述】
        res.setResDes(SUCCESS_DES);
        // 返回报文【收支明细集合】
        res.setObject("banks", banks);
        return res;
    }

}
