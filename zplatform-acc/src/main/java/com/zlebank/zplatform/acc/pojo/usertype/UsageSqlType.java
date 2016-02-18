/* 
 * UsageSqlType.java  
 * 
 * version 1.0
 *
 * 2015年8月27日 
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

import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月27日 下午6:11:44
 * @since
 */
public class UsageSqlType extends BaseEnumSqlType<Usage> {
    @Override
    public Object nullSafeGet(ResultSet rs,
            String[] names,
            SessionImplementor arg2,
            Object owner) throws HibernateException, SQLException {
        return Usage.fromValue(rs.getString(names[0]));
    }

    public void nullSafeSet(PreparedStatement st,
            Object value,
            int index,
            SessionImplementor arg2) throws HibernateException, SQLException {
        Usage status = value != null ? (Usage) value : Usage.UNKNOW;
        st.setString(index, status.getCode());
    }
}
