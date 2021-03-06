/* 
 * FrozenStatusType.java  
 * 
 * version TODO
 *
 * 2015年8月26日 
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

import com.zlebank.zplatform.acc.bean.enums.FrozenStatusType;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月26日 下午1:47:50
 * @since 
 */
public class FrozenStatusSqlType   extends BaseEnumSqlType<FrozenStatusType>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return FrozenStatusType.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        FrozenStatusType status = value!=null?(FrozenStatusType)value:FrozenStatusType.UNKNOW;
        st.setString(index, status.getCode());
    }

}
