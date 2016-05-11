package com.zlebank.zlpatform.acc2.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReader {
    private final static String CONFIG_FILE_PATH = "D:\\project\\证联资本\\新支付平台\\版本控制\\1.5\\subjectVo.xls";
    /**
     * 读取excel
     * 
     * @param y
     * @return
     * @throws BiffException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    protected List<String[]> readExcle(String sheetName) throws BiffException,
            IOException {
        // 创建一个list 用来存储读取的内容
        @SuppressWarnings("rawtypes")
        List list = new ArrayList();
        Workbook rwb = null;
        Cell cell = null;

        // 创建输入流
        InputStream stream = new FileInputStream(CONFIG_FILE_PATH);

        // 获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);

        // 获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(sheetName);

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
}
