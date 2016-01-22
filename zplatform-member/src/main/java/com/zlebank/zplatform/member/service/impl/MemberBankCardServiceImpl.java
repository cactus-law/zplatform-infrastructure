/* 
 * MemberBankCardService.java  
 * 
 * version v1.0
 *
 * 2016年1月15日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.bean.DefaultPageResult;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.QuickpayCustBean;
import com.zlebank.zplatform.member.bean.RealNameBean;
import com.zlebank.zplatform.member.bean.enums.RealNameLvType;
import com.zlebank.zplatform.member.dao.AutonymIdentiDAO;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.dao.QuickpayCustDAO;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.UnbindBankFailedException;
import com.zlebank.zplatform.member.pojo.PojoAutonymIdenti;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.pojo.PojoQuickpayCust;
import com.zlebank.zplatform.member.service.MemberBankCardService;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月15日 下午2:51:05
 * @since 
 */
@Service
public class MemberBankCardServiceImpl  implements MemberBankCardService {

    private Log log = LogFactory.getLog(MemberBankCardServiceImpl.class);
    
    @Autowired
    QuickpayCustDAO quickpayCustDAO;
    @Autowired
    AutonymIdentiDAO autonymIdentiDAO;
    @Autowired
    private MemberDAO memberDAO;
    
    /**
     * 保存会员实名认证
     * @param bean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void saveRealNameInfo(RealNameBean bean) {
        if(log.isDebugEnabled()) {
            log.debug("参数1："+JSONObject.fromObject(bean));
        }
        // 更新实名认证表
        PojoAutonymIdenti pojo = new PojoAutonymIdenti();
        pojo.setMemberId(bean.getMemberId());// 会员号
        pojo.setRealname(bean.getRealname());// 真实姓名
        pojo.setIdentiType(bean.getIdentiType());// 证件类型
        pojo.setIdentiNum(bean.getIdentiNum());// 证件号
        pojo.setStatus("00");// 状态，00-有效，01-无效
        Date currentDate = new Date();
        pojo.setInuser(bean.getInuser());// 录入时间
        pojo.setIntime(currentDate);// 录入时间
        pojo.setUpuser(bean.getUpuser());// 更新人
        pojo.setUptime(currentDate);// 更新时间
        autonymIdentiDAO.merge(pojo);
        
        // 更新会员表
        PojoMember member = memberDAO.getMemberByMemberId(bean.getMemberId(), null);
        member.setRealnameLv(RealNameLvType.LV3);
        memberDAO.update(member);
    }

    /**
     * 查询实名认证信息【根据会员ID】
     * @param bean
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public RealNameBean queryRealNameInfo(RealNameBean bean) {
        if(log.isDebugEnabled()) {
            log.debug("参数1："+JSONObject.fromObject(bean));
        }
        PojoAutonymIdenti pojo = autonymIdentiDAO.getByMemberId(bean.getMemberId());
        RealNameBean rtnBean = new RealNameBean();
        rtnBean.setMemberId(pojo.getMemberId());
        rtnBean.setIdentiType(pojo.getIdentiType());
        rtnBean.setIdentiNum(pojo.getIdentiNum());
        rtnBean.setRealname(pojo.getRealname());
        rtnBean.setStatus(pojo.getStatus());
        return rtnBean;
    }

    /**
     * 保存银行卡绑卡信息
     * @param bean
     * @ Return long 绑卡ID
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public long saveQuickPayCust(QuickpayCustBean bean) {
        PojoQuickpayCust oldPojo = quickpayCustDAO.getQuickPayCard(bean.getRelatememberno(), bean.getCardno());
        if (oldPojo != null) {
            return oldPojo.getId();
        }
        PojoQuickpayCust pojo = BeanCopyUtil.copyBean(PojoQuickpayCust.class, bean);
        pojo.setStatus("00");
        pojo = quickpayCustDAO.merge(pojo);
        return pojo.getId();
    }

    /**
     * 解绑银行卡信息
     * @param bean
     * @throws DataCheckFailedException 
     * @throws UnbindBankFailedException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void unbindQuickPayCust(QuickpayCustBean bean) throws DataCheckFailedException, UnbindBankFailedException {
        if (log.isDebugEnabled()) {
            log.debug("解绑银行卡");
            log.debug(JSONObject.fromObject(bean));
        }
        try {
            PojoQuickpayCust card = quickpayCustDAO.getById(bean.getId());
            if (card == null) {
                throw new DataCheckFailedException("会员不存在");
            }
            card.setStatus("02");
            quickpayCustDAO.update(card);
        } catch(DataCheckFailedException e) {
            throw e;
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            throw new UnbindBankFailedException();
        }
    }

    /**
     * 查询签约银行卡信息（会员）
     * @param memberId 会员号
     * @param cardType 卡类型 
     *          0：借记卡+贷记卡
     *          1：借记卡
     *          2：贷记卡
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PagedResult<QuickpayCustBean> queryMemberBankCard(String memberId, String cardType,int page,  int pageSize) {
        if (log.isDebugEnabled()) {
            log.debug("查询签约银行卡信息（会员）");
            log.debug(memberId);
            log.debug(cardType);
            log.debug(page);
            log.debug(pageSize);
        }
        QuickpayCustBean queryBean = new QuickpayCustBean();
        queryBean.setCardtype(cardType);
        queryBean.setRelatememberno(memberId);
        int offset = (page) * pageSize;
        List<PojoQuickpayCust> pojoList = quickpayCustDAO.getListByQuery(offset, pageSize, queryBean);
        List<QuickpayCustBean> beanList = pojoToBean(pojoList);
        long count = quickpayCustDAO.count(queryBean);
        PagedResult<QuickpayCustBean> rtnBean = new DefaultPageResult<QuickpayCustBean>(beanList, count);
        return rtnBean;
    }

    /**
     * @param pojoList
     * @return
     */
    private List<QuickpayCustBean> pojoToBean(List<PojoQuickpayCust> pojoList) {
        List<QuickpayCustBean> rtnList = new ArrayList<QuickpayCustBean>();
        for (PojoQuickpayCust pojo : pojoList) {
            rtnList.add(BeanCopyUtil.copyBean(QuickpayCustBean.class, pojo));
        }
        return rtnList;
    }

}
