package com.ek.eapp.dd.config;

import java.util.UUID;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 企业corpid, 需要修改成开发者所在企业
     */
    public static final String CORP_ID = "dingbcb401623a369ad835c2f4657eb6378f";
    /**
     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPKEY = "dinggf5vug8zwalp4ogx";
    /**
     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPSECRET = "PXflo4SKaLjpGe_x4TfTOD61PYiX4YOvxTxZc6kZ89mfyMiA07ggcQXTvvFaUqQD";

    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
    public static final String ENCODING_AES_KEY = "46dedf63a4594f11bafed59fe107b581bcbb800ba05";

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "12345"
     */
    public static final String TOKEN = "12345";

    /**
     * 应用的agentdId，登录开发者后台可查看
     */
    public static final Long AGENTID = 286153387L;

    /**
     * 审批模板唯一标识，可以在审批管理后台找到
     */
    public static final String PROCESS_CODE = "PROC-AA8A19B7-1FB0-4714-A750-BE548EB89BFA";

    /**
     * 回调host
     */
    public static final String CALLBACK_URL_HOST = "http://edikai.vaiwan.com";

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 11);
        System.out.println(uuid);
        System.out.println("46dedf63a4594f11bafed59fe107b581bcbb800ba05".length());
    }
}
