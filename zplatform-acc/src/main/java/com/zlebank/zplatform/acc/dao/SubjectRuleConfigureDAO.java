/* 
 * SubjectRuleConfigure.java  
 * 
 * version TODO
 *
 * 2015年8月27日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.acc.bean.AccEntryRules;
import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoSubjectRuleConfigure;
import com.zlebank.zplatform.commons.dao.BaseDAO;

/**
 * 规则关系的增删改查
 * Class Description
 * 规则配置DAO
 * @author yangpeng
 * @version
 * @date 2015年8月27日 下午6:32:53
 * @since 
 */
public interface SubjectRuleConfigureDAO extends BaseDAO<PojoSubjectRuleConfigure>{
    
    /**
     * 根据ID得到分录规则
     * @param id
     * @return PojoSubjectRuleConfigure
     */
    public PojoSubjectRuleConfigure getSubjectRule(long id);
    /**
     * 根据ID与状态得到分录规则
     * @param id
     * @param status
     * @return
     */
    public PojoSubjectRuleConfigure getConfigure(long id,RuleStatusType status);
    
    /**
     * 新增分录规则配置
     * @param subject
     * @return PojoSubjectRuleConfigure
     */
    public PojoSubjectRuleConfigure save(PojoSubjectRuleConfigure subject);
  
    /**
     * 根据ID得到未注销的分录规则
     * @param id
     * @param status
     * @return
     */
    public PojoSubjectRuleConfigure getNeConfigure(long id);
    
    /**
     * 根据交易流水得到分录规则
     * @param id
     * @param status
     * @return
     */
    public List<AccEntryRules> getRulesByTxnInfo(TradeInfo id);
    
        /**
         * 根据条件得到分录规则
         * @param sar分录查询条件
         * @param firset 
         * @param max分页单位
         * @return
         */
    public List<Map<String,Object>> getRuleBySar(SubjectAccountRule sar,Integer firset,Integer max)throws AccBussinessException;
    /**
     * 总条数
     * @param sar
     * @return
     */
    public  long getcount(SubjectAccountRule sar);   
    /**
     * 根据交易类型得到分录规则
     * @param id
     * @param status
     * @return
     */
    public List<PojoSubjectRuleConfigure> getRulesByBusiCode(String busiCode);
}
