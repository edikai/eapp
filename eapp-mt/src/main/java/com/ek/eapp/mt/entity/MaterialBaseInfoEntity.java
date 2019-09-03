/*
 * 类名称:MaterialBaseInfoEntity.java
 * 包名称:com.platform.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-03 08:50:03        qin_hqing     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 *
 * @author qin_hqing
 * @date 2019-09-03 08:50:03
 */
@Data
@TableName("dd_material_base_info")
public class MaterialBaseInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 
     */
    private String mtTypeCode;
    /**
     * 
     */
    private String mtTypeName;
    /**
     * 
     */
    private String mtTypeMsUnits;
    /**
     * 
     */
    private Double mtTypePriceUnits;
    /**
     * 
     */
    private String ddCorpId;
    /**
     * 
     */
    private Integer ddDeptId;
    /**
     * 
     */
    private String attribute1;
    /**
     * 
     */
    private String attribute2;
    /**
     * 
     */
    private String attribute3;
    /**
     * 
     */
    private String attribute4;
    /**
     * 
     */
    private String createBy;
    /**
     * 
     */
    private Date createDate;
}
