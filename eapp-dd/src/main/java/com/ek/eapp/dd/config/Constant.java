package com.ek.eapp.dd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 项目中的常量定义类
 */
@Component
public class Constant {

    /**
     * 企业corpid, 需要修改成开发者所在企业
     */
//    public static final String CORP_ID = "dingbcb401623a369ad835c2f4657eb6378f";
    public static String CORP_ID;
    /**
     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
     */
//    public static final String APPKEY = "dinggf5vug8zwalp4ogx";
    public static String APPKEY;
    /**
     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
     */
//    public static String APPSECRET = "PXflo4SKaLjpGe_x4TfTOD61PYiX4YOvxTxZc6kZ89mfyMiA07ggcQXTvvFaUqQD";
    public static String APPSECRET;

    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
//    public static String ENCODING_AES_KEY = "46dedf63a4594f11bafed59fe107b581bcbb800ba05";
    public static String ENCODING_AES_KEY;

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "12345"
     */
//    public static String AES_TOKEN = "12345";
    public static String AES_TOKEN;

    /**
     * 应用的agentdId，登录开发者后台可查看
     */
//    public static Long AGENT_ID = 286153387L;
    public static Long AGENT_ID;

    /**
     * 回调host
     */
//    public static final String CALLBACK_URL_HOST = "http://edikai.vaiwan.com";
//    public static String CALLBACK_URL_HOST = "http://49.235.132.193";
    public static String CALLBACK_URL_HOST;

    @Value("${dd.eapp.corp_id}")
    public void setCorpId(String CORP_ID) {
        Constant.CORP_ID = CORP_ID;
    }
    @Value("${dd.eapp.app_key}")
    public void setAPPKEY(String APPKEY) {
        Constant.APPKEY = APPKEY;
    }
    @Value("${dd.eapp.app_secret}")
    public void setAPPSECRET(String APPSECRET) {
        Constant.APPSECRET = APPSECRET;
    }
    @Value("${dd.eapp.encoding_aes_key}")
    public void setEncodingAesKey(String encodingAesKey) {
        Constant.ENCODING_AES_KEY = encodingAesKey;
    }
    @Value("${dd.eapp.aes_token}")
    public void setAesToken(String aesToken) {
        Constant.AES_TOKEN = aesToken;
    }
    @Value("${dd.eapp.agent_id}")
    public void setAgentId(Long agentId) {
//        Constant.AGENT_ID = Long.parseLong(agentId);
        Constant.AGENT_ID = agentId;
    }

    @Value("${dd.eapp.callback_url_host}")
    public void setCallbackUrlHost(String callbackUrlHost) {
        Constant.CALLBACK_URL_HOST = callbackUrlHost;
    }

}
