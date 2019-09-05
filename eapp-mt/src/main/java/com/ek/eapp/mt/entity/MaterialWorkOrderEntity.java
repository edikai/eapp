/*
 * 类名称:MaterialWorkOrderEntity.java
 * 包名称:com.platform.entity
 *
 * 修改履历:
 *     日期                       修正者        主要内容
 *     2019-09-04 21:21:14        lipengjun     初版做成
 *
 * Copyright (c) 2019-2019 微同科技
 */
package com.ek.eapp.mt.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ek.eapp.dd.model.ProcessInstanceInputVO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 *
 * @author qin_hqing
 * @date 2019-09-04 21:21:14
 */
@Data
@TableName("dd_material_work_order")
public class MaterialWorkOrderEntity extends ProcessInstanceInputVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId
    private Integer id;
    /**
     * 
     */
    private String mtWoCode;
    /**
     * 
     */
    private String mtWoDesc;
    /**
     * 
     */
    private Date mtWoUseDate;
    /**
     * 
     */
    private Integer mtBaseId;
    /**
     * 
     */
    private Double mtBaseAmount;
    /**
     * 
     */
    private Double mtWoTotalPrice;
    /**
     *
     */
    private String ddCorpId;
    /**
     *
     */
    private Long ddDeptId;
    /**
     *
     */
    private String ddUserId;
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
