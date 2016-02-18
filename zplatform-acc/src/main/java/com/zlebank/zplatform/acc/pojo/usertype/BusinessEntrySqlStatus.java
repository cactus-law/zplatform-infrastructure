/* 
 * AccEntrySqlStatus.java  
 * 
 * version v1.0
 *
 * 2015年8月31日 
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
import com.zlebank.zplatform.acc.bean.enums.BusinessEntryStatus;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 下午6:01:05
 * @since 
 */
public class BusinessEntrySqlStatus  extends BaseEnumSqlType<BusinessEntryStatus> {
    @Override
    public Object nullSafeGet(ResultSet rs,
            String[] names,
            SessionImplementor arg2,
            Object owner) throws HibernateException, SQLException {
        return BusinessEntryStatus.fromValue(rs.getString(names[0]));
    }

    public void nullSafeSet(PreparedStatement st,
            Object value,
            int index,
            SessionImplementor arg2) throws HibernateException, SQLException {
        BusinessEntryStatus status = value != null
                ? (BusinessEntryStatus) value
                : BusinessEntryStatus.UNKNOW;
        st.setString(index, status.getCode());
    }

}
