/* 
 * MerchSqlType.java  
 * 
 * version TODO
 *
 * 2015年9月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;
import com.zlebank.zplatform.member.bean.enums.MerchStatusType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月9日 下午9:50:03
 * @since 
 */
public class MerchStatusSqlType  extends BaseEnumSqlType<MerchStatusType>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return MerchStatusType.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
    	MerchStatusType status = value!=null?(MerchStatusType)value:MerchStatusType.UNKNOW;
        st.setString(index, status.getCode());
    }
}