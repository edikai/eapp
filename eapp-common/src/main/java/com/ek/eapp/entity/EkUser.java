package com.ek.eapp.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EkUser implements Serializable {

    private Integer id;

    private String loginName;

    private String password;

    private String username;

    private String remark;

    private String validFlag;

    private Integer createBy;

    private Date createDate;
}