/* 
 * PersonDAO.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.pojo.PojoPersonDeta;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月9日 下午6:28:27
 * @since 
 */

public interface PersonDAO  extends BaseDAO<PojoPersonDeta> {
    /**
     * 通过手机号查询会员信息
     * @param phone
     * @return
     */
    public PojoPersonDeta getPersonByPhone(String phone);
       /**
        * 通过emal查询会员信息
        * @param email
        * @return
        */
    public PojoPersonDeta getPersonByEmail(String email);
    
        /**
         * 通过memberId得到会员信息
         * @param memberId
         * @return
         */
    public PojoPersonDeta  getPersonByMemberId(String memberId);
    
    /**
     * 个人会员根据memberID 和密码登录
     * @param memberId
     * @param pwd
     * @return
     */
    public PojoPersonDeta  personLoninByMid(String memberId, String pwd );
    
    /**
     * 个人会员根据电话号码和密码登录
     * @param memberId
     * @param pwd
     * @return
     */
    public PojoPersonDeta  personLoninByPhone(String phone, String pwd );
    
    /**
     * 个人会员根据邮箱和密码登录
     * @param memberId
     * @param pwd
     * @return
     */
    public PojoPersonDeta  personLoninByEmail(String email, String pwd );
    
}
