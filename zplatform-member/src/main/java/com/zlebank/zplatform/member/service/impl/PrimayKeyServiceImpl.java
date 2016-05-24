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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.exception.PrimaykeyGeneratedException;
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
    private final Log log = LogFactory.getLog(PrimayKeyServiceImpl.class);
    @Autowired
    private ParaDicDAO primayDao;
    private final static String SEQUENCES = "seq_t_merch_deta_memberid";

    @Override
    public String getNexId(String paraType) throws MemberBussinessException {
        PojoParaDic para;
        try {
            para = primayDao.getPrimay(paraType);
        } catch (Exception e) {
            throw new MemberBussinessException("MemberBussinessException");
        }
        if (para == null) {
            throw new MemberBussinessException("MemberBussinessException");
        }
        List<Map<String, Object>> li = primayDao.getSeq(SEQUENCES);
        String head = para.getParaCode();
        Map<String, Object> map = li.get(0);
        String tail = map.get("NEXTVAL") + "";
        String memberId = MemberUtil.getMemberID(head, tail);
        return memberId;
    }

    @Override
    @Transactional
    public String getNexId(String paraType, String seqName)
            throws PrimaykeyGeneratedException {
        PojoParaDic para;
        try {
            para = primayDao.getPrimay(paraType);
        } catch (Exception e) {
            log.warn("获取会员序列号发生错误.Casued by:"+e.getMessage());
            throw new PrimaykeyGeneratedException();
        }
        if (para == null) {
            log.warn("获取会员序列号发生错误.序列号参数:null");
            throw new PrimaykeyGeneratedException();
        }
        String tail = primayDao.getSeqNextval(seqName);
        String head = para.getParaCode();
        String memberId = MemberUtil.getMemberID(head, tail);
        return memberId;
    }
}
