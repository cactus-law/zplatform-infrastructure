/* 
 * CommonStatusSqlType.java  
 * 
 * version TODO
 *
 * 2016年9月29日 
 * 
 * Copyright (c) 2016,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.CommonStatus;
import com.zlebank.zplatform.commons.dao.pojo.BaseEnumSqlType;

/**
 * Class Description
 *
 * @author houyong
 * @version
 * @date 2016年9月29日 下午12:05:37
 * @since 
 */
public class CommonStatusSqlType extends BaseEnumSqlType<CommonStatus>{

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor arg2,Object owner)
            throws HibernateException, SQLException {
        return CommonStatus.fromValue(rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor arg2)
            throws HibernateException, SQLException {
        CommonStatus status = value!=null?(CommonStatus)value:CommonStatus.UNKNOW;
        st.setString(index, status.getCode());
    }

}
