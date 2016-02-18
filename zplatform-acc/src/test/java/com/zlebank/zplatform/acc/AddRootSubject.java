/* 
 * AddRootSubject.java  
 * 
 * version TODO
 *
 * 2015年9月1日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.enums.AcctElementType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.bean.enums.Usage;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.mock.TestMemberBean;
import com.zlebank.zplatform.acc.service.AccountService;
import com.zlebank.zplatform.acc.service.BusiAcctService;
import com.zlebank.zplatform.acc.service.SubjectRuleService;
import com.zlebank.zplatform.acc.service.SubjectService;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月1日 上午11:25:31
 * @since 
 */
public class AddRootSubject {
    private  static SubjectRuleService subjectRuleService;
    private static   SubjectService subjectservice;
     private   static BusiAcctService busiAcctService;
     private static AccountService account;
     
    public static void getSubject() {
    subjectservice=(SubjectService)ApplicationContextUtil.get().getBean("subjectServiceImpl");
    busiAcctService = (BusiAcctService) ApplicationContextUtil.get().
            getBean("busiAcctServiceImpl");
    subjectRuleService=(SubjectRuleService)ApplicationContextUtil.get().getBean("subjectRuleServiceImpl");
    account=(AccountService)ApplicationContextUtil.get().getBean("accountServiceImpl");
    }
   
    
    /**
     * 新增账户
     * @throws BiffException
     * @throws IOException
     */
     
    public void testAddBusi() throws BiffException, IOException {
        this.addBusiacct();
       
    }
    /**
     * 新增规则
     * @throws BiffException
     * @throws IOException
     */
     
    public void testAddRule() throws BiffException, IOException {
        try {
            this.addRule();
        } catch (AccBussinessException e) {
        System.out.println(e);
        }
       
    }
    
    
    /**
     * 新增科目
     */
     
    public void testAddSubject() {
     try {
        this.addSubject();
    } catch (BiffException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    /**
     * 新增叶子科目
     * @throws IOException 
     * @throws BiffException 
     * @throws AbstractAccException 
     */
     
    public void testAddLeafSub() throws AbstractAccException, BiffException, IOException {
        this.addLeafSubject();
  
    }
    
    
    /**
     * 修改科目
     * @throws IOException 
     * @throws BiffException 
     */
     
    public void testUpdateSubject() throws BiffException, IOException{
        this.UpdateSubject();
        
    }
    /**
     * 修改规则
     * @throws IOException 
     * @throws BiffException 
     * @throws AccBussinessException 
     */
     
    public void testUpdateRule() throws AccBussinessException, BiffException, IOException{
        this.updateRule();
        
    }
    
    /**
     * 读取excel
     * @param y
     * @return
     * @throws BiffException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private List<String[]> readExcle(String y) throws BiffException, IOException{
      //创建一个list 用来存储读取的内容
        @SuppressWarnings("rawtypes")
        List list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;
        
        //创建输入流
        InputStream stream = new FileInputStream("d:\\subjectVo.xls");
        
        //获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);
        
        //获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(y);  
        
       
        //行数(表头的目录不需要，从1开始)
        for(int i=1; i<sheet.getRows(); i++){
         
         //创建一个数组 用来存储每一列的值
         String[] str = new String[sheet.getColumns()];
   
         //列数
         for(int j=0; j<sheet.getColumns(); j++){
        
          //获取第i行，第j列的值
          cell = sheet.getCell(j,i);    
          
          str[j] = cell.getContents();
          
         }
         //把刚获取的列存入list
         list.add(str);
        
        
    }
        return list;
        }
        /**
         * 新增叶子科目
         * @throws BiffException
         * @throws IOException
         * @throws AbstractAccException 
         */
       private   void addLeafSubject() throws  AbstractAccException, BiffException, IOException{
          List<String[]> list= this.readExcle("新增叶子科目");
         //会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）
        
          
         for(int i=0;i<list.size();i++){
          String[] str = (String[])list.get(i);
         long parentSubjectId=Long.valueOf(str[0]);
         String acctCode=str[1];
         String acctName=str[2];
         long userId=Long.valueOf(str[3]);
         account.addAcct(parentSubjectId, acctCode, acctName, userId);
              }
        } 
         
          
        
         
     
           /**
            * 新增科目
            * @throws BiffException
            * @throws IOException
            */
       private   void addSubject() throws BiffException, IOException{
           List<String[]> list= this.readExcle("新增科目");
          //会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）
         
           
          for(int i=0;i<list.size();i++){
           String[] str = (String[])list.get(i);
           Subject sub=new Subject();
           String element="";
           String elem=str[1].replaceAll(" ","");
           if("资产".equals(elem)){
               element="1";
           }
           else if("负债".equals(elem)){
               element="2";
           }else if("共同类".equals(elem)){
               element="3";
           }else if("所有者权益".equals(elem)){
               element="4";
           }else if("成本".equals(elem)){
               element="5";
           }else if("损益".equals(elem)){
               element="6";
           }
           String type= ("借").equals (str[5]) ? "D" : "C";
           sub.setAcctElement(AcctElementType.fromValue(element));
           sub.setCrdr(CRDRType.fromValue(type));
           sub.setAcctCode(str[2]);
           sub.setAcctCodeName(str[3]);
           sub.setNotes(str[4]);
           if(StringUtil.isNotEmpty(str[6])){
              Subject s= new Subject();
              s.setAcctCode(str[6]);
               sub.setParentSubject(s);
           }
           try {
               if(StringUtil.isEmpty(str[0])){
             subjectservice.addSubject(sub, 110L);
               }
         } catch (AbstractAccException e) {
            System.out.println(e);
         }
          
           
          }
          
        }
       
    /**
     * 修改科目
     * @throws BiffException
     * @throws IOException
     */
    private   void UpdateSubject() throws BiffException, IOException{
        List<String[]> list= this.readExcle("修改科目");
       //会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）
      
        
       for(int i=0;i<list.size();i++){
        String[] str = (String[])list.get(i);
       
        
        Subject sub=new Subject();
        String element="";
        String elem=str[1].replaceAll(" ","");
        if("资产".equals(elem)){
            element="1";
        }
        else if("负债".equals(elem)){
            element="2";
        }else if("共同类".equals(elem)){
            element="3";
        }else if("所有者权益".equals(elem)){
            element="4";
        }else if("成本".equals(elem)){
            element="5";
        }else if("损益".equals(elem)){
            element="6";
        }
        String type= ("借").equals (str[5]) ? "D" : "C";
        sub.setAcctElement(AcctElementType.fromValue(element));
        sub.setCrdr(CRDRType.fromValue(type));
        sub.setAcctCode(str[2]);
        sub.setAcctCodeName(str[3]);
        sub.setNotes(str[4]);
        if(StringUtil.isNotEmpty(str[6])){
           Subject s= new Subject();
           s.setAcctCode(str[6]);
            sub.setParentSubject(s);
        }
        try {
            
                sub.setId(Long.valueOf(str[0]));
                sub.setDac(str[7]);
                subjectservice.updateSubject(sub, 110L);
            
      } catch (AbstractAccException e) {
         System.out.println(e);
      }
       
        
       }
       
     }
  
    
    //新增账户
    private   void addBusiacct() throws BiffException, IOException{
        List<String[]> list= this.readExcle("新增账户");
       //会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）
      
        
       for(int i=0;i<list.size();i++){
        String[] str = (String[])list.get(i);
       
        BusiAcct busiAcct = new BusiAcct();
        TestMemberBean member=new TestMemberBean();
        member.setMemberId(str[0]);
        member.setMemberType(BusinessActorType.fromValue("0"+str[1]));
        member.setMerchName(str[4]);
        busiAcct.setBusiAcctName(str[5]);
     
        busiAcct.setUsage(Usage.fromValue(str[2]));
        try {
            busiAcctService.openBusiAcct(member, busiAcct, Long.valueOf(str[3]));
        } catch (AbstractBusiAcctException e) {
                System.out.println(e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

     }
    
    
    }
    //新增规则
    private  void addRule() throws BiffException, IOException, AccBussinessException{
        List<String[]> list= this.readExcle("新增规则");
        //会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）
       
         
        for(int i=0;i<list.size();i++){
         String[] str = (String[])list.get(i);
        SubjectRule rule=new SubjectRule();
        rule.setTxntype(str[0]);
        rule.setFlag(str[1]);
        rule.setCrdr(CRDRType.fromValue(str[2]));
        rule.setChannelCode(str[3]);
        rule.setProdductCode(str[4]);
        rule.setEntryAlgorithm(str[5]);
        subjectRuleService.addSubjectRule(rule, 100L);
      }
        
    }
    
 /**
  * 修改规则
  * @throws BiffException
  * @throws IOException
  * @throws AccBussinessException
  */
    private  void updateRule() throws BiffException, IOException, AccBussinessException{
        List<String[]> list= this.readExcle("修改规则");
        //会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）
       
         
        for(int i=0;i<list.size();i++){
         String[] str = (String[])list.get(i);
        SubjectRule rule=new SubjectRule();
        rule.setId(Long.valueOf(str[0]));
        rule.setTxntype(str[1]);
        rule.setFlag(str[2]);
        rule.setCrdr(CRDRType.fromValue(str[3]));
        rule.setChannelCode(str[4]);
        rule.setProdductCode(str[5]);
        rule.setEntryAlgorithm(str[6]);
        rule.setStatus(RuleStatusType.fromValue("0"+str[7]));
       
        subjectRuleService.updateSubjectRule(rule, 100L);
      }
        
    }
    
  
}

