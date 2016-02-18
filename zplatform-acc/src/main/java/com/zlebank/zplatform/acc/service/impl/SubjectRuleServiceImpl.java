/* 
 * SubjectRuleServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.dao.SubjectRuleConfigureDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoSubjectRuleConfigure;
import com.zlebank.zplatform.acc.service.SubjectRuleService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月27日 下午5:30:50
 * @since 
 */
@Service
public class SubjectRuleServiceImpl implements SubjectRuleService {
    
    @Autowired
    private SubjectRuleConfigureDAO subRule;
    
    
    


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public long addSubjectRule(SubjectRule SubjectRule,long userId) throws AccBussinessException {
      PojoSubjectRuleConfigure subRulePojo= BeanCopyUtil.copyBean(PojoSubjectRuleConfigure.class, SubjectRule);
        subRulePojo.setInTime(new Date());
        subRulePojo.setInUser(userId);
        subRulePojo.setStatus(RuleStatusType.NORMAL);
        PojoSubjectRuleConfigure  subjectRulePojo =  subRule.save(subRulePojo);  
        if(subjectRulePojo==null){
           throw new AccBussinessException("E100009"); 
        }else{
        return subjectRulePojo.getId();
    }}

 
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
    public Boolean updateSubjectRule(SubjectRule SubjectRule,long userId) throws AccBussinessException {
      PojoSubjectRuleConfigure subjectRulePo=  subRule.getNeConfigure(SubjectRule.getId());
      if(subjectRulePo==null){
         throw new AccBussinessException("E100010", new Object[]{String.valueOf(SubjectRule.getId())});
      }else{
          PojoSubjectRuleConfigure rule=   BeanCopyUtil.copyBean(PojoSubjectRuleConfigure.class, SubjectRule);
          rule.setUpTime(new Date());
          rule.setUpUser(userId);
          rule.setInTime(subjectRulePo.getInTime());
          rule.setInUser(subjectRulePo.getInUser());
          PojoSubjectRuleConfigure resultRulepo=   subRule.save(rule);
    if(resultRulepo==null){
        throw new AccBussinessException("E100011");
        
    }else{
        //修改成功
        return true;
    }
      }
    }
  
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
    public Boolean stopSubjectRule(Long id,Long userId) throws AccBussinessException {
        PojoSubjectRuleConfigure rulePojo= subRule.getNeConfigure(id);
        if(rulePojo==null){
            throw new AccBussinessException("E100012");
        }else{
            rulePojo.setUpTime(new Date());
            rulePojo.setUpUser(userId);
            rulePojo.setStatus(RuleStatusType.STOP);
            return true;
          
        }
    }

  
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
    public Boolean enableSubjectRule(Long id,Long userId) throws AccBussinessException {
        PojoSubjectRuleConfigure rulePojo= subRule.getNeConfigure(id);
      if(rulePojo==null){
          throw new AccBussinessException("E100012");
      }else{
          rulePojo.setUpTime(new Date());
          rulePojo.setUpUser(userId);
          rulePojo.setStatus(RuleStatusType.NORMAL);
          return true;
        
      }
    }

 
    @Override
    @Transactional(propagation =Propagation.REQUIRED ,rollbackFor=Throwable.class)
    public SubjectRule subjectRuleByID(Long id) throws AccBussinessException {
        PojoSubjectRuleConfigure rulePojo= subRule.getSubjectRule(id);
        if(rulePojo==null){
            throw new AccBussinessException("E100012");
        }else{
            return   BeanCopyUtil.copyBean(SubjectRule.class, rulePojo);
          
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
    public SubjectRule subjectRuleByID(Long id, RuleStatusType status)
            throws AccBussinessException {
     PojoSubjectRuleConfigure rulePojo= subRule.getConfigure(id, status);
        if(rulePojo==null){
            throw new AccBussinessException("E100012");
        }else{
            return   BeanCopyUtil.copyBean(SubjectRule.class, rulePojo);
          
        }
    }


    /**
     *
     * @param id
     * @return
     * @throws AccBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
    public Boolean deleteSubjectRule(Long id) throws AccBussinessException {
        PojoSubjectRuleConfigure rulePojo= subRule.getNeConfigure(id);
        if(rulePojo==null){
            throw new AccBussinessException("E100012");
        }else{
            rulePojo.setStatus(RuleStatusType.LOGOUT);
            return true;
          
        }
        
    }


 




    /**
     *
     * @param sar
     * @param firset
     * @param max
     * @return
     * @throws AccBussinessException
     */
    @Override
    public List<Map<String, Object>> getRuleBySar(SubjectAccountRule sar,
            Integer page,
            Integer pageSize) throws AccBussinessException {
       
        
        long total = getcount(sar);

        page = getPage(total, page, pageSize);
        int offset = (page - 1) * pageSize;
      List<Map<String, Object>> li=subRule.getRuleBySar(sar, offset, pageSize);
      if(!li.isEmpty()){
        for(Map<String, Object> map:li){
           String ordform=(String)map.get("ORDFORM");
           String or=    ordform.replaceAll("A", "本金")
                   .replaceAll("B", "佣金").replaceAll("C", "手续费").replaceAll("T", "通道手续费")
                   .replaceAll("-", "<span style='color:red;font-size:18px' >&nbsp;-&nbsp;</span>")
                  .replaceAll("\\+", "<span style='color:red;font-size:18px' >&nbsp;+&nbsp;</span>");
           String ors=    ordform.replaceAll("A", "本金")
                   .replaceAll("B", "佣金").replaceAll("C", "手续费").replaceAll("T", "通道手续费");
                 
           map.put("ORDF", ors); 
           map.put("ORDFORMS", or);
        }
        return li;
          
      }  
        return null;
        
    }


    /**
     *
     * @param sar
     * @return
     */
    @Override
    public Long getcount(SubjectAccountRule sar) {
    return   subRule.getcount(sar);
    }

    /**
     * 当前页
     * @param total
     * @param page
     * @param pageSize
     * @return
     */
    private int getPage(long total, int page, int pageSize) {
        long maxPage = (total % pageSize == 0) ? (total / pageSize) : (total
                / pageSize + 1);
        if (page > maxPage) {
            return Long.valueOf(maxPage).intValue();
        }
        return page;
    }

}
