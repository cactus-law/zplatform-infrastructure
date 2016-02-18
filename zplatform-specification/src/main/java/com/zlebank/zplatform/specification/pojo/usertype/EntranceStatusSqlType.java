/* 
 * EntranceStatusSqlType.java  
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
import com.zlebank.zplatform.specification.bean.enums.EntranceStatus;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年9月16日 上午10:30:33
 * @since 
 */
public class EntranceStatusSqlType extends BaseEnumSqlType<EntranceStatus>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return EntranceStatus.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        EntranceStatus status = value!=null?(EntranceStatus)value:EntranceStatus.UNKNOW;
        st.setString(index, status.getCode());
    }
}
