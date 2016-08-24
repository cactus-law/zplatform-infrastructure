/* 
 * MemberApplyDAOImpl.java  
 * 
 * version TODO
 *
 * 2016年8月19日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.member.dao.MemberApplyDAO;
import com.zlebank.zplatform.member.pojo.PojoMemberApply;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2016年8月19日 上午9:16:14
 * @since 
 */
@Repository("memberApplyDAO")
public class MemberApplyDAOImpl extends HibernateBaseDAOImpl<PojoMemberApply> implements MemberApplyDAO{

}
