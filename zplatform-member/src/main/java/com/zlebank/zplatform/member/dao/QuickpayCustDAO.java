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
     * 通过会员ID，卡号，身份证号，账户名，手机号得到绑卡对象
     * @param memberId
     * @param cardNo
     * @param idnum
     * @param accname
     * @param phone
     * @return
     */
    public PojoQuickpayCust getQuickPayCard(String memberId, String cardNo,String idnum,String accname,String phone);

    /**
     * 通过ID (主键) 得到绑卡对象
     * @param id
     * @return
     */
    public PojoQuickpayCust getById(long id);
}
