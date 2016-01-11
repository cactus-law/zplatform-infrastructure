/* 
 * PrimayKeyServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年9月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoParaDic;
import com.zlebank.zplatform.member.service.PrimayKeyService;
import com.zlebank.zplatform.member.util.MemberUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月11日 下午3:47:12
 * @since
 */
@Service
public class PrimayKeyServiceImpl implements PrimayKeyService {
    @Autowired
    private ParaDicDAO primayDao;
    private final static String SEQUENCES="seq_t_merch_deta_memberid";
    /**
     * 得到序列
     * 
     * @param keyName
     * @return
     * @throws MemberBussinessException
     */
    @Override
    @Transactional
    public String getNexId(String paraType) throws MemberBussinessException {
        PojoParaDic para = primayDao.getPrimay(paraType);
        List<Map<String, Object>> li = primayDao.getSeq(SEQUENCES);
        String head = para.getParaCode();
        Map<String, Object> map = li.get(0);
        String tail = map.get("NEXTVAL") + "";
        String memberId = MemberUtil.getMemberID(head, tail);
        return memberId;
    }

}
