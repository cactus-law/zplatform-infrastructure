/* 
 * EntranceAPILogDAO.java  
 * 
 * version TODO
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.dao;
import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.specification.pojo.PojoEntranceAPILog;

/**
 * 
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:51:37
 * @since
 */
public interface EntranceAPILogDAO  extends BaseDAO<PojoEntranceAPILog> {

    /**
     * 通过会员号和事务ID取得相应的记录
     * @param memberId
     * @param sessionId
     * @return
     */
    public PojoEntranceAPILog getByMemberIdSessionId(String memberId, String sessionId);

}
