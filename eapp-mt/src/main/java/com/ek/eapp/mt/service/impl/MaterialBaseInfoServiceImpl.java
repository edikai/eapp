/*
 * 类名称:MaterialBaseInfoServiceImpl.java
 * 包名称:com.platform.service.impl
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-03 08:50:03        qin_hqing     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ek.eapp.mt.dao.MaterialBaseInfoDao;
import com.ek.eapp.mt.entity.MaterialBaseInfoEntity;
import com.ek.eapp.mt.service.MaterialBaseInfoService;
import com.ek.eapp.util.CodeUtil;
import com.ek.eapp.util.QueryPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author qin_hqing
 * @date 2019-09-03 08:50:03
 */
@Service("materialBaseInfoService")
public class MaterialBaseInfoServiceImpl extends ServiceImpl<MaterialBaseInfoDao, MaterialBaseInfoEntity> implements MaterialBaseInfoService {

    @Autowired
    private CodeUtil codeUtil;

    @Override
    public List<MaterialBaseInfoEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.id");
        params.put("asc", false);
        Page<MaterialBaseInfoEntity> page = new QueryPlus<MaterialBaseInfoEntity>(params).getPage();
        return page.setRecords(baseMapper.selectMaterialBaseInfoPage(page, params));
    }

    @Override
    public boolean add(MaterialBaseInfoEntity materialBaseInfo) {
        materialBaseInfo.setMtTypeCode(codeUtil.generateLong());
        return this.save(materialBaseInfo);
    }

    @Override
    public boolean update(MaterialBaseInfoEntity materialBaseInfo) {
        return this.updateById(materialBaseInfo);
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
