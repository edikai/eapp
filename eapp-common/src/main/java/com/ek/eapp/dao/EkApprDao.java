package com.ek.eapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: EkApprDao
 * @Description: 更新审批相关状态
 * @Author: qin_hqing
 * @Date: 2019-09-06
 * @Version: V2.0
 **/
@Component
@Mapper
public interface EkApprDao {

    int updateStatus(Map<String, Object> params);

}
