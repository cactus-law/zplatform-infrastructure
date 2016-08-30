/* 
 * MemberServiceImpl.java  
 * 
 * version v1.2.1
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcct;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcctSubjectMapping;
import com.zlebank.zplatform.acc.service.AccountQueryService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.SubjectSelector;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.BusinessActor;
import com.zlebank.zplatform.member.bean.EnterpriseBusi;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.bean.PersonBusi;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.dao.MemberApplyDAO;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.dao.MerchDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.pojo.PojoMemberApply;
import com.zlebank.zplatform.member.service.MemberService;

/**
 * Class Description
 *
 * @author yangpeng,yangying
 * @version
 * @date 2015年9月10日 上午11:20:11
 * @since
 */
@Service
public class MemberServiceImpl implements MemberService {
    private final static String INDIVIDUAL = "1";// 个人会员
    private final static String ENTERPRISE = "2";// 企业会员

    @Autowired
    private MemberDAO memberDAOImpl;
    @Autowired
    private AccountQueryService aqs;
    @Autowired
    private BusiAcctService busiacc;
    @Autowired
    private BusiAcctDAO busiAcctDAO;
    @Autowired
    private MerchDAO merchDAO;
    @Autowired
    private SubjectSelector subjectSelector;
    @Autowired
    private MemberApplyDAO memberApplyDAO;

    /**
     *
     * @param busiAcctCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public BusiAcctQuery getBusiQueryBybCode(String busiAcctCode) {

        return aqs.getBusiQueryBybCode(busiAcctCode);
    }

    /**
     *
     * @param memberId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<BusiAcctQuery> getAllBusiByMId(String memberId) {

        return aqs.getAllBusiByMId(memberId);
    }

    /**
     *
     * @param page
     * @param pageSize
     * @param mQuery
     * @return
     * @throws MemberBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PagedResult<AccEntry> getAccEntryByQuery(int page,
            int pageSize,
            MemberQuery mQuery) throws MemberBussinessException {
        AccEntryQuery aeq = BeanCopyUtil.copyBean(AccEntryQuery.class, mQuery);
        // 业务账户号->会计账户号
        BusiAcctQuery query = aqs.getMemberQueryByID(mQuery.getAcctCode());
        if (query == null)
            throw new MemberBussinessException("M100006", mQuery.getAcctCode());
        String acctCode = query.getAcctCode();
        aeq.setAcctCode(acctCode);
        aeq.setStatus(AccEntryStatus.ACCOUNTED);
        return aqs.getAccEntryByQuery(page, pageSize, aeq);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public PagedResult<AccEntry> getAccEntryByQueryN(int page,
            int pageSize,
            MemberQuery mQuery) {
        AccEntryQuery aeq = BeanCopyUtil.copyBean(AccEntryQuery.class, mQuery);
        // 业务账户号->会计账户号
        BusiAcctQuery query = aqs.getMemberQueryByID(mQuery.getAcctCode());
        if (query != null) {
            String acctCode = query.getAcctCode();
            aeq.setAcctCode(acctCode);
        }
        return aqs.getAccEntryByQuery(page, pageSize, aeq);

    }

    /**
     * 开通 - 个人会员或 - 企业会员 - 合作机构 的【业务账户】和【会计账户】
     *
     * @param name
     *            会员名称
     * @param memberId
     *            会员号
     * @param userId
     *            操作人ID
     * @return 业务账户列表
     * @throws AbstractBusiAcctException
     * @throws MemberBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<BusiAcct> openBusiAcct(String name,
            String businessActorId,
            long userId) throws AbstractBusiAcctException,
            MemberBussinessException {
        
        /*
         * 获取会员号第一位，判断会员类型
         */
        String str = businessActorId;
        String mid = str.substring(0, 1);
        BusinessActorType busiActorType;
        if (ENTERPRISE.equals(mid)) {// 如果是开通企业会员
            busiActorType = BusinessActorType.ENTERPRISE;
        } else if (INDIVIDUAL.equals(mid)) {// 如果是开通个人会员
            busiActorType = BusinessActorType.INDIVIDUAL;
        } else {
            throw new MemberBussinessException("M100004");
        }
        
        List<BusiAcct> busiAcctList = null;
        busiAcctList = openDefaultIndiBusiAcct(name, businessActorId, userId,
                busiActorType);
        
        if (busiAcctList == null) {
            return new ArrayList<BusiAcct>();
        }
        return busiAcctList;
    }

    /**
     * 为会员开通所有默认开通的账户
     * 
     * @param memberName
     * @param memberId
     * @param userId
     * @param busiActorType
     * @return a list of business account
     * @throws AbstractBusiAcctException
     * @throws MemberBussinessException
     */
    private List<BusiAcct> openDefaultIndiBusiAcct(String memberName,
            String memberId,
            long userId,
            BusinessActorType busiActorType) throws AbstractBusiAcctException,
            MemberBussinessException {
        List<PojoBusiAcctSubjectMapping> busiAcctSubjectMappings = subjectSelector
                .getDefaultList(busiActorType);
        List<BusiAcct> busiAccts = new ArrayList<BusiAcct>();
        for (PojoBusiAcctSubjectMapping busiAcctSubjectMapping : busiAcctSubjectMappings) {
            BusiAcct busiAcct = new BusiAcct();
            busiAcct.setBusiAcctName(memberName);
            busiAcct.setUsage(busiAcctSubjectMapping.getUsage());
            BusinessActor busiActor = null;
            switch (busiActorType) {
                case INDIVIDUAL :
                    PersonBusi indivual = new PersonBusi();
                    indivual.setBusinessActorId(memberId);
                    busiActor = indivual;
                    break;
                case ENTERPRISE :
                    EnterpriseBusi enterprise = new EnterpriseBusi();
                    enterprise.setBusinessActorId(memberId);
                    busiActor = enterprise;
                    break;
                default :// never reach here
                    throw new MemberBussinessException("M100004");
            }
            String busiAccCode = busiacc.openBusiAcct(busiActor, busiAcct,
                    userId);
            busiAcct.setBusiAcctCode(busiAccCode);
            busiAccts.add(busiAcct);
        }
        return busiAccts;
    }


    /**
     *
     * @param memberId
     * @param type
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PojoMember getMbmberByMemberId(String memberId, MemberType type) {
        return memberDAOImpl.getMemberByMemberId(memberId, type);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PojoMember getMemberByEmail(String email) {
        return memberDAOImpl.getMemberByEmail(email);
    }

    /**
     *
     * @param phone
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PojoMember getMemberByphone(String phone) {
        return memberDAOImpl.getMemberByphone(phone);
    }

    /**
     * 根据账户号取memberId
     * 
     * @param account
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public String getMemberIdByBusiCode(String busiCode) {
        String memberId = "";
        PojoBusiAcct busiPojo = busiAcctDAO.getByBusiAcctCode(busiCode);
        if (busiPojo == null)
            return null;
        memberId = busiPojo.getBusinessActorId();
        return memberId;
    }

    /**
     *
     * @param qa
     * @return
     */
    @Override
    @Transactional(readOnly=true)
    public PagedResult<BusiAcctQuery> getBusiAccount(QueryAccount qa,
            Integer page,
            Integer pageSize) {
        return aqs.queryPaged(page, pageSize, qa);
    }

	/**
	 *
	 * @param loginName
	 * @param instiCode
	 * @return
	 */
	@Override
	public PojoMember getMemberByLoginNameAndCoopInsti(String loginName,
			long instiCode) {
		// TODO Auto-generated method stub
		return memberDAOImpl.getMemberByLoginNameAndCoopInsti(loginName, instiCode);
	}

	/**
	 *
	 * @param phone
	 * @param instiCode
	 * @return
	 */
	@Override
	@Transactional(readOnly=true)
	public PojoMember getMemberByPhoneAndCoopInsti(String phone, long instiCode) {
		// TODO Auto-generated method stub
		return memberDAOImpl.getMemberByPhoneAndCoopInsti(phone, instiCode);
	}
	
	@Transactional(readOnly=true,isolation=Isolation.READ_COMMITTED)
	public PojoMemberApply getMemberApply(String memberId){
		PojoMemberApply memberApply = memberApplyDAO.getMemberApply(memberId);
		return memberApply;
	}

}
