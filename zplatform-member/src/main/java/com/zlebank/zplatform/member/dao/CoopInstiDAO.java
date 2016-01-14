package com.zlebank.zplatform.member.dao;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;

@Repository
public interface CoopInstiDAO extends BaseDAO<PojoCoopInsti>{
    
    /**
     * check if cooperative institution is exist
     * @param instiCode
     * @return
     */
    boolean isNameExist(String instiName);
}
