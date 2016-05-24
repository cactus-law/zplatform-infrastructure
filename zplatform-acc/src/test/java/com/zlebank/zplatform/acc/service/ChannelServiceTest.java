package com.zlebank.zplatform.acc.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.ApplicationContextUtil;
import com.zlebank.zplatform.acc.bean.ChannelBean;
import com.zlebank.zplatform.acc.exception.InvalidChannelData;
import com.zlebank.zplatform.acc.exception.SaveChannelDataException;

/**
 * 
 * 通道测试类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月12日 下午5:04:10
 * @since
 */

public class ChannelServiceTest {
    private ApplicationContext context = ApplicationContextUtil.get();
    ChannelService channelService;
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("AccountContextTest.xml");
// 查看加载类的情况
//        for (String str : context.getBeanDefinitionNames())
//            System.out.println(str);
        channelService = (ChannelService) context.getBean("channelServiceImpl");
    }  
    
    @Test
    @Ignore
    public void addChannelTest() {
        ChannelBean channel = new ChannelBean();
        channel.setChnlcode("99000002");
        channel.setChnlname("test");
        try {
            channelService.addChannel(channel );
        } catch (InvalidChannelData e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        } catch (SaveChannelDataException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
