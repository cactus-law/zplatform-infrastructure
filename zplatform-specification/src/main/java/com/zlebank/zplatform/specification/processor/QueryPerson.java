/* 
 * SelectPersonSynchor.java  
 * 
 * version TODO
 *
 * 2015年9月15日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.specification.processor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.pojo.PojoPersonDeta;
import com.zlebank.zplatform.member.service.PersonService;
import com.zlebank.zplatform.specification.dao.MemberEntranceLinkDAO;
import com.zlebank.zplatform.specification.message.Message;
import com.zlebank.zplatform.specification.message.Response;
import com.zlebank.zplatform.specification.pojo.PojoMemberEntranceLink;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月15日 下午8:22:55
 * @since 
 */
@Service
public class QueryPerson   extends RawAbstractProcessor{
    private final static Log log = LogFactory
            .getLog(QueryPerson.class);
    private static final String SUCCESS_CODE="00";
    private static final String SUCCESS_DES="个人会员查询成功";
    private static final String FAIL_CODE="30"; 
    private static final String FAIL_DES="个人会员查询失败：";
    @Autowired
    private PersonService personService;
    @Autowired
    private MemberEntranceLinkDAO memberLink;
  
    /**
     *个人会员同步查询
     * @param request
     * @return
     */
    /*业务检查  
     * 商会下面是否有该会员 
     * 
     * 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Response realProcess(Message request) {
        // 返回结果定义
        Response res = new Response();
        res.setObject("merchNo", request.getString("merchNo"));
        res.setObject("sessionId", request.getString("sessionId"));
        String errorMsg="";

        PojoPersonDeta person=null;
        try {
            // 从 会员所属关联表 查出 相应的记录
            List<PojoMemberEntranceLink> pojoMemberEntranceLinkList = memberLink.getByEntranceMemberId(request.getString("entranceMemId"));
            if (pojoMemberEntranceLinkList != null &&  pojoMemberEntranceLinkList.size()>1) {
             // 返回报文【应答码】
                res.setResCode(FAIL_CODE);
                // 返回报文【应答码描述】
                res.setResDes(FAIL_DES + "根据对方的内部会员号查到多条记录，请联系管理员。");
                return res;
            }
            PojoMemberEntranceLink pojoMemberEntranceLink = pojoMemberEntranceLinkList != null ?  pojoMemberEntranceLinkList.get(0) : new  PojoMemberEntranceLink();
            // 如果不存在或者与报文里的会员号不一致，则定义为会员不存在。
            if (StringUtil.isNotEmpty(pojoMemberEntranceLink.getMemberId())) {
                if (StringUtil.isEmpty(request.getString("memberId")) || pojoMemberEntranceLink.getMemberId().equals(request.getString("memberId"))) {
                    // 获取个人会员
                    person=personService.getPersonByMemberId(pojoMemberEntranceLink.getMemberId());
                }  else {
                    errorMsg = "根据对方的内部会员号找到的会员号为空或者和传入的会员号不一致";
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e); 
        }

        if (person == null) {
            errorMsg = "会员不存在";
        }
        
        if (!StringUtil.isEmpty(errorMsg)) {
            // 返回报文【应答码】
            res.setResCode(FAIL_CODE);
            // 返回报文【应答码描述】
            res.setResDes(FAIL_DES + errorMsg);
            return res;
        }

      // 返回报文【应答码】
      res.setResCode(SUCCESS_CODE);
      // 返回报文【应答码描述】
      res.setResDes(SUCCESS_DES);
      // 返回报文【个人会员号】
      res.setObject("memberId", person.getMemberId());
      // 返回报文【个人会员状态】
      res.setObject("status", person.getStatus() == null ? "" : person.getStatus().getCode());
      
      return res;
    }

}
