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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.AccEntryQuery;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.dao.BusiAcctDAO;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.pojo.PojoBusiAcct;
import com.zlebank.zplatform.acc.service.AccountQueryService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.member.bean.CoopInstiBusi;
import com.zlebank.zplatform.member.bean.EnterpriseBusi;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.bean.PersonBusi;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.dao.MerchDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.service.MemberService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月10日 上午11:20:11
 * @since
 */
@Service
public class MemberServiceImpl implements MemberService {
	private final static String PERSON = "1";// 个人会员
	private final static String ENTERPRISE = "2";// 企业会员
	private final static String COOPINSTI = "3";// 合作机构
	private final static String CAPITALNAME = "【默认资金账户】";

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
	public PagedResult<AccEntry> getAccEntryByQuery(int page, int pageSize,
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
	public PagedResult<AccEntry> getAccEntryByQueryN(int page, int pageSize,
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
	 * 开通
	 * - 个人会员或
	 * - 企业会员
	 * - 合作机构
	 *  的【业务账户】和【会计账户】
	 *
	 * @param name 会员名称
	 * @param memberId 会员号
	 * @param userId 操作人ID
	 * @return  业务账户列表
	 * @throws AbstractBusiAcctException
	 * @throws MemberBussinessException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<BusiAcct> openBusiAcct(String name, String businessActorId, long userId)
			throws AbstractBusiAcctException, MemberBussinessException {
		List<BusiAcct> busiAcctList = null;
		// 判断开通个人还是商户还是合作机构
		String str = businessActorId;
		String mid = str.substring(0, 1);
		// 企业会员账户开通
		if (ENTERPRISE.equals(mid)) {
		    busiAcctList = new ArrayList<BusiAcct>();
			BusiAcct bus = openSecurity(name, businessActorId, userId);
			busiAcctList.add(bus);
			// 个人会员账户开通
		} else if (PERSON.equals(mid)) {
			BusiAcct bus = openPersonSecurity(name, businessActorId, userId);
			busiAcctList = new ArrayList<BusiAcct>();
			busiAcctList.add(bus);
			// 合作机构账户开通
		} else if (COOPINSTI.equals(mid)) {
	        BusiAcct bus = openCoopInsti(name, businessActorId, userId);
	        busiAcctList = new ArrayList<BusiAcct>();
	        busiAcctList.add(bus);
		} else {
			throw new MemberBussinessException("M100004");
		}
		return busiAcctList;
	}

	/**
	 * 商户资金账户开通
	 * 
	 * @param name
	 * @param memberId
	 * @param userId
	 * @return
	 * @throws AbstractBusiAcctException
	 */
	private BusiAcct openSecurity(String name, String businessActorId, long userId)
			throws AbstractBusiAcctException {

	    EnterpriseBusi mb = new EnterpriseBusi();
		mb.setBusinessActorId(businessActorId);
		mb.setBusinessActorName(name + CAPITALNAME);
		BusiAcct bus = new BusiAcct();
		bus.setUsage(Usage.BASICPAY);
		bus.setBusiAcctName(name + CAPITALNAME);
		String code = busiacc.openBusiAcct(mb, bus, userId);
		bus.setBusiAcctCode(code);
		return bus;
	}

	/**
	 * 个人资金账户开通
	 * 
	 * @param name
	 * @param memberId
	 * @param userId
	 * @return
	 * @throws AbstractBusiAcctException
	 */
	private BusiAcct openPersonSecurity(String name, String businessActorId,
			long userId) throws AbstractBusiAcctException {
		PersonBusi pb = new PersonBusi();
		pb.setBusinessActorId(businessActorId);
		BusiAcct bus = new BusiAcct();
		bus.setUsage(Usage.BASICPAY);
		bus.setBusiAcctName(name + CAPITALNAME);
		String code = busiacc.openBusiAcct(pb, bus, userId);
		bus.setBusiAcctCode(code);
		return bus;
	}
	
	   /**
     * 合作机构资金账户开通
     * 
     * @param name
     * @param memberId
     * @param userId
     * @return
     * @throws AbstractBusiAcctException
     */
    private BusiAcct openCoopInsti(String name, String businessActorId,
            long userId) throws AbstractBusiAcctException {
        CoopInstiBusi pb = new CoopInstiBusi();
        pb.setBusinessActorId(businessActorId);
        BusiAcct bus = new BusiAcct();
        bus.setUsage(Usage.BASICPAY);
        bus.setBusiAcctName(name + CAPITALNAME);
        String code = busiacc.openBusiAcct(pb, bus, userId);
        bus.setBusiAcctCode(code);
        return bus;
    }

	/**
	 *
	 * @param memberId
	 * @param type
	 * @return
	 */
	@Override
	public PojoMember getMbmberByMemberId(String memberId, BusinessActorType type) {
		return memberDAOImpl.getMbmberByMemberId(memberId, type);
	}

	/**
	 *
	 * @param email
	 * @return
	 */
	@Override
	public PojoMember getMemberByEmail(String email) {
		return memberDAOImpl.getMemberByEmail(email);
	}

	/**
	 *
	 * @param phone
	 * @return
	 */
	@Override
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
	public PagedResult<BusiAcctQuery> getBusiAccount(QueryAccount qa,
			Integer page, Integer pageSize) {
		return aqs.queryPaged(page, pageSize, qa);
	}

}
