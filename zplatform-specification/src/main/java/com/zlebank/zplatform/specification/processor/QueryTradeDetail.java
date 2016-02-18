/* 
 * QueryTradeInfoDetail.java  
 * 
 * version v1.0
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import java.text.ParseException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.dao.MerchDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.message.content.EntryDetail;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * 账户收支明细查询
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月19日 下午3:18:59
 * @since 
 */
@Service
public class QueryTradeDetail  extends RawAbstractProcessor {
    private final static Log log = LogFactory.getLog(QueryTradeDetail.class);
    private static final String SUCCESS_CODE = "00";
    private static final String SUCCESS_DES = "账户收支明细查询成功";
    private static final String FAIL_CODE = "30";
    private static final String FAIL_INSIDE_CODE = "40";
    private static final String FAIL_DES = "账户收支明细查询失败：";
    private static final String TIME_START="000000";
    private static final String TIME_END="235959";
    
    @Autowired
    private MemberService memberService;
    @Autowired
    private MerchDAO merchDAO;
    @Autowired
    private BusiAcctDAO busiAcctDAO;
    @Autowired
    private MemberEntranceLinkDAO memberEntranceLinkDAO;
    /**
     * 账户收支明细查询
     * @param request
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    protected Response realProcess(Message request) {
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId")); 
        ArrayList<EntryDetail> list = new ArrayList<EntryDetail>();
        
        // 取得一级商户
        String merchNo = request.getString("merchNo");
        // 取得业务账户
        String busiCode = request.getString("acctCode");
        // 取得业务账户对应的memberId
        String memberId = memberService.getMemberIdByBusiCode(busiCode);
        // 取得父级memberId
        String parentMemberId ="";
        PojoMemberEntranceLink linkPojo =  memberEntranceLinkDAO.getByMemberId(memberId);
        if (linkPojo!=null) {
            parentMemberId = linkPojo.getBelongMemberId();
        }
        if (!merchNo.equals(memberId)
                && !merchNo.equals(parentMemberId)) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes("此业务账户不属于该商户");
            return res;
        }

        // 根据条件得到收支明细
        MemberQuery mq = new MemberQuery();
        try {
            mq.setStartTime(DateUtil.convertToDate(request.getString("from")+TIME_START, "yyyy-MM-ddhhmmss"));
            mq.setEndTime(DateUtil.convertToDate(request.getString("to")+TIME_END, "yyyy-MM-ddhhmmss"));
        } catch (ParseException e1) {
            e1.printStackTrace();
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes("日期格式不符合");
            return res;
        }
        // 业务账户
        mq.setAcctCode(request.getString("acctCode"));
        try {
            PagedResult<AccEntry> details =  memberService.getAccEntryByQuery(1, Integer.MAX_VALUE, mq);

            // 结果设定
            for (AccEntry entry : details.getPagedResult()) {
                EntryDetail ed = new  EntryDetail();
                ed.setSeqNo(String.valueOf(entry.getVoucherCode()));// 流水号
                ed.setAcctCode(entry.getAcctCode());// 账户号
                ed.setFlag(entry.getCrdr().getCode());// 收支标记
                ed.setAmount(entry.getAmount().toYuan());// 金额
                ed.setCurrency(entry.getAmount().getCurrency().getCurrencyCode());// 货币代码
                list.add(ed);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            // 返回报文【应答码】
            res.setResCode(FAIL_INSIDE_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES);
            return res;
        }catch (MemberBussinessException e) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES+e.getMessage());
            return res;
        } catch (Exception e) {
            log.error(e.getMessage(),e); 
            // 返回报文【应答码】
            res.setResCode(FAIL_INSIDE_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES);
            return res;
        }

        // 返回报文【应答码】
        res.setResCode(SUCCESS_CODE);
        // 返回报文【应答码描述】
        res.setResDes(SUCCESS_DES);
        // 返回报文【收支明细集合】
        res.setObject("entryDetails", list);
        return res;
    }

}
