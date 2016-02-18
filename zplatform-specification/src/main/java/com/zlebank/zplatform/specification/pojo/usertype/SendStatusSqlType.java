/* 
 * RequestSqlType.java  
 * 
 * version TODO
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
import com.zlebank.zplatform.specification.bean.enums.SendStatus;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年9月16日 下午4:40:57
 * @since 
 */
public class SendStatusSqlType extends BaseEnumSqlType<SendStatus>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return SendStatus.formatValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        SendStatus status = value!=null?(SendStatus)value:SendStatus.UNKNOW;
        st.setString(index, status.getCode());
    }
}
