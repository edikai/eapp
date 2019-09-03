package com.ek.eapp.service;

import com.ek.eapp.entity.EkUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IEkUserService
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-05
 * @Version: V2.0
 **/

public interface IEkUserService {

    @Transactional
    int deleteByPrimaryKey(Integer id);

    @Transactional
    int insertSelective(EkUser ekUser);

    EkUser selectByPrimaryKey(Integer id);

    EkUser selectByLoginName(String loginName);

    @Transactional
    int updateByPrimaryKeySelective(EkUser ekUser);

    List<EkUser> selectUserList(Map<String, Object> map);

}
