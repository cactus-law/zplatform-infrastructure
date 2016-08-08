/* 
 * AccRuleService.java  
 * 
 * version TODO
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 * 新增规则   RulePo(需要确定 交易类型+产品代码+渠道+科目号 联合起来 不重复)
 * 修改规则 规则修改 (需要审核)
 * 删除规则 (需要审核)
 * 查询规则 (规则名称 、科目代码、)
 */
package com.zlebank.zplatform.acc.service;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoSubjectRuleConfigure;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

/**
 * Class Description
 *  科目规则service
 * @author yangpeng
 * @version
 * @date 2015年8月25日 下午8:04:58
 * @since 
 */
public interface SubjectRuleService {
    /**
     * 新增规则
     * @param SubjectRule
     * @return id(规则ID)
     * @throws AccBussinessException
     */
    public long addSubjectRule(SubjectRule SubjectRule,long userId) throws AccBussinessException;
       /**
        * 修改规则
        * @param SubjectRule
        * @return Boolean 
        * @throws AccBussinessException
        */
    public Boolean  updateSubjectRule(SubjectRule SubjectRule,long userId) throws AccBussinessException;
        /**
         * 停用规则
         * @param id
         * @return Boolean
         * @throws AccBussinessException
         */
    public Boolean stopSubjectRule(Long id,Long userId) throws AccBussinessException;
        /**
         * 启用规则
         * @param id
         * @return Boolean
         * @throws AccBussinessException
         */
    public Boolean enableSubjectRule(Long id,Long userId) throws AccBussinessException;
        /**
         * 根据ID得到未停用的规则
         * @param id
         * @return SubjectRule
         * @throws AccBussinessException
         */
    public SubjectRule subjectRuleByID(Long id) throws AccBussinessException;
    /**
     * 根据ID与状态得到规则
     * @param id 规则ID
     * @param status 规则状态
     * @return SubjectRule 
     * @throws AccBussinessException
     */
    public SubjectRule subjectRuleByID(Long id,RuleStatusType status)throws AccBussinessException;
    
    public Boolean deleteSubjectRule(Long id) throws AccBussinessException;
    /**
     * 根据条件得到分录流水
     * @param sar分录查询条件
     * @param firset 
     * @param max分页单位
     * @return
     */
public List<Map<String, Object>> getRuleBySar(SubjectAccountRule sar,Integer page,Integer pageSize)throws AccBussinessException;
/**
 * 总条数
 * @param sar
 * @return
 */
public  Long getcount(SubjectAccountRule sar);
    
public List<PojoSubjectRuleConfigure> getRuleByBusiAndEvent(TradeInfo tradeInfo,EntryEvent entryEvent);
    
    }


