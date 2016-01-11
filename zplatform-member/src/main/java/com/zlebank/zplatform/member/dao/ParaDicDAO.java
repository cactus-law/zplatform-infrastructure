/* 
 * PrimayKeyDAO.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoParaDic;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:12:32
 * @since 
 */
public interface ParaDicDAO extends BaseDAO<PojoParaDic>{
        public PojoParaDic getPrimay(String paraType)throws MemberBussinessException;
        public List<Map<String, Object>> getSeq(String sequences);
}
