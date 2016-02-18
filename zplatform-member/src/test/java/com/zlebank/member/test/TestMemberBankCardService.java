/* 
 * TestMemberBankCardService.java  
 * 
 * version v1.0
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.member.test;

import java.util.List;

import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.member.bean.QuickpayCustBean;
import com.zlebank.zplatform.member.bean.RealNameBean;
import com.zlebank.zplatform.member.service.MemberBankCardService;

/**
 * 银行卡相关测试
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午5:35:11
 * @since 
 */
public class TestMemberBankCardService {
    private ApplicationContext ac;
    private MemberBankCardService memberBankCardService;
    @Before
    public void init() {
        ac = ApplicationContextUtil.get();
        memberBankCardService =  (MemberBankCardService) ac.getBean("memberBankCardServiceImpl");
    }
    /**
     * 实名认证保存
     */
//    @Test
    public void saveRealNameInfo() {
        boolean ok=true;
        try {
            RealNameBean bean = new RealNameBean();
            bean.setMemberId("100000000000564");
            bean.setIdentiNum("131122198809042145");
            bean.setIdentiType("01");
            memberBankCardService.saveRealNameInfo(bean);
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        Assert.assertTrue(ok);
    }
    
    /**
     * 实名认证查询
     */
//    @Test
    public void queryRealNameInfo() {
        boolean ok=true;
        RealNameBean queryRealNameInfo = null;
        try {
            RealNameBean bean = new RealNameBean();
            bean.setMemberId("100000000000564");
            queryRealNameInfo = memberBankCardService.queryRealNameInfo(bean);
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        
        System.out.println(JSONObject.fromObject(queryRealNameInfo));
        Assert.assertTrue(ok);
    }
    
    /**
     * 绑定银行卡
     */
//    @Test
    public void saveQuickPayCust() {
        boolean ok=true;
        try {
            QuickpayCustBean bean = new QuickpayCustBean();
            bean.setAccname("鲁晓帅-测试");
            bean.setCardno("6225102415463254");
            bean.setIdtype("01");
            bean.setIdnum("131122198701021456");
            bean.setCvv2("801");
            bean.setRelatememberno("100000000000564");
            memberBankCardService.saveQuickPayCust(bean );
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        Assert.assertTrue(ok);
    }
    /**
     * 解绑银行卡
     */
//    @Test
    public void unbindQuickPayCust() {
        boolean ok=true;
        try {
            QuickpayCustBean bean = new QuickpayCustBean();
            bean.setId(96L);
//            bean.setAccname("鲁晓帅-测试");
//            bean.setCardno("6225102415463254");
//            bean.setIdtype("01");
//            bean.setIdnum("131122198701021456");
//            bean.setCvv2("801");
//            bean.setRelatememberno("100000000000564");
            memberBankCardService.unbindQuickPayCust(bean);
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        Assert.assertTrue(ok);
    }
    
    
    /**
     * 签约银行卡查询
     */
//    @Test
    public void querySign() {
        boolean ok=true;
        try {
            QuickpayCustBean bean = new QuickpayCustBean();
//            bean.setId(96L);
//            bean.setAccname("鲁晓帅-测试");
//            bean.setCardno("6225102415463254");
//            bean.setIdtype("01");
//            bean.setIdnum("131122198701021456");
//            bean.setCvv2("801");
            bean.setRelatememberno("100000000000564");
            PagedResult<QuickpayCustBean> rtn = memberBankCardService.queryMemberBankCard("100000000000564","0",2,5);
            System.out.println(rtn.getTotal());
            List<QuickpayCustBean> rtnList = rtn.getPagedResult();
            for (QuickpayCustBean str : rtnList) {
                System.out.println(String.format("id:%s，卡号:%s，卡类型:%s", str.getId(),str.getCardno(),str.getCardtype()));
            }
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        Assert.assertTrue(ok);
    }
}
