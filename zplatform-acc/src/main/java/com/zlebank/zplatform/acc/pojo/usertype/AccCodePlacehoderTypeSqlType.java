package com.zlebank.zplatform.acc.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.acc.bean.enums.AccCodeType;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;
/**
 * 
 * TODO
 *
 * @author yangying
 * @version
 * @date 2016年5月10日 下午1:31:07
 * @since
 */
public class AccCodePlacehoderTypeSqlType extends BaseEnumSqlType<AccCodeType>{
    @Override
    public Object nullSafeGet(ResultSet rs,
            String[] names,
            SessionImplementor arg2,
            Object owner) throws HibernateException, SQLException {
        return AccCodeType.fromValue(rs.getString(names[0]));
    }

    public void nullSafeSet(PreparedStatement st,
            Object value,
            int index,
            SessionImplementor arg2) throws HibernateException, SQLException {
        AccCodeType accCodePlacehoderType = value != null
                ? (AccCodeType) value
                : AccCodeType.UNKNOW;
        st.setString(index, accCodePlacehoderType.getCode());
    }
}
