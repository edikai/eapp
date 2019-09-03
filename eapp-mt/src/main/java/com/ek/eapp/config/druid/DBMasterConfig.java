package com.ek.eapp.config.druid;

import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @ClassName: DBMasterConfig
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-27
 * @Version: V2.0
 **/

@Configuration
@ConfigurationProperties(value = "spring.datasource.druid.master")
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ek.eapp.dao", "com.ek.eapp.*.dao"}, sqlSessionFactoryRef = "sqlSessionFactoryMaster")
@Data
public class DBMasterConfig extends AbstractDruidDBConfig {

    private String url;
    private String username;
    private String password;

    @Primary
    @Bean(name = "dataSourceMaster", destroyMethod = "close", initMethod = "init")
    public DataSource dataSource() {
        System.out.println("url="+url);
        return super.createDataSource(url, username, password);
    }

    @Primary
    @Bean(name = "sqlSessionFactoryMaster")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory(dataSource());
    }

    @Primary
    @Bean(name = "transactionManagerMaster")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
