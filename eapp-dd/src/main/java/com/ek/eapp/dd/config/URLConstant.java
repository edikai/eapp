package com.ek.eapp.dd.config;

/**
 * 钉钉接口URL
 */

public class URLConstant {
    /**
     * 钉钉网关gettoken地址
     */
    public static final String URL_GET_TOKKEN = "https://oapi.dingtalk.com/gettoken";

    /**
     *获取用户在企业内userId的接口URL
     */
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/user/getuserinfo";

    /**
     *获取用户姓名的接口url
     */
    public static final String URL_USER_GET = "https://oapi.dingtalk.com/user/get";

    /**
     * 发起审批实例的接口url
     */
    public static final String URL_PROCESSINSTANCE_START = "https://oapi.dingtalk.com/topapi/processinstance/create";

    /**
     * 获取审批实例的接口url
     */
    public static final String URL_PROCESSINSTANCE_GET = "https://oapi.dingtalk.com/topapi/processinstance/get";

    /**
     * 发送企业通知消息的接口url
     */
    public static final String MESSAGE_ASYNCSEND = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";

    /**
     * 获取企业回调接口url
     */
    public static final String CALLBACK_GET = "https://oapi.dingtalk.com/call_back/get_call_back";

    /**
     * 删除企业回调接口url
     */
    public static final String CALLBACK_DELETE = "https://oapi.dingtalk.com/call_back/delete_call_back";

    /**
     * 注册企业回调接口url
     */
    public static final String CALLBACK_REGISTER = "https://oapi.dingtalk.com/call_back/register_call_back";

}
