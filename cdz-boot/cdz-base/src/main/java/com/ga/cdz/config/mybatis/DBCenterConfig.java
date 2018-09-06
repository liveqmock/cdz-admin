package com.ga.cdz.config.mybatis;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author:luqi
 * @description: 中心库配置
 * @date:2018/9/4_9:09
 */
@Data
@ConfigurationProperties(prefix = "mysql.datasource.center")
@SpringBootConfiguration
public class DBCenterConfig {
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
