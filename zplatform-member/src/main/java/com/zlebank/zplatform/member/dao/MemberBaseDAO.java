/* 
 * MemberBaseDAO.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao;

import org.hibernate.Session;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.pojo.PojoMemberBase;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午8:41:31
 * @since 
 */
public interface MemberBaseDAO extends BaseDAO<PojoMemberBase> {
   /**
    * @param memberId
    * @return
    */
    public PojoMemberBase getMember(String memberId);
    
    public Session getCurrentSession();
    
}
