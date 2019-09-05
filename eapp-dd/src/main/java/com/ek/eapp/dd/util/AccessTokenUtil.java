package com.ek.eapp.dd.util;

import com.ek.eapp.dd.config.Constant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.ek.eapp.util.EkRedisUtil;
import com.ek.eapp.util.SpringUtil;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ek.eapp.dd.config.URLConstant.URL_GET_TOKKEN;

/**
 * 获取access_token工具类
 */
public class AccessTokenUtil {
    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

    private static EkRedisUtil redisUtil = (EkRedisUtil) SpringUtil.getBean("ekRedisUtil");
    private static final String DD_API_ACCESS_TOKEN_KEY = "dd:api:accessToken";

    public static String getToken() throws RuntimeException {
        try {
            boolean isExistToken = redisUtil.containsKey(DD_API_ACCESS_TOKEN_KEY);
            if (isExistToken){
                return redisUtil.get(DD_API_ACCESS_TOKEN_KEY);
            }

            bizLogger.debug("{} is not initialized, initialize it now.", DD_API_ACCESS_TOKEN_KEY);

            DefaultDingTalkClient client = new DefaultDingTalkClient(URL_GET_TOKKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(Constant.APPKEY);
            request.setAppsecret(Constant.APPSECRET);
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            String accessToken = response.getAccessToken();

            redisUtil.set(DD_API_ACCESS_TOKEN_KEY, accessToken);
            return accessToken;
        } catch (ApiException e) {
            bizLogger.error("getAccessToken failed", e);
            throw new RuntimeException();
        }

    }
}
