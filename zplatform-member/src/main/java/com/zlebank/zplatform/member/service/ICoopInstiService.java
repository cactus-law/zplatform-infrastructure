package com.zlebank.zplatform.member.service;

import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.bean.CoopInstiMK;
import com.zlebank.zplatform.member.exception.AbstractCoopInstiException;
/**
 * 
 * cooperative institution service
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
     * @return null if not exist
     */
    CoopInstiMK getCoopInstiMK(String instiCode,
            TerminalAccessType terminalAccessType);
    /**
     * create a new cooperative institution 
     * @param instiName
     * @param userId
     * @return a unique code represent this institution in platform
     * @throws AbstractCoopInstiException if instiName exist,or generate cooperative institution mk error,or open insti account error
     */
    public String createCoopInsti(String instiName, long userId)
            throws AbstractCoopInstiException;
}
