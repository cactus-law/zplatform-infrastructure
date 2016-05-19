package com.zlebank.zlpatform.acc2.openAcct;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.ApplicationContextUtil;
import com.zlebank.zplatform.acc.bean.ChannelBean;
import com.zlebank.zplatform.acc.exception.InvalidChannelData;
import com.zlebank.zplatform.acc.exception.SaveChannelDataException;
import com.zlebank.zplatform.acc.service.ChannelService;
import com.zlebank.zplatform.commons.test.RandomUtil;

/**
 * 
 * 通道测试类
 *
 * @author Luxiaoshuai
 * @version
 * @date 2016年1月12日 下午5:04:10
 * @since
 */

public class AddChannelTest {
    private ApplicationContext context = ApplicationContextUtil.get();
    ChannelService channelService;
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("AccountContextTest.xml");
        channelService = (ChannelService) context.getBean("channelServiceImpl");
    }  
    
    public void addChannelTest() {
        ChannelBean channel = new ChannelBean();
        String chnlcode = RandomUtil.randomNumber(8);
        String chnlname = RandomUtil.randomCharacters("ch-test", 7);
        channel.setChnlcode(chnlcode);
        channel.setChnlname(chnlname);
        try {
            channelService.addChannel(channel);
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
