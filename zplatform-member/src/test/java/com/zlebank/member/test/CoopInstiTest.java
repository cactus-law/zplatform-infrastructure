package com.zlebank.member.test;

import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.member.bean.enums.TerminalAccessType;
import com.zlebank.zplatform.member.exception.CoopInstiException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.ICoopInstiService;

public class CoopInstiTest {
    
    private ApplicationContext context;
    private String instiName;
     
    public void prepare(){
        context = ApplicationContextUtil.get();
        instiName = "测试机构"+String.valueOf(new Random().nextInt(100));
    }
    
    @Test
    @Ignore
    public void testCreate(){
        ICoopInstiService coopInstiService = (ICoopInstiService)context.getBean("");
        try {
            coopInstiService.createCoopInsti(instiName);
        } catch (CoopInstiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Test
    @Ignore
    public void testGetInstiMK(){
        ApplicationContext context = ApplicationContextUtil.get();
        ICoopInstiService coopInstiService = (ICoopInstiService)context.getBean("");
        coopInstiService.getCoopInstiMK(instiName, TerminalAccessType.WIRELESS);
    }
    
    @Test
    public void testExceptionDesc(){
        MemberBussinessException m = new MemberBussinessException("EMS020001");
        System.out.println(m.getMessage());
        m = new MemberBussinessException("EASAC0001","1232131231321");
        System.out.println(m.getMessage());
    }
}
