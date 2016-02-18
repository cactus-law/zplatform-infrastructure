/* 
 * MemberSqlType.java  
 * 
 * version 1.0
 *
 * 2015年8月28日 
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

import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月28日 下午1:11:16
 * @since
 */
public class BusinessActorTypeSqlType extends BaseEnumSqlType<BusinessActorType> {
    @Override
    public Object nullSafeGet(ResultSet rs,
            String[] names,
            SessionImplementor arg2,
            Object owner) throws HibernateException, SQLException {
        return BusinessActorType.fromValue(rs.getString(names[0]));
    }

    public void nullSafeSet(PreparedStatement st,
            Object value,
            int index,
            SessionImplementor arg2) throws HibernateException, SQLException {
        BusinessActorType type = value != null
                ? (BusinessActorType) value
                : BusinessActorType.UNKNOW;
        st.setString(index, type.getCode());
    }
}
