package com.taobao.ticket;

import com.alibaba.dxp.hsfext.annotation.HSFConsumer;
import com.taobao.hsf.hsfunit.util.ServiceUtil;
import com.taobao.uic.common.domain.BaseUserDO;
import com.taobao.uic.common.service.userinfo.UicReadService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @auther: zheshan
 * Time: 13-10-7 下午5:54
 */
@Service
public class UicReadDecorator {
    @HSFConsumer
    private UicReadService uicReadService;

    @HSFConsumer(interfaceName = "uicReadService2")
    private UicReadService uicReadService2;
    //支持使用xml配置文件的方式来注入
    @HSFConsumer(interfaceName = "uicReadServiceXML")
    private UicReadService uicReadServiceXML;

    //使用xml配置文件的方式来注入
    @Resource(name = "uicReadServiceXML")
    private UicReadService uicReadServiceXML2;
    public BaseUserDO getUser(long userId){
        //不是必须，因为运行很快，所以要在configServer推地址下来前检测地址是否推下來，可以自己sleep(500)
        ServiceUtil.waitServiceReady(uicReadService);
        BaseUserDO user = uicReadService.getBaseUserByUserId(userId, "detail")
                .getModule();
        ServiceUtil.waitServiceReady(uicReadService2);
        try {
            uicReadService2.getBaseUserByUserId(userId, "detail")
                    .getModule();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    @PostConstruct
    public void init(){
        System.out.println("uicReadService from @HSFConsumer:"+uicReadService+","+uicReadService2);
        System.out.println("uicReadService from XML configuration:"+ uicReadServiceXML+","+uicReadServiceXML2);
    }
}
