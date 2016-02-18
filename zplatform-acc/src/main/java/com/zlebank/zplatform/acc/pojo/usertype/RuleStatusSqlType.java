/* 
 * StatusSqlType.java  
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

import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月24日 下午6:11:55
 * @since 
 */
public class RuleStatusSqlType  extends BaseEnumSqlType<RuleStatusType>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return RuleStatusType.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        RuleStatusType status = value!=null?(RuleStatusType)value:RuleStatusType.UNKNOW;
        st.setString(index, status.getCode());
    }
}
