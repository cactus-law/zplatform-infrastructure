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
package com.zlebank.zlpatform.acc2.tools;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import org.junit.Test;

import com.zlebank.zplatform.acc.ApplicationContextUtil;
import com.zlebank.zplatform.acc.bean.Subject;
import com.zlebank.zplatform.acc.bean.enums.AcctElementType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.exception.AbstractAccException;
import com.zlebank.zplatform.acc.service.SubjectService;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * test add root subject
 *
 * @author yangying
 * @version
 * @date 2015年9月1日 上午11:25:31
 * @since
 */
class AddRootSubject extends ExcelReader{
    private static SubjectService subjectservice; 

    public static void getSubject() {
        subjectservice = (SubjectService) ApplicationContextUtil.get().getBean(
                "subjectServiceImpl");
    }

    

    /**
     * 新增科目
     */
    @Test
    public void testAddSubject() {
        try {
            getSubject();
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
     * 新增科目
     * 
     * @throws BiffException
     * @throws IOException
     */
    private void addSubject() throws BiffException, IOException {
        List<String[]> list = this.readExcle("新增科目");
        // 会计要素（1:资产 2:负债 3:共同 4:所有权益 5:成本 6:损益）

        for (int i = 0; i < list.size(); i++) {
            String[] str = (String[]) list.get(i);
            Subject sub = new Subject();
            String element = "";
            String elem = str[1].replaceAll(" ", "");
            if ("资产".equals(elem)) {
                element = "1";
            } else if ("负债".equals(elem)) {
                element = "2";
            } else if ("共同类".equals(elem)) {
                element = "3";
            } else if ("所有者权益".equals(elem)) {
                element = "4";
            } else if ("成本".equals(elem)) {
                element = "5";
            } else if ("损益".equals(elem)) {
                element = "6";
            }
            String type = ("借").equals(str[5]) ? "D" : "C";
            sub.setAcctElement(AcctElementType.fromValue(element));
            sub.setCrdr(CRDRType.fromValue(type));
            sub.setAcctCode(str[2]);
            sub.setAcctCodeName(str[3]);
            sub.setNotes(str[4]);
            if (StringUtil.isNotEmpty(str[6])) {
                Subject s = new Subject();
                s.setAcctCode(str[6]);
                sub.setParentSubject(s);
            }
            try {
                if (StringUtil.isEmpty(str[0])) {
                    subjectservice.addSubject(sub, 110L);
                }
            } catch (AbstractAccException e) {
                System.out.println(e);
            }
        }
    }
}
