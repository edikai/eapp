/*
 * 类名称:MaterialWorkOrderService.java
 * 包名称:com.platform.service
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-04 21:21:14        lipengjun     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ek.eapp.mt.entity.MaterialWorkOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @date 2019-09-04 21:21:14
 */
public interface MaterialWorkOrderService extends IService<MaterialWorkOrderEntity> {

    /**
     * 查询所有列表
     *
     * @param params 查询参数
     * @return List
     */
    List<MaterialWorkOrderEntity> queryAll(Map<String, Object> params);

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
     * @param materialWorkOrder 
     * @return 新增结果
     */
    boolean add(MaterialWorkOrderEntity materialWorkOrder);

    /**
     * 根据主键更新
     *
     * @param materialWorkOrder 
     * @return 更新结果
     */
    boolean update(MaterialWorkOrderEntity materialWorkOrder);

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
