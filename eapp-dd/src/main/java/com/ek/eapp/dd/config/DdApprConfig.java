package com.ek.eapp.dd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: DdApprConfig
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-09
 * @Version: V2.0
 **/
@Configuration
public class DdApprConfig {

    /**
     * 审批模板唯一标识，可以在审批管理后台找到
     */
    public static String WCMT_PROCESS_CODE;

    @Value(value = "${dd.eapp.appr.wcmt_process_code}")
    public void setWcmtProcessCode(String wcmtProcessCode) {
        WCMT_PROCESS_CODE = wcmtProcessCode;
    }
}
