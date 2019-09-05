package com.ek.eapp.dd.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.ek.eapp.dd.config.Constant;
import com.ek.eapp.dd.config.URLConstant;
import com.ek.eapp.dd.model.ProcessInstanceInputVO;
import com.ek.eapp.util.R;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ProcessInstanceUtil
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-05
 * @Version: V2.0
 **/
@Component
public class ProcessInstanceUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 发起钉钉审批流程
     * @param processInstance
     * @return R {code: 200, id: ProcessInstanceId}
     * @throws ApiException
     */
    public R startProcessInstance(ProcessInstanceInputVO processInstance) throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_START);
        OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
        request.setProcessCode(Constant.PROCESS_CODE);

        request.setFormComponentValues(processInstance.generateForms());

        /**
         * 如果想复用审批固定流程，使用或签会签的话，可以不传审批人，具体请参考文档： https://open-doc.dingtalk.com/microapp/serverapi2/ebkwx8
         * 本次quickstart，演示不传审批人的场景
         */
//        request.setApprovers(processInstance.getOriginatorUserId());
        request.setOriginatorUserId(processInstance.getOriginatorUserId());
        request.setDeptId(processInstance.getDeptId());
        request.setCcList(processInstance.getOriginatorUserId());
        request.setCcPosition("FINISH");

        OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());

        if (Long.parseLong(response.getErrorCode()) != 0) {
            logger.error("Error to startProcessInstance, ErrorCode={}, ErrorMsg={}", response.getErrorCode(), response.getMsg());
            return R.error(Integer.parseInt(response.getErrorCode()), response.getMsg());
        }

        return R.ok().put("id", response.getProcessInstanceId());
    }
}
