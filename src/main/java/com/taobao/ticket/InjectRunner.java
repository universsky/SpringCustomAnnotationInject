package com.taobao.ticket;

import com.taobao.hsf.hsfunit.HSFEasyStarter;
import com.taobao.hsf.hsfunit.util.ServiceUtil;
import com.taobao.ticket.UicReadDecorator;
import com.taobao.uic.common.domain.BaseUserDO;
import com.taobao.uic.common.service.userinfo.UicReadService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class InjectRunner {
    public static void main(String[] args) {
        HSFEasyStarter.start("D:\\env\\hsf", "1.4.9.6");//在用到consumer bean前启动hsf
        String springResourcePath = "InjectRunner-spring-applicationContext.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                springResourcePath);


        UicReadDecorator reader = ctx.getBean(UicReadDecorator.class);
        BaseUserDO user = reader.getUser(10000L);
        System.out.println("user[id:10000L] nick:" + user);


        /*UicReadService uicReadService = (UicReadService) ctx
                .getBean("uicReadServiceXML");

        ServiceUtil.waitServiceReady(uicReadService);//不是必须，因为运行很快，所以要在configServer推地址下来前检测地址是否推下來，可以自己sleep(500)
        BaseUserDO user = uicReadService.getBaseUserByUserId(10000L, "detail")
                .getModule();
        System.out.println("user[id:10000L] nick:" + user.getNick());*/
    }
}
