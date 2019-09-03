package com.ek.eapp.config.druid;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName: AbstractDruidDBConfig
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-27
 * @Version: V2.0
 **/

@Configuration
@EnableConfigurationProperties(DruidDbProperties.class)
public abstract class AbstractDruidDBConfig {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DruidDbProperties druidDbProperties;

    public DruidDataSource createDataSource(String url, String username, String password) {
        if (StringUtils.isEmpty(url)){
            log.error("properties url is null.");
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        System.out.println(druidDbProperties.toString());
        try {
            druidDataSource.setFilters(druidDbProperties.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter error.", e);
        }
        druidDataSource.setConnectProperties(druidDbProperties.getConnectionProperties());
//        druidDataSource.getProxyFilters().stream().filter(f -> f instanceof StatFilter).forEach(f -> {
//            StatFilter filter = (StatFilter) f;
//            filter.setLogSlowSql(Boolean.parseBoolean(druidDbProperties.getFilter().get("stat.logSlowSql").toString()));
//
//        });
        druidDataSource.getProxyFilters().stream().filter(f -> f instanceof StatFilter).forEach(f -> {
            StatFilter filter = (StatFilter) f;
            System.out.println(filter.isLogSlowSql());

        });
//        druidDataSource.getProxyFilters().stream().filter(f -> f instanceof StatFilter).forEach(System.out::println);
        return druidDataSource;
    }

    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        return sqlSessionFactory(dataSource, "classpath*:mapper/**/*.xml");
    }

    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, String mapperLocations) throws Exception {
        return createSqlSessionFactory(dataSource, mapperLocations);
    }

    /**
     * 加载mybatis xml配置文件，并初始化分页插件
     *
     * @param dataSource      数据源
     * @param mapperLocations 自定义xml配置路径
     * @return
     * @throws Exception
     */
    public SqlSessionFactory createSqlSessionFactory(DataSource dataSource, String mapperLocations) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props); // 添加插件
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});

        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));

        return sqlSessionFactoryBean.getObject();
    }
}
