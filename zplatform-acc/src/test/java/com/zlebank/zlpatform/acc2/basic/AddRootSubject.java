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
package com.zlebank.zlpatform.acc2.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
class AddRootSubject {
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
     * 读取excel
     * 
     * @param y
     * @return
     * @throws BiffException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private List<String[]> readExcle(String y) throws BiffException,
            IOException {
        // 创建一个list 用来存储读取的内容
        @SuppressWarnings("rawtypes")
        List list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;

        // 创建输入流
        InputStream stream = new FileInputStream("d:\\subjectVo.xls");

        // 获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);

        // 获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(y);

        // 行数(表头的目录不需要，从1开始)
        for (int i = 1; i < sheet.getRows(); i++) {

            // 创建一个数组 用来存储每一列的值
            String[] str = new String[sheet.getColumns()];

            // 列数
            for (int j = 0; j < sheet.getColumns(); j++) {

                // 获取第i行，第j列的值
                cell = sheet.getCell(j, i);

                str[j] = cell.getContents();

            }
            // 把刚获取的列存入list
            list.add(str);

        }
        return list;
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
