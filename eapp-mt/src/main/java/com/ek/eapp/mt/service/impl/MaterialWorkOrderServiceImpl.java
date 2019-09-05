/*
 * 类名称:MaterialWorkOrderServiceImpl.java
 * 包名称:com.platform.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-04 21:21:14        lipengjun     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ek.eapp.dd.util.ProcessInstanceUtil;
import com.ek.eapp.mt.dao.MaterialWorkOrderDao;
import com.ek.eapp.mt.entity.MaterialWorkOrderEntity;
import com.ek.eapp.mt.service.MaterialWorkOrderService;
import com.ek.eapp.util.CodeUtil;
import com.ek.eapp.util.QueryPlus;
import com.ek.eapp.util.R;
import com.taobao.api.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author qin_hqing
 * @date 2019-09-04 21:21:14
 */
@Service("materialWorkOrderService")
public class MaterialWorkOrderServiceImpl extends ServiceImpl<MaterialWorkOrderDao, MaterialWorkOrderEntity> implements MaterialWorkOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CodeUtil codeUtil;
    @Autowired
    ProcessInstanceUtil processInstanceUtil;

    @Override
    public List<MaterialWorkOrderEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.ID");
        params.put("asc", false);
        Page<MaterialWorkOrderEntity> page = new QueryPlus<MaterialWorkOrderEntity>(params).getPage();
        return page.setRecords(baseMapper.selectMaterialWorkOrderPage(page, params));
    }

    @Override
    public boolean add(MaterialWorkOrderEntity materialWorkOrder) {
        materialWorkOrder.setMtWoCode(codeUtil.generateLong());
        return this.save(materialWorkOrder);
    }

    @Transactional
    @Override
    public R addAndStartProcess(MaterialWorkOrderEntity materialWorkOrder) throws ApiException, RuntimeException {

        boolean bl = this.add(materialWorkOrder);
        logger.info("add result = {}", bl);
        if (bl) {
            materialWorkOrder.setOriginatorUserId(materialWorkOrder.getDdUserId());
            materialWorkOrder.setDeptId(materialWorkOrder.getDdDeptId());

            R result = processInstanceUtil.startProcessInstance(materialWorkOrder);
            if (Integer.parseInt(result.get("code").toString()) != R.SUCCESS_CODE){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  // 如果钉钉审批流程发起失败，则手动回滚事务
                return result;
            }
//            MaterialWorkOrderEntity entity = new MaterialWorkOrderEntity();
//            entity.setAttribute1(result.get("id").toString());
//            entity.setMtWoCode(materialWorkOrder.getMtWoCode());
//            this.updateByWoCode(entity);
        }

        return R.ok();
    }

    @Override
    public boolean update(MaterialWorkOrderEntity materialWorkOrder) {
        return this.updateById(materialWorkOrder);
    }

    @Override
    public boolean updateByWoCode(MaterialWorkOrderEntity materialWorkOrder) {
        return this.updateByWoCode(materialWorkOrder);
    }

    @Override
    public boolean delete(Integer id) {
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Integer[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }
}
