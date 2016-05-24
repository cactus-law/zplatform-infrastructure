/* 
 * SubjectRuleTest.java  
 * 
 * version TODO
 *
 * 2015年8月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;
import static org.junit.Assert.assertEquals;

import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.SubjectRuleService;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月31日 下午8:24:11
 * @since 
 */
public class SubjectRuleTest {

    private  static SubjectRuleService subjectRuleService;
   
    
    
    public static void getSubject() {
        subjectRuleService=(SubjectRuleService)ApplicationContextUtil.get().getBean("subjectRuleServiceImpl");
    }
    

    public void testQueryPaged() {
//        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.zlebank.zplatform.acc.service.impl.SubjectRuleServiceImpl#addSubjectRule(com.zlebank.zplatform.acc.bean.SubjectRule, long)}.
     */
     
    public void testAddSubjectRule() {
        SubjectRule ruleVo=new SubjectRule();
        ruleVo.setChannelCode("k0005");
        ruleVo.setCrdr(CRDRType.CR);
        ruleVo.setEntryAlgorithm("a+b");
      //  ruleVo.setFlag("F0001");
        ruleVo.setIsShow("y");
        ruleVo.setProdductCode("10050108");
        ruleVo.setTxntype("1000");
        ruleVo.setStatus(RuleStatusType.NORMAL);
    try {
        long    id = subjectRuleService.addSubjectRule(ruleVo, 102L);
        SubjectRule sub=    subjectRuleService.subjectRuleByID(id);
        assertEquals(sub.getChannelCode(), "k0005");
    } catch (AccBussinessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
  
    }

    /**
     * Test method for {@link com.zlebank.zplatform.acc.service.impl.SubjectRuleServiceImpl#updateSubjectRule(com.zlebank.zplatform.acc.bean.SubjectRule, long)}.
     */
     
    public void testUpdateSubjectRule() {
        SubjectRule ruleVo=new SubjectRule();
        ruleVo.setId(12L);
        ruleVo.setChannelCode("k0005");
        ruleVo.setCrdr(CRDRType.CR);
        ruleVo.setEntryAlgorithm("a+b");
       // ruleVo.setFlag("F0001");
        ruleVo.setIsShow("y");
        ruleVo.setProdductCode("10050108");
        ruleVo.setTxntype("A1066");
        ruleVo.setStatus(RuleStatusType.NORMAL);
        try {
          boolean isok=  subjectRuleService.updateSubjectRule(ruleVo, 88L);
            assertEquals(true, isok);
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Test method for {@link com.zlebank.zplatform.acc.service.impl.SubjectRuleServiceImpl#stopSubjectRule(java.lang.Long)}.
     */
    
    public void testStopSubjectRule() {
        
        try {
            long id=12L;
          boolean isok=subjectRuleService.stopSubjectRule(id,5L);
          assertEquals(true, isok);
      } catch (AccBussinessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
    }

    /**
     * Test method for {@link com.zlebank.zplatform.acc.service.impl.SubjectRuleServiceImpl#enableSubjectRule(java.lang.Long)}.
     */
     
    public void testEnableSubjectRule() {
        
        try {
            long id=12;
          boolean isok=subjectRuleService.enableSubjectRule(id,5l);
          assertEquals(true, isok);
      } catch (AccBussinessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
    }

    /**
     * Test method for {@link com.zlebank.zplatform.acc.service.impl.SubjectRuleServiceImpl#subjectRoleByID(java.lang.Long)}.
     */
     
    public void testSubjectRoleByID() {
        try {
            SubjectRule ruleVo=subjectRuleService.subjectRuleByID(3l);
            assertEquals( 3l,ruleVo.getId());
            
        } catch (AccBussinessException e) {
         
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link com.zlebank.zplatform.acc.service.impl.SubjectRuleServiceImpl#subjectRuleByID(java.lang.Long, com.zlebank.zplatform.acc.bean.RuleStatusType)}.
     */
     
    public void testSubjectRuleByID() {
        try {
            SubjectRule ruleVo=subjectRuleService.subjectRuleByID(3l,RuleStatusType.LOGOUT);
            assertEquals( 3l,ruleVo.getId());
            
        } catch (AccBussinessException e) {
         
            e.printStackTrace();
        }
    }

}
