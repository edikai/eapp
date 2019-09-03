package com.ek.eapp.service.impl;

import com.ek.eapp.dao.EkUserDao;
import com.ek.eapp.entity.EkUser;
import com.ek.eapp.service.IEkUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: EkUserServiceImpl
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-05
 * @Version: V2.0
 **/

@Service
public class EkUserServiceImpl implements IEkUserService {

    @Autowired
    private EkUserDao userDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(EkUser ekUser) {
        return userDao.insertSelective(ekUser);
    }

    @Override
    public EkUser selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public EkUser selectByLoginName(String loginName) {
        System.out.println("EkUserServiceImpl.selectByLoginName");
        if (null == loginName || StringUtils.isEmpty(loginName))
            return null;
        return userDao.selectByLoginName(loginName);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(EkUser ekUser) {
        return userDao.updateByPrimaryKeySelective(ekUser);
    }

    @Override
    public List<EkUser> selectUserList(Map<String, Object> map) {
        return userDao.selectUserList(map);
    }
}
