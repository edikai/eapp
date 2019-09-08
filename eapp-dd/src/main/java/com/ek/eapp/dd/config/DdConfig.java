/**
 * Copyright (C), 2018-2019, JEEEK
 * FileName: DdConfig
 * Author:   Edi_kai
 * Date:     2019/9/8 21:22
 * Description:
 * Version:  V1.0
 * <author>     <time>       <version>    <desc>
 * 作者姓名      修改时间       版本号       描述
 */
package com.ek.eapp.dd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "dd.eapp")
@Data
public class DdConfig {
}
