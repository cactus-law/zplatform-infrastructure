/* 
 * IndustryGroupMemberServiceImpl.java  
 * 
 * version TODO
 *
 * 2016年9月28日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.enums.CommonStatus;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.Individual;
import com.zlebank.zplatform.member.bean.InduGroupMemberBean;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.dao.IndustryGroupMemberDAO;
import com.zlebank.zplatform.member.pojo.PojoIndustryGroupMember;
import com.zlebank.zplatform.member.service.IndustryGroupMemberService;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月28日 上午9:43:40
 * @since 
 */
@Service
public class IndustryGroupMemberServiceImpl extends AbstractBasePageService<InduGroupMemberQuery, InduGroupMemberBean> implements IndustryGroupMemberService {
    @Autowired
    private IndustryGroupMemberDAO induGroupMemberDao;
    @Autowired
    private BusiAcctService busiAcctService;
    /**
     *
     * @param example
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    protected long getTotal(InduGroupMemberQuery queryBean) {
        return induGroupMemberDao.count(queryBean);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    protected List<InduGroupMemberBean> getItem(int offset,
            int pageSize,
            InduGroupMemberQuery queryBean) {
        List<PojoIndustryGroupMember> pojoInduGroupMembers= induGroupMemberDao.getItem(offset,pageSize,queryBean);
        List<InduGroupMemberBean> result=new ArrayList<InduGroupMemberBean>();
        for (PojoIndustryGroupMember pojoIndustryGroupMember : pojoInduGroupMembers) {
            InduGroupMemberBean beanTemp=BeanCopyUtil.copyBean(InduGroupMemberBean.class, pojoIndustryGroupMember);
            result.add(beanTemp);
        }
        pojoInduGroupMembers=null;
        return result;
    }

    /**
     *
     * @param bean
     * @throws AbstractBusiAcctException 
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public String addMemberToGroup(final InduGroupMemberBean bean,boolean openAcct) throws AbstractBusiAcctException {
        PojoIndustryGroupMember pojoInduMember=new PojoIndustryGroupMember();
        pojoInduMember=BeanCopyUtil.copyBean(PojoIndustryGroupMember.class, bean);
        pojoInduMember.setInTime(new Date());
        final String uniqueTag=generateUniqueTag(bean);
        pojoInduMember.setUniqueTag(uniqueTag);
        pojoInduMember.setStatus(CommonStatus.NORMAL);
        pojoInduMember=induGroupMemberDao.merge(pojoInduMember);
        if (openAcct==true) {
            BusinessActor busiActor=new BusinessActor() {
                @Override
                public BusinessActorType getBusinessActorType() {
                    // TODO Auto-generated method stub
                    return bean.getBusiActorType();
                }
                
                @Override
                public String getBusinessActorId() {
                    // TODO Auto-generated method stub
                    return uniqueTag;
                }
            };
            BusiAcct busiAcct=new BusiAcct();
            busiAcct.setBusiAcctName("授信账户");
            busiAcct.setUsage(bean.getUsage());
            busiAcctService.openBusiAcct(busiActor, busiAcct, bean.getInuser());
        }
        return pojoInduMember.getUniqueTag();
    }
    
    private String generateUniqueTag(InduGroupMemberBean bean){
        String uniqueTag=null;
        if (bean.getUsage()==Usage.BASICPAY) {
            uniqueTag=bean.getMemberId();
        }else{
            int result=(int)(Math.random()*900)+100;
            uniqueTag=DateUtil.getCurrentDateTime().substring(0,12)+result;
        }
        return uniqueTag;
    }


	/**
	 *
	 * @param memberId
	 * @param groupCode
	 * @return
	 */
	@Override
	public InduGroupMemberBean getGroupMemberByMemberIdAndGroupCode(
			String memberId, String groupCode) {
		PojoIndustryGroupMember groupMember = induGroupMemberDao.getGroupMemberByMemberIdAndGroupCode(memberId, groupCode);
		if(groupMember==null){
			return null;
		}
		return BeanCopyUtil.copyBean(InduGroupMemberBean.class, groupMember);
	}
	
    /**
     *
     * @param queryBean
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public List<InduGroupMemberBean> queryGroupMember(InduGroupMemberQuery queryBean) {
        List<PojoIndustryGroupMember> groupMembers= induGroupMemberDao.queryGroupMember(queryBean);
        List<InduGroupMemberBean> result=new ArrayList<InduGroupMemberBean>();
        for (PojoIndustryGroupMember pojoIndustryGroupMember : groupMembers) {
            InduGroupMemberBean beanTemp=BeanCopyUtil.copyBean(InduGroupMemberBean.class, pojoIndustryGroupMember);
            result.add(beanTemp);
        }
        groupMembers=null;
        return result;
    }

	
}
