/*
 * 类名称:MaterialBaseInfoDao.java
 * 包名称:com.platform.dao
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-03 08:50:03        qin_hqing     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ek.eapp.mt.entity.MaterialBaseInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Dao
 *
 * @author qin_hqing
 * @date 2019-09-03 08:50:03
 */
@Component
@Mapper
public interface MaterialBaseInfoDao extends BaseMapper<MaterialBaseInfoEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<MaterialBaseInfoEntity> queryAll(@Param("params") Map<String, Object> params);

    /**
     * 自定义分页查询
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return List
     */
    List<MaterialBaseInfoEntity> selectMaterialBaseInfoPage(IPage page, @Param("params") Map<String, Object> params);
}
