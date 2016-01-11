/* 
 * MemberAccountQuery.java  
 * 
 * version TODO
 *
 * 2015年9月19日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.message.content.Account;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * 会员账户查询(返回N条)
 *
 * @author yangpeng
 * @version
 * @date 2015年9月19日 下午3:27:14
 * @since
 */
@Service
public class QueryMemberAccount extends RawAbstractProcessor {
    private final static Log log = LogFactory
            .getLog(QueryMemberAccount.class);
    private static final String SUCCESS_CODE = "00";
    private static final String SUCCESS_DES = "查询成功";
    private static final String FAIL_CODE = "30";

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberEntranceLinkDAO memberLinkDao;

    /**
     *
     * @param request
     * @return
     */
    @Override
    protected Response realProcess(Message request) {
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId"));
        request.getString("memberType");
        // 一级商户memid
        String merchNo = request.getString("merchNo");
        //需要查询的memid
        String memberNo = request.getString("memberId");
        // 子商户的信息
        PojoMemberEntranceLink memberLink = memberLinkDao
                .getByMemberId(memberNo);
        String pId = "";
        // 如果子商户不存在
        if (memberLink != null) {
            pId = memberLink.getBelongMemberId();
        }
        if (!merchNo.equals(pId) && !merchNo.equals(memberNo)) {
            if (log.isDebugEnabled()) {
                log.debug("查不到相应的会员信息。使用的查询条件（一级商户："+merchNo+"会员号："+memberNo+"）");
            }
            res.setResCode(FAIL_CODE);
            res.setResDes("查不到相应的会员信息。（一级商户："+merchNo+"会员号："+memberNo+"）");
            return res;
        }
        request.getString("mac");
        List<BusiAcctQuery> busiacc = memberService.getAllBusiByMId(memberNo);
        if (busiacc.isEmpty()) {
            res.setResCode(FAIL_CODE);
            res.setResDes("根据会员号没有找到相应的账户信息（会员号："+memberNo+"）");
        } else {
            List<Account> accounts = new ArrayList<Account>();
            for (BusiAcctQuery bus : busiacc) {
                Account acc = new Account();
                acc.setAcctCode(bus.getBusiAcctCode());
                acc.setAcctName(bus.getBusiAcctName());
                acc.setBalance(bus.getBalance().toYuan());
                acc.setFrozenBalance(bus.getFronzenBalance().toYuan());
                acc.setTotalBalance(bus.getTotalBalance().toYuan());
                acc.setUsage(bus.getUsage().getCode());
                acc.setStatus(bus.getAcctType().getCode());
                acc.setCurrency(bus.getTotalBalance().getCurrency()
                        .getCurrencyCode());
                accounts.add(acc);
            }
            res.setResCode(SUCCESS_CODE);
            res.setResDes(SUCCESS_DES);
            res.setObject("accounts", accounts);
        }

        return res;
    }

}
