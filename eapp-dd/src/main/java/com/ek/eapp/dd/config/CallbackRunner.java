package com.ek.eapp.dd.config;

import com.ek.eapp.dd.apiutil.CallbackUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CallbackRunner
 * @Description: 随服务启动钉钉回调
 * @Author: qin_hqing
 * @Date: 2019-09-06
 * @Version: V2.0
 **/
@Component
@Order(1)
public class CallbackRunner implements ApplicationRunner {

    @Autowired
    private CallbackUtil callbackUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        callbackUtil.startDdCallback();
    }
}
