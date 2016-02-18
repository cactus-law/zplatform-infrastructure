/* 
 * RegistMemberService.java  
 * 
 * version TODO
 *
 * 2016年1月14日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.MemberBean;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.exception.CreateBusiAcctFailedException;
import com.zlebank.zplatform.member.exception.CreateMemberFailedException;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.InvalidMemberDataException;
import com.zlebank.zplatform.member.exception.LoginFailedException;

/**
 * 会员注册
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月14日 下午6:07:31
 * @since 
 */
public interface MemberOperationService {
    /**
     * 注册会员
     * @param memberType
     * @param member  (属性必填 : loginName/pwd/phone/instiCode)
     * @return String 会员号，失败时返回NULL
     * @throws InvalidMemberDataException 
     * @throws CreateMemberFailedException 
     * @throws CreateBusiAcctFailedException 
     */
    public String registMember(MemberType memberType, MemberBean member) throws InvalidMemberDataException, CreateMemberFailedException, CreateBusiAcctFailedException;

    /**
     * 会员登陆
     * @param memberType (属性必填 : [phone或loginName]/pwd/instiCode )
     * @param member
     * @return 会员号，失败时返回NULL
     * @throws DataCheckFailedException 
     * @throws LoginFailedException 
     */
    public String login(MemberType memberType, MemberBean member) throws DataCheckFailedException, LoginFailedException;

    /**
     * 验证支付密码 (属性必填 : [phone或loginName]/paypwd/instiCode )
     * @param memberType
     * @param member
     * @return
     * @throws DataCheckFailedException
     */
    boolean verifyPayPwd(MemberType memberType, MemberBean member)
            throws DataCheckFailedException;

    /**
     * 重置登陆密码
     * @param memberType
     * @param member (属性必填 : [phone或loginName]/pwd/instiCode )
     * @param newPwd 必填
     * @param boolean isCheckOldPassword 是否检查原有密码
     * @return boolean 重置成功返回true 其它情况返回false
     * @throws DataCheckFailedException
     */
    boolean resetLoginPwd(MemberType memberType,
            MemberBean member,
            String newPwd, boolean isCheckOldPassword) throws DataCheckFailedException;

    /**
     * 重置支付密码
     * @param memberType
     * @param member  (属性必填 : [phone或loginName]/paypwd/instiCode )
     * @param newPayPwd 必填
     * @param boolean isCheckOldPassword 是否检查原有密码
     * @return
     * @throws DataCheckFailedException
     */
    boolean resetPayPwd(MemberType memberType, MemberBean member, String newPayPwd, boolean isCheckOldPassword) throws DataCheckFailedException;
}
