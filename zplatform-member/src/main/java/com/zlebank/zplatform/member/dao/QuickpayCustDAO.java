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

import com.zlebank.zplatform.commons.dao.BasePagedQueryDAO;
import com.zlebank.zplatform.member.bean.QuickpayCustBean;
import com.zlebank.zplatform.member.pojo.PojoQuickpayCust;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月18日 下午7:15:43
 * @since 
 */
public interface QuickpayCustDAO  extends BasePagedQueryDAO<PojoQuickpayCust,QuickpayCustBean> {
    
    /**
     * 通过会员ID和卡号得到绑卡对象
     * @param memberId
     * @param cardNo
     * @return
     */
    public PojoQuickpayCust getQuickPayCard(String memberId, String cardNo);

    /**
     * 通过ID (主键) 得到绑卡对象
     * @param id
     * @return
     */
    public PojoQuickpayCust getById(long id);
    
    
}
