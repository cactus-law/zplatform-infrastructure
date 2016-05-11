package com.zlebank.zlpatform.acc2.tools;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import org.junit.Before;
import org.junit.Test;

import com.zlebank.zlpatform.acc2.util.ApplicationContextAbled;
import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.enums.AccCodeType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.SubjectRuleService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;

public class ImporttEntryRule extends ApplicationContextAbled {
    private ExcelReader excelReader = new ExcelReader();

    private SubjectRuleService subjectRuleService;

    @Before
    public void init() {
        subjectRuleService = context.getBean(SubjectRuleService.class);
    }

    @Test
    public void importt() {
        try {
            List<String[]> list = excelReader.readExcle("新增规则");

            for (int i = 0; i < list.size(); i++) {
                String[] str = (String[]) list.get(i);
                SubjectRule rule = new SubjectRule();
                int j = 0;
                rule.setTxntype(str[j]);
                rule.setAcctCode(str[j++]);
                rule.setAcctCodeType(AccCodeType.fromValue(str[j++]));
                rule.setCrdr(CRDRType.fromValue(str[j++]));
                rule.setEntryAlgorithm(str[j++]);
                rule.setStatus(RuleStatusType.fromValue(str[j++]));
                rule.setSyncFlag(str[j++]);
                rule.setEntryEvent(EntryEvent.fromValue(str[j++]));
                subjectRuleService.addSubjectRule(rule, 9999999999L);
            }
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
