package com.zlebank.zplatform.member.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;

public class TerminalAccessTypeSqlType extends BaseEnumSqlType<TerminalAccessType>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return TerminalAccessType.fromValue(rs.getInt(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        TerminalAccessType terminalAccessType = value!=null?(TerminalAccessType)value:TerminalAccessType.UNKNOW;
        st.setInt(index, terminalAccessType.getCode());
    }
}
