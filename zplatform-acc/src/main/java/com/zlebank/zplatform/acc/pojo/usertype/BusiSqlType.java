/* 
 * CRDRSqlType.java  
 * 
 * version TODO
 *
 * 2015年8月24日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.acc.bean.enums.BusiType;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月24日 下午6:11:05
 * @since 
 */
public class BusiSqlType extends BaseEnumSqlType<BusiType>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return BusiType.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        BusiType status = value!=null?(BusiType)value:BusiType.UNKNOW;
        st.setString(index, status.getCode());
    }
}
