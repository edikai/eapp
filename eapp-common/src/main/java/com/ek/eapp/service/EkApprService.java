package com.ek.eapp.service;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName: EkApprService
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-06
 * @Version: V2.0
 **/

public interface EkApprService {

    String[] necessaryParams = new String[]{"tableName", "statusColumnName", "statusColumnValue", "matchColumnName", "matchColumnValue"};

    default boolean checkUpdateStatusParams(Map<String, Object> params) {
        final boolean[] bl = {true};

        Arrays.asList(necessaryParams).forEach(key -> bl[0] = bl[0] && params.containsKey(key));

        return bl[0];
    }

    int updateStatus(Map<String, Object> params);

}
