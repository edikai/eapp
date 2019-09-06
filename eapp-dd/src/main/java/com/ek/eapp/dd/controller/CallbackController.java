package com.ek.eapp.dd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.dingtalk.oapi.lib.aes.Utils;
import com.ek.eapp.constant.ApprConstant;
import com.ek.eapp.constant.EkConstant;
import com.ek.eapp.dd.config.Constant;
import com.ek.eapp.dd.config.URLConstant;
import com.ek.eapp.dd.apiutil.MessageUtil;
import com.ek.eapp.service.EkApprService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * E应用回调信息处理
 */
@RestController
@RequestMapping("/api")
public class CallbackController {

    private static final Logger bizLogger = LoggerFactory.getLogger("BIZ_CALLBACKCONTROLLER");
    private static final Logger mainLogger = LoggerFactory.getLogger(CallbackController.class);

    /**
     * 创建套件后，验证回调URL创建有效事件（第一次保存回调URL之前）
     */
    private static final String CHECK_URL = "check_url";

    /**
     * 审批任务回调
     */
    private static final String BPMS_TASK_CHANGE = "bpms_task_change";

    /**
     * 审批实例回调
     */
    private static final String BPMS_INSTANCE_CHANGE = "bpms_instance_change";

    /**
     * 相应钉钉回调时的值
     */
    private static final String CALLBACK_RESPONSE_SUCCESS = "success";

    @Autowired
    private EkApprService apprService;

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> callback(@RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "timestamp", required = false) String timestamp,
                                        @RequestParam(value = "nonce", required = false) String nonce,
                                        @RequestBody(required = false) JSONObject json) {
        String params = " signature:"+signature + " timestamp:"+timestamp +" nonce:"+nonce+" json:"+json;
        try {
            DingTalkEncryptor dingTalkEncryptor = new DingTalkEncryptor(Constant.TOKEN, Constant.ENCODING_AES_KEY,
                Constant.CORP_ID);

            //从post请求的body中获取回调信息的加密数据进行解密处理
            String encryptMsg = json.getString("encrypt");
            String plainText = dingTalkEncryptor.getDecryptMsg(signature, timestamp, nonce, encryptMsg);
            JSONObject obj = JSON.parseObject(plainText);

            //根据回调数据类型做不同的业务处理
            String eventType = obj.getString("EventType");
            if (BPMS_TASK_CHANGE.equals(eventType)) {
                bizLogger.info("收到审批任务进度更新: " + plainText);
                //todo: 实现审批的业务逻辑，如发消息
            } else if (BPMS_INSTANCE_CHANGE.equals(eventType)) {
                //todo: 实现审批的业务逻辑，如发消息
                bizLogger.info("收到审批实例状态更新: " + plainText);
                String processInstanceId = obj.getString("processInstanceId");
                if (obj.containsKey("result")){
                    String ddRes = obj.getString("result");

                    Map<String, Object> apprParams = new HashMap<>();
                    apprParams.put(ApprConstant.APPR_TABLE_NAME, ApprConstant.TABLE_DD_MT_WO);
                    apprParams.put(ApprConstant.APPR_STATUS_COLUMN_NAME, ApprConstant.TABLE_DD_MT_WO_STATUS_COL);

                    if (EkConstant.DD_STATUS_AGREE.equals(ddRes)) {
                        MessageUtil.sendMessageToOriginator(processInstanceId);
                        apprParams.put(ApprConstant.APPR_STATUS_COLUMN_VALUE, EkConstant.EappStaus.Agree.getStatus());
                    }else if (EkConstant.DD_STATUS_REFUSE.equals(ddRes)){
                        apprParams.put(ApprConstant.APPR_STATUS_COLUMN_VALUE, EkConstant.EappStaus.Refuse.getStatus());
                    }
                    apprParams.put(ApprConstant.APPR_MATCH_COLUMN_NAME, ApprConstant.TABLE_DD_MT_WO_MCH_COL);
                    apprParams.put(ApprConstant.APPR_MATCH_COLUMN_VALUE, processInstanceId);

                    int updateRes = apprService.updateStatus(apprParams);

                    if (updateRes > 0) {
                        bizLogger.info("表{}中字段{}为{}的状态更新成功-->{}.", ApprConstant.TABLE_DD_MT_WO, ApprConstant.TABLE_DD_MT_WO_MCH_COL, processInstanceId, ddRes);
                    }else {
                        bizLogger.info("表{}中字段{}为{}的状态更新失败-->{}.", ApprConstant.TABLE_DD_MT_WO, ApprConstant.TABLE_DD_MT_WO_MCH_COL, processInstanceId, ddRes);
                    }
                }

            } else {
                // 其他类型事件处理
            }

            // 返回success的加密信息表示回调处理成功
            Map<String, String> map = dingTalkEncryptor.getEncryptedMap(CALLBACK_RESPONSE_SUCCESS, System.currentTimeMillis(), Utils.getRandomStr(8));
//            map.forEach((key, val) -> {
//                bizLogger.info("key={}, value={}", key, val);
//            });
            return map;
        } catch (Exception e) {
            //失败的情况，应用的开发者应该通过告警感知，并干预修复
            mainLogger.error("process callback failed！"+params,e);
            return null;
        }

    }

    public static void main(String[] args) throws Exception{
        String accessToken = "12cf728d438030cf871915f75688bfc1";
        // 先删除企业已有的回调
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.CALLBACK_DELETE);
        OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
        request.setHttpMethod("GET");
        client.execute(request, accessToken);

        // 重新为企业注册回调
        client = new DefaultDingTalkClient(URLConstant.CALLBACK_REGISTER);
        OapiCallBackRegisterCallBackRequest registerRequest = new OapiCallBackRegisterCallBackRequest();
        registerRequest.setUrl(Constant.CALLBACK_URL_HOST + "/api/callback");
        registerRequest.setAesKey(Constant.ENCODING_AES_KEY);
        registerRequest.setToken(Constant.TOKEN);
        registerRequest.setCallBackTag(Arrays.asList("bpms_instance_change", "bpms_task_change"));
        OapiCallBackRegisterCallBackResponse registerResponse = client.execute(registerRequest, accessToken);
        if (registerResponse.isSuccess()) {
            System.out.println("回调注册成功了！！！");
        }
    }
}
