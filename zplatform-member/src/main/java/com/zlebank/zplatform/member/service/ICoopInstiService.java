package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.CoopInstiMk;
import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;

/**
 * 
 * coop
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
    CoopInstiMk getCoopInstiMK(String instiCode,TerminalAccessType terminalAccessType);
    /**
     * 
     * @param instiCode
     * @param instiName
     * @return
     */
    String createCoopInsti(String instiCode,String instiName);
}
