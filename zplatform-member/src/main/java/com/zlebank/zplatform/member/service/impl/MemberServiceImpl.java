/* 
 * MemberServiceImpl.java  
 * 
 * version TODO
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
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.bean.MerchBusi;
import com.zlebank.zplatform.member.bean.personBusi;
import com.zlebank.zplatform.member.bean.enums.MemberType;
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
	private final static String PERSON = "1";
	private final static String MERCH = "2";
	private final static String CAPITALNAME = "资金账户";
	private final static String SECURITYNAME = "保证金账户";

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
	 *
	 * @param memberId
	 * @return
	 * @throws AbstractBusiAcctException
	 * @throws MemberBussinessException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<BusiAcct> openBusiAcct(String name, String memberId, long userId)
			throws AbstractBusiAcctException, MemberBussinessException {
		List<BusiAcct> li = null;
		// 判断开通个人还是商户
		String str = memberId;
		String mid = str.substring(0, 1);
		// 商户账户开通
		if (MERCH.equals(mid)) {
			// MerchBusi mb=new MerchBusi();
			// mb.setMemberId(memberId);
			// mb.setMerchName(name+SECURITYNAME);
			// BusiAcct bus=new BusiAcct();
			// bus.setUsage(Usage.BAIL);
			// bus.setBusiAcctName(name+SECURITYNAME);
			// String code= busiacc.openBusiAcct(mb, bus, userId);
			// bus.setBusiAcctCode(code);
			// BusiAcct busi=new BusiAcct();
			// mb.setMerchName(name+CAPITALNAME);
			// busi.setUsage(Usage.BASICPAY);
			// busi.setBusiAcctName(name+CAPITALNAME);
			// String codes= busiacc.openBusiAcct(mb, busi, userId);
			// busi.setBusiAcctCode(codes);
			li = new ArrayList<BusiAcct>();
			BusiAcct bus = openSecurity(name, memberId, userId);
			li.add(bus);
			BusiAcct busi = openCapital(name, memberId, userId);
			li.add(busi);
			// 个人会员开通账户
		} else if (PERSON.equals(mid)) {
			// personBusi pb=new personBusi();
			// pb.setMemberId(memberId);
			// BusiAcct bus=new BusiAcct();
			// bus.setUsage(Usage.BAIL);
			// bus.setBusiAcctName(name+CAPITALNAME);
			// String code= busiacc.openBusiAcct(pb, bus, userId);
			// bus.setBusiAcctCode(code);
			BusiAcct bus = openPersonSecurity(name, memberId, userId);
			li = new ArrayList<BusiAcct>();
			li.add(bus);
			// memberId不合法
		} else {
			throw new MemberBussinessException("M100004");
		}
		// 开通个人账户
		// 开通商户账户
		return li;
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
	private BusiAcct openSecurity(String name, String memberId, long userId)
			throws AbstractBusiAcctException {

		MerchBusi mb = new MerchBusi();
		mb.setMemberId(memberId);
		mb.setMerchName(name + CAPITALNAME);
		BusiAcct bus = new BusiAcct();
		bus.setUsage(Usage.BASICPAY);
		bus.setBusiAcctName(name + CAPITALNAME);
		String code = busiacc.openBusiAcct(mb, bus, userId);
		bus.setBusiAcctCode(code);
		return bus;
	}

	/**
	 * 商户开通保证金账户
	 * 
	 * @param name
	 * @param memberId
	 * @param userId
	 * @return
	 * @throws AbstractBusiAcctException
	 */
	private BusiAcct openCapital(String name, String memberId, long userId)
			throws AbstractBusiAcctException {
		MerchBusi mb = new MerchBusi();
		BusiAcct busi = new BusiAcct();
		mb.setMemberId(memberId);
		mb.setMerchName(name + SECURITYNAME);
		busi.setUsage(Usage.BAIL);
		busi.setBusiAcctName(name + SECURITYNAME);
		String codes = busiacc.openBusiAcct(mb, busi, userId);
		busi.setBusiAcctCode(codes);
		return busi;
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
	private BusiAcct openPersonSecurity(String name, String memberId,
			long userId) throws AbstractBusiAcctException {
		personBusi pb = new personBusi();
		pb.setMemberId(memberId);
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
	public PojoMember getMbmberByMemberId(String memberId, MemberType type) {
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
		memberId = busiPojo.getMemberId();
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
