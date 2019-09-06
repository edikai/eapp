package com.ek.eapp.dd.apiutil;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackGetCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.response.OapiCallBackGetCallBackResponse;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.ek.eapp.dd.config.Constant;
import com.ek.eapp.dd.config.URLConstant;
import com.taobao.api.ApiException;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: CollbackUtil
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-06
 * @Version: V2.0
 **/
@Component
@Data
public class CallbackUtil {

    @Value("${dd.callback.callable:false}")
    private boolean callable;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void startDdCallback() throws ApiException {
        if (!callable){
            logger.warn("钉钉回调注册未开启.");
            return;
        }
        String accessToken = AccessTokenUtil.getToken();

//        DingTalkClient  client = new DefaultDingTalkClient(URLConstant.CALLBACK_GET);
//        OapiCallBackGetCallBackRequest getRequest = new OapiCallBackGetCallBackRequest();
//        getRequest.setHttpMethod("GET");
//        OapiCallBackGetCallBackResponse getResponse = client.execute(getRequest, accessToken);

//        if (getResponse.getErrcode() == 0) {
//            List<String> cbTags = getResponse.getCallBackTag();
//            logger.info("已注册回调列表数：{}", cbTags.size());
//            if (cbTags.size() > 0) {
//                // 先删除企业已有的回调
//                client = new DefaultDingTalkClient(URLConstant.CALLBACK_DELETE);
//                OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
//                request.setHttpMethod("GET");
//                client.execute(request, accessToken);
//
//                logger.info("删除已注册回调成功.");
//            }
//        }

        // 先删除企业已有的回调
        DingTalkClient  client = new DefaultDingTalkClient(URLConstant.CALLBACK_DELETE);
        OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
        request.setHttpMethod("GET");
        client.execute(request, accessToken);

        logger.info("删除已注册回调成功.");

        // 重新为企业注册回调
        client = new DefaultDingTalkClient(URLConstant.CALLBACK_REGISTER);
        OapiCallBackRegisterCallBackRequest registerRequest = new OapiCallBackRegisterCallBackRequest();
        registerRequest.setUrl(Constant.CALLBACK_URL_HOST + "/api/callback");
        registerRequest.setAesKey(Constant.ENCODING_AES_KEY);
        registerRequest.setToken(Constant.TOKEN);
        registerRequest.setCallBackTag(Arrays.asList("bpms_instance_change", "bpms_task_change"));
        OapiCallBackRegisterCallBackResponse registerResponse = client.execute(registerRequest,AccessTokenUtil.getToken());
        if (registerResponse.isSuccess()) {
            logger.info("钉钉回调注册成功.");
        }
    }
}
