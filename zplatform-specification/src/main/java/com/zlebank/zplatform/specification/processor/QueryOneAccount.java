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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.message.content.Account;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * 账户余额查询(返回一条)
 *
 * @author yangpeng
 * @version
 * @date 2015年9月19日 下午3:27:14
 * @since
 */
@Service
public class QueryOneAccount extends RawAbstractProcessor {
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
        String acctCode = request.getString("acctCode");
        // 取得父级商户
        String pmemId = request.getString("merchNo");
        // 通过业务账户号得到memberId
        String memberId = memberService.getMemberIdByBusiCode(acctCode);
        // 如果得不到 那么错误
        if (StringUtil.isEmpty(memberId)) {
            res.setResCode(FAIL_CODE);
            res.setResDes("通过业务账户号没找到相应的会员号。");
            return res;
        }
        //如果父级账户号不等于自身
        if (!pmemId.equals(memberId)) {
            // 通过memberId得到映射关系
            PojoMemberEntranceLink memberLink = memberLinkDao
                    .getByMemberId(memberId);
            if (memberLink == null) {
                res.setResCode(FAIL_CODE);
                res.setResDes("会员所属关联信息为空");
                return res;
            }
            String pId = memberLink.getBelongMemberId();
            // 如果
            if (!pmemId.equals(pId)) {
                res.setResCode(FAIL_CODE);
                res.setResDes("一级商户号和该会员号不匹配");
                return res;
            }
       
        }

        request.getString("mac");
        BusiAcctQuery busi = memberService.getBusiQueryBybCode(acctCode);
        if (busi == null) {
            res.setResCode(FAIL_CODE);
            res.setResDes("根据业务账户号没有取到相应的账户信息。");
        } else {
            Account acc = new Account();
            acc.setAcctCode(busi.getBusiAcctCode());
            acc.setAcctName(busi.getBusiAcctName());
            acc.setBalance(busi.getBalance().toYuan());
            acc.setFrozenBalance(busi.getFronzenBalance().toYuan());
            acc.setTotalBalance(busi.getTotalBalance().toYuan());
            acc.setUsage(busi.getUsage().getCode());
            acc.setStatus(busi.getAcctType().getCode());
            acc.setCurrency(busi.getTotalBalance().getCurrency()
                    .getCurrencyCode());
            res.setResCode(SUCCESS_CODE);
            res.setResDes(SUCCESS_DES);
            res.setObject("account", acc);
        }

        return res;
    }

}
