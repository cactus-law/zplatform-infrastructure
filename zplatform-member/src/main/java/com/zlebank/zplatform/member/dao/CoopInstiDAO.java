package com.zlebank.zplatform.member.dao;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.pojo.PojoCoopInsti;
import com.zlebank.zplatform.member.pojo.PojoInstiMK;

@Repository
public interface CoopInstiDAO extends BaseDAO<PojoCoopInsti>{
    
    /**
     * check if cooperative institution is exist
     * @param instiCode
     * @return
     */
    boolean isNameExist(String instiName);
    /**
     * get PojoInstiMK entity by instiCode and terminalAccessType
     * @param instiCode
     * @param terminalAccessType
     * @return
     */
    PojoInstiMK getByInstiCode(String instiCode,TerminalAccessType terminalAccessType);
}
