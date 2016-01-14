package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.bean.CoopInstiMK;
import com.zlebank.zplatform.member.exception.CoopInstiException;
/**
 * 
 * coop service
 *
 * @author yangying
 * @version
 * @date 2016年1月13日 下午1:53:19
 * @since
 */
public interface ICoopInstiService {
    /**
     * 
     * @param instiCode
     * @param terminalAccessType
     * @return
     */
    CoopInstiMK getCoopInstiMK(String instiCode,TerminalAccessType terminalAccessType);
    /**
     * 
     * @param instiCode
     * @param instiName
     * @return
     */
    String createCoopInsti(String instiName)throws CoopInstiException;
}
