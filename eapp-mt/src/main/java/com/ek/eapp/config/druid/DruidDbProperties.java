package com.ek.eapp.config.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName: DruidDbProperties
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-27
 * @Version: V2.0
 **/

@Configuration
@ConfigurationProperties(value = "spring.datasource.druid")
@Data
public class DruidDbProperties {

    private String driverClassName ;
    /**
     * 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
     */
    private int initialSize = 10;
    /**
     * 最小连接池数量
     */
    private int minIdle = 50;
    /**
     * 最大连接池数量
     */
    private int maxActive = 300;
    /**
     * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    private long maxWait = 60000;
    /**
     * 有两个含义： 1)
     * Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。 2)
     * testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private long timeBetweenEvictionRunsMillis = 60000;
    /**
     * 连接保持空闲而不被驱逐的最长时间
     */
    private long minEvictableIdleTimeMillis = 3600000;
    /**
     * 用来检测连接是否有效的sql，要求是一个查询语句，常用select
     * 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。
     */
    private String validationQuery = "SELECT USER()";
    /**
     * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     */
    private boolean testWhileIdle = true;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private boolean testOnBorrow = false;
    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     */
    private boolean testOnReturn = false;
    /**
     * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat 日志用的filter:log4j
     * 防御sql注入的filter:wall
     */
    private String filters = "mergeStat,config,wall";
    private Properties connectionProperties;
    private Properties filter;

//    @Value("filter.stat.log-slow-sql")
    private boolean logSlowSql = false;
    /**
     * 白名单
     */
    private String allow;
    /**
     * 黑名单
     */
    private String deny;
    private String username = "admin";
    private String password = "admin";

    public String toString() {
        StringBuffer buf = new StringBuffer();

        buf.append("{");
        buf.append("\n\tdriverClassName: ").append(driverClassName).append(",");
        buf.append("\n\tinitialSize: ").append(initialSize).append(",");
        buf.append("\n\tminIdle: ").append(minIdle).append(",");
        buf.append("\n\tmaxActive: ").append(maxActive).append(",");
        buf.append("\n\ttimeBetweenEvictionRunsMillis: ").append(timeBetweenEvictionRunsMillis).append(",");
        buf.append("\n\tminEvictableIdleTimeMillis: ").append(minEvictableIdleTimeMillis).append(",");
        buf.append("\n\tvalidationQuery: ").append(validationQuery).append(",");
        buf.append("\n\ttestWhileIdle: ").append(testWhileIdle).append(",");
        buf.append("\n\ttestOnBorrow: ").append(testOnBorrow).append(",");
        buf.append("\n\ttestOnReturn: ").append(testOnReturn).append(",");
        buf.append("\n\tfilters: ").append(filters).append(",");
        buf.append("\n\tconnectionProperties: ").append(connectionProperties).append(",");
        buf.append("\n\tallow: ").append(allow).append(",");
        buf.append("\n\tdeny: ").append(deny).append(",");
        buf.append("\n\tusername: ").append(username);
        buf.append("\n\tfilter: ").append(filter);
        buf.append("\n}");

        return buf.toString();
    }

//    public Properties getConnectionProperties() {
//
//        Properties connectionProperties = System.getProperties();
//
//        System.out.println( this.filter.get("stat.log-slow-sql"));
//        connectionProperties.put("druid.stat.logSlowSql", this.filter.get("stat.log-slow-sql").toString());
//
//
//        return connectionProperties;
//    }

}
