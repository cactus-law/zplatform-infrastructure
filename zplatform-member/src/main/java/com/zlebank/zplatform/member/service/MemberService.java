/* 
 * MemberService.java  
 * 
 * version TODO
 *
 * 2015年9月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import java.util.List;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMember;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月10日 上午11:11:20
 * @since 
 */
public interface MemberService {
    
    /**
     * 根据业务账户号得到账户信息
     * @param busiAcctCode
     * @return List<BusiAcctQuery>
     */
    public BusiAcctQuery getBusiQueryBybCode(String busiAcctCode);
    /**
     * 根据会员号得到所有账户信息
     * @param memberId
     * 
     * @return List<BusiAcctQuery>
     */
    public List<BusiAcctQuery> getAllBusiByMId(String memberId);
    /**
     * 接口调用根据条件得到分录流水
     * @param accountId
     * @return List<AccEntry>
     * @throws MemberBussinessException 
     */
    public  PagedResult<AccEntry>  getAccEntryByQuery(int page,int pageSize, MemberQuery mQuery) throws MemberBussinessException;
        /**
         * 开通账户
         * @param memberId
         * @param userId
         * @return
         */
    public List<BusiAcct> openBusiAcct(String name,String memberId,long userId) throws AbstractBusiAcctException, MemberBussinessException;
    /**
     * 通过 会员ID、状态得到会员
     * @param memberId
     * @param type
     * @return
     */
    public PojoMember getMbmberByMemberId(String memberId,BusinessActorType type);
    
    /**
     * 通过邮箱得到会员信息 
     * @param email
     * @return PojoMember
     */
     public PojoMember getMemberByEmail(String email);
         /**
          * 通过电话得到会员信息
          * @param phone
          * @return PojoMember
          */
     public PojoMember getMemberByphone(String phone);

    /**
     *  判断是否是该一级商户的业务账户
     * @param merchNo 一级商户的memberId
     * @param account 业务账户
     * @return
     */
     public String  getMemberIdByBusiCode(String busiCode);
     /**
      * 内部调用分录流水查询
      * @param page
      * @param pageSize
      * @param mQuery
      * @return
      */
     public PagedResult<AccEntry> getAccEntryByQueryN(int page,
             int pageSize,
             MemberQuery mQuery);
     
     /**
      * 账户查询
      * @param qa 用户账户查询Bean
      * @return
      */
     public PagedResult<BusiAcctQuery> getBusiAccount(QueryAccount qa,Integer page,Integer pageSize);
    

     
    }
     

