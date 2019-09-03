/*
 * 类名称:MaterialBaseInfoController.java
 * 包名称:com.platform.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-03 08:50:03        qin_hqing     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ek.eapp.mt.service.MaterialBaseInfoService;
import com.ek.eapp.util.R;
import com.platform.entity.MaterialBaseInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author qin_hqing
 * @date 2019-09-03 08:50:03
 */
@RestController
@RequestMapping("/api/mt-base-info")
public class MaterialBaseInfoController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MaterialBaseInfoService materialBaseInfoService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return R
     */
    @RequestMapping("/query-all")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<MaterialBaseInfoEntity> list = materialBaseInfoService.queryAll(params);

        return R.ok().put("list", list);
    }

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return R
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        Page page = materialBaseInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        MaterialBaseInfoEntity materialBaseInfo = materialBaseInfoService.getById(id);

        return R.ok().put("materialbaseinfo", materialBaseInfo);
    }

    /**
     * 新增
     *
     * @param materialBaseInfo materialBaseInfo
     * @return R
     */
    @RequestMapping("/save")
    public R save(@RequestBody MaterialBaseInfoEntity materialBaseInfo) {

        materialBaseInfoService.add(materialBaseInfo);

        return R.ok();
    }

    /**
     * 修改
     *
     * @param materialBaseInfo materialBaseInfo
     * @return R
     */
    @RequestMapping("/update")
    public R update(@RequestBody MaterialBaseInfoEntity materialBaseInfo) {

        materialBaseInfoService.update(materialBaseInfo);

        return R.ok();
    }

    /**
     * 根据主键删除
     *
     * @param ids ids
     * @return R
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        materialBaseInfoService.deleteBatch(ids);

        return R.ok();
    }
}
