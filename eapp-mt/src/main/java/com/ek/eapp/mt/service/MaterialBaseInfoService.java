/*
 * 类名称:MaterialBaseInfoService.java
 * 包名称:com.platform.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-03 08:50:03        qin_hqing     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.entity.MaterialBaseInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author qin_hqing
 * @date 2019-09-03 08:50:03
 */
public interface MaterialBaseInfoService extends IService<MaterialBaseInfoEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<MaterialBaseInfoEntity> queryAll(Map<String, Object> params);

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return Page
     */
    Page queryPage(Map<String, Object> params);

    /**
     * 新增
     *
     * @param materialBaseInfo 
     * @return 新增结果
     */
    boolean add(MaterialBaseInfoEntity materialBaseInfo);

    /**
     * 根据主键更新
     *
     * @param materialBaseInfo 
     * @return 更新结果
     */
    boolean update(MaterialBaseInfoEntity materialBaseInfo);

    /**
     * 根据主键删除
     *
     * @param id id
     * @return 删除结果
     */
    boolean delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids ids
     * @return 删除结果
     */
    boolean deleteBatch(Integer[] ids);
}
