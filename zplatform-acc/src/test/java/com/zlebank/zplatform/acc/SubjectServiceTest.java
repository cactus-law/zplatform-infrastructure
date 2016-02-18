/* 
 * SubjectServiceTest.java  
 * 
 * version TODO
 *
 * 2015年8月28日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import static org.junit.Assert.assertEquals;

import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.SubjectQuery;
import com.zlebank.zplatform.acc.bean.enums.AcctElementType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.SubjectService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年8月28日 下午4:44:28
 * @since 
 */
public class SubjectServiceTest {

 private  static SubjectService subjectservice;
    
     
    public static void getSubject() {
    subjectservice=(SubjectService)ApplicationContextUtil.get().getBean("subjectServiceImpl");
           
    }
   
     
    public void testGetTotal() {
       
    }

   
     
    public void testAddSubject()  {
        try {
        Subject subVo=new Subject();

        subVo.setAcctCode("1889");
        subVo.setAcctCodeName("test根科目");
        subVo.setAcctElement(AcctElementType.ASSETS);
        subVo.setCrdr(CRDRType.CR);
        Subject su=new Subject();
        su.setAcctCode("195501");
        su.setAcctCodeName("test中间科目");
        su.setParentSubject(subVo);
       
       
          String code=  subjectservice.addSubject(subVo, 20);
            assertEquals("9999", code);
            
        } catch (AbstractAccException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

   
     
    public void testUpdateSubject() {
        Subject subVo=new Subject();
        subVo.setId(119L);
        subVo.setAcctCode("6987");
        subVo.setAcctCodeName("test根科目");
        subVo.setAcctElement(AcctElementType.ASSETS);
        subVo.setCrdr(CRDRType.CR);
        boolean isok;
        try {
            isok = subjectservice.updateSubject(subVo, 20);
            assertEquals(true, isok);
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   
    }

    
     
    public void testStopSubject() {
        try {
        Subject subVo=new Subject();
        subVo.setId(119L);
        subVo.setAcctCode("6987");
        subVo.setAcctCodeName("test根科目");
        subVo.setAcctElement(AcctElementType.ASSETS);
        subVo.setCrdr(CRDRType.CR);
        boolean isok=subjectservice.stopSubject(119L);
        assertEquals(true, isok);
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

   
     
    public void testSubjectByID() {
        SubjectQuery pojo;
        try {
            pojo = subjectservice.subjectByID(175L);
            String code =pojo.getAcctCode();
            assertEquals("100201", code);
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            System.out.println(e);
        }
     
    }

     
    public void testSubjectByCode() {
        SubjectQuery pojo;
        try {
            pojo = subjectservice.subjectByCode("9999");
            String code =pojo.getAcctCode();
            assertEquals("9999", code);
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
    }



}
