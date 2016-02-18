/* 
 * EntranceTypeSqlType.java  
 * 
 * version 1.0
 *
 * 2015年9月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;
import com.zlebank.zplatform.specification.bean.enums.EntranceType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 下午12:12:39
 * @since 
 */
public class EntranceTypeSqlType extends BaseEnumSqlType<EntranceType>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return EntranceType.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        EntranceType status = value!=null?(EntranceType)value:EntranceType.UNKNOW;
        st.setString(index, status.getCode());
    }
}
