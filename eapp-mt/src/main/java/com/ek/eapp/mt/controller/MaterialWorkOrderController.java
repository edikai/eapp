/*
 * 类名称:MaterialWorkOrderController.java
 * 包名称:com.platform.controller
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-04 21:21:14        lipengjun     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.ek.eapp.mt.entity.MaterialWorkOrderEntity;
import com.ek.eapp.mt.service.MaterialWorkOrderService;
import com.ek.eapp.util.R;
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
 * @date 2019-09-04 21:21:14
 */
@RestController
@RequestMapping("/api/mt-work-order")
public class MaterialWorkOrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MaterialWorkOrderService materialWorkOrderService;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return R
     */
    @RequestMapping("/query-all")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<MaterialWorkOrderEntity> list = materialWorkOrderService.queryAll(params);

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
        Page page = materialWorkOrderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 按月汇总统计
     * @param params 查询参数
     * @return R
     */
    @RequestMapping("/stats-list-month")
    public @ResponseBody R statsListMonth(@RequestParam Map<String, Object> params) {
        if (!params.containsKey("selectMonth")){
            return R.error("参数selectMonth不存在.");
        }
        if (!params.containsKey("corpId")){
            return R.error("参数corpId不存在.");
        }
        String selectMonth = params.get("selectMonth").toString();
        params.put("selectMonth", String.format("%s-%s", selectMonth, "01"));
        List<MaterialWorkOrderEntity> list = materialWorkOrderService.statsListMonth(params);

        return R.ok().put("list", list);
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return R
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        MaterialWorkOrderEntity materialWorkOrder = materialWorkOrderService.getById(id);

        return R.ok().put("materialworkorder", materialWorkOrder);
    }

    /**
     * 新增
     *
     * @param materialWorkOrder materialWorkOrder
     * @return R
     */
    @RequestMapping("/save")
    public R save(@RequestBody MaterialWorkOrderEntity materialWorkOrder) {

        materialWorkOrderService.add(materialWorkOrder);

        return R.ok();
    }

    /**
     * 新增 并发起钉钉审批流程
     *
     * @param materialWorkOrder materialWorkOrder
     * @return R
     */
    @RequestMapping("/save-and-start")
    public R saveAndStartProcess(@RequestBody MaterialWorkOrderEntity materialWorkOrder) {

        List<OapiProcessinstanceCreateRequest.FormComponentValueVo> list = materialWorkOrder.generateForms();
        System.out.println(list.size());
        list.forEach((valueVo) -> {
            logger.info("name={}, value={}, extValue={}", valueVo.getName(), valueVo.getValue(), valueVo.getExtValue());
        });

        R result = null;
        try {
            result = materialWorkOrderService.addAndStartProcess(materialWorkOrder);
        } catch (Exception e) {
            e.printStackTrace();
            result = R.error("发起失败，请重新尝试");
        }

        return result;
    }

    /**
     * 修改
     *
     * @param materialWorkOrder materialWorkOrder
     * @return R
     */
    @RequestMapping("/update")
    public R update(@RequestBody MaterialWorkOrderEntity materialWorkOrder) {

        materialWorkOrderService.update(materialWorkOrder);

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
        materialWorkOrderService.deleteBatch(ids);

        return R.ok();
    }
}
