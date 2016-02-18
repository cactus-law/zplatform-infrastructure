/* 
 * TradeSqlType.java  
 * 
 * version TODO
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

import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月31日 下午6:05:42
 * @since 
 */
public class TradeSqlType  extends BaseEnumSqlType<TradeType> {
    @Override
    public Object nullSafeGet(ResultSet rs,
            String[] names,
            SessionImplementor arg2,
            Object owner) throws HibernateException, SQLException {
        return TradeType.fromValue(rs.getString(names[0]));
    }

    public void nullSafeSet(PreparedStatement st,
            Object value,
            int index,
            SessionImplementor arg2) throws HibernateException, SQLException {
        TradeType status = value != null
                ? (TradeType) value
                : TradeType.UNKNOW;
        st.setString(index, status.getCode());
    }

}
