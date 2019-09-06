package com.ek.eapp.dd.apiutil;

import com.ek.eapp.dd.config.Constant;
import com.ek.eapp.dd.config.URLConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @TODO 发送钉钉通知消息
 * @author qin_hqing
 * @date 2018/08/28
 */
public class MessageUtil {

    private static final Logger bizLogger = LoggerFactory.getLogger(MessageUtil.class);

    public static void sendMessageToOriginator(String processInstanceId) throws RuntimeException {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_GET);
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(processInstanceId);
            OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken());
            String recieverUserId = response.getProcessInstance().getOriginatorUserid();

            client = new DefaultDingTalkClient(URLConstant.MESSAGE_ASYNCSEND);

            OapiMessageCorpconversationAsyncsendV2Request messageRequest = new OapiMessageCorpconversationAsyncsendV2Request();
            messageRequest.setUseridList(recieverUserId);
            messageRequest.setAgentId(Constant.AGENTID);
            messageRequest.setToAllUser(false);

            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype("text");
            msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
            msg.getText().setContent("您的申请已通过，请悉知.");
            messageRequest.setMsg(msg);

            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(messageRequest,AccessTokenUtil.getToken());
            if (rsp.isSuccess()){
                bizLogger.debug("已发送消息给用户.");
            }
        } catch (ApiException e) {
            bizLogger.error("send message failed", e);
            throw new RuntimeException();
        }
    }
}
