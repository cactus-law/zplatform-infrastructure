package com.zlebank.zplatform.acc.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

public class EntryEventSqlType extends BaseEnumSqlType<EntryEvent>{
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return EntryEvent.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        EntryEvent entryEvent = value!=null?(EntryEvent)value:EntryEvent.UNKNOW;
        st.setString(index, entryEvent.getCode());
    }
}
