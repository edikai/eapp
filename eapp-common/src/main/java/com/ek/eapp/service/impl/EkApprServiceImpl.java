package com.ek.eapp.service.impl;

import com.ek.eapp.dao.EkApprDao;
import com.ek.eapp.service.EkApprService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: EkApprServiceImpl
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-09-06
 * @Version: V2.0
 **/
@Service
public class EkApprServiceImpl implements EkApprService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EkApprDao apprDao;

    @Override
    public int updateStatus(Map<String, Object> params) {
        if (!this.checkUpdateStatusParams(params)){
            logger.error("缺少参数,必传参数{}.", necessaryParams.toString());
            return 0;
        }

        return apprDao.updateStatus(params);
    }
}
