/* 
 * QuickpayCustDAO.java  
 * 
 * version TODO
 *
 * 2016年1月18日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.dao;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.pojo.PojoQuickpayCust;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午7:15:43
 * @since 
 */
public interface QuickpayCustDAO  extends BaseDAO<PojoQuickpayCust> {
    
    /**
     * 通过会员ID和卡号得到绑卡对象
     * @param memberId
     * @param cardNo
     * @return
     */
    public PojoQuickpayCust getQuickPayCard(String memberId, String cardNo);
}
