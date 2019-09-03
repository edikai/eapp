package com.ek.eapp.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ek.eapp.entity.EkUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface EkUserDao extends BaseMapper<EkUser> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(EkUser ekUser);

    EkUser selectByPrimaryKey(Integer id);

    EkUser selectByLoginName(@Param("loginName") String loginName);

    int updateByPrimaryKeySelective(EkUser ekUser);

    List<EkUser> selectUserList(Map<String, Object> map);
}