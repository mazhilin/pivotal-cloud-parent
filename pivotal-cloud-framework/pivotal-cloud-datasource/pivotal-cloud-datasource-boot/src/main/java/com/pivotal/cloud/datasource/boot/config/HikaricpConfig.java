package com.pivotal.cloud.datasource.boot.config;

import lombok.Data;

import java.util.Properties;

/**
 * @packageName com.pivotal.cloud.datasource.boot.config.HikaricpConfig
 * @projectName: CooocaaCloud
 * @className: HikaricpConfig
 * @title: 封装CooocaaCloud项目-HikariConfig类
 * @content: HikaricpConfig-HikariCP数据库连接池属性配置类
 * @description: CooocaaCloud项目-HikariConfig类,主要用作HikariConfig-HikariCP数据库连接池属性配置类。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:03
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CooocaaCloud Systems Incorporated. All rights reserved.
 */
@Data
public class HikaricpConfig {

    /**
     * Hikaricp-poolName
     */
    private String poolName;

    /**
     * Hikaricp-catalog: 数据库目录（可选）
     */
    private String catalog;

    /**
     * Hikaricp-connectionTimeout: 连接超时时间，单位毫秒，默认值30000（30秒）
     */
    private Long connectionTimeout;
    /**
     * Hikaricp-validationTimeout: 连接校验超时时间，单位毫秒，默认值5000（5秒）
     */
    private Long validationTimeout;
    /**
     * Hikaricp-idleTimeout: 连接最大空闲时间，单位毫秒，默认值600000（10分钟）
     */
    private Long idleTimeout;
    /**
     * Hikaricp-idleTimeout: 连接最大空闲时间，单位毫秒，默认值600000（10分钟）
     */
    private Long leakDetectionThreshold;
    /**
     * Hikaricp-maxLifetime: 连接最大存活时间，单位毫秒，默认值1800000（30分钟）
     */
    private Long maxLifetime;
    /**
     * Hikaricp-maximumPoolSize: 最大连接数，默认值10
     */
    private Integer maxPoolSize;
    /**
     * Hikaricp-maximumPoolSize: 最大连接数，默认值10
     */
    private Integer maximumPoolSize;
    /**
     * Hikaricp-maximumPoolSize: 最大连接数，默认值10
     */
    private Integer minIdle;
    /**
     * Hikaricp-minimumIdle: 最小空闲连接数，默认值10
     */
    private Integer minimumIdle;


    /**
     * Hikaricp-initializationFailTimeout
     */
    private Long initializationFailTimeout;
    /**
     * Hikaricp-connectionInitSql
     */
    private String connectionInitSql;
    /**
     * Hikaricp-connectionTestQuery
     */
    private String connectionTestQuery;
    /**
     * Hikaricp-dataSourceClassName
     */
    private String dataSourceClassName;
    /**
     * Hikaricp-dataSourceJndiName
     */
    private String dataSourceJndiName;
    /**
     * Hikaricp-transactionIsolationName
     */
    private String transactionIsolationName;
    /**
     * Hikaricp-isAutoCommit
     */
    private Boolean isAutoCommit;
    /**
     * Hikaricp-isReadOnly
     */
    private Boolean isReadOnly;
    /**
     * Hikaricp-isIsolateInternalQueries
     */
    private Boolean isIsolateInternalQueries;
    /**
     * HikaricpisRegisterMbeans
     */
    private Boolean isRegisterMbeans;
    /**
     * Hikaricp-isAllowPoolSuspension
     */
    private Boolean isAllowPoolSuspension;
    /**
     * Hikaricp-dataSourceProperties
     */
    private Properties dataSourceProperties;
    /**
     * Hikaricp-healthCheckProperties
     */
    private Properties healthCheckProperties;


    /**
     * Hikaricp-schema
     */
    private String schema;
    /**
     * Hikaricp-exceptionOverrideClassName
     */
    private String exceptionOverrideClassName;
    /**
     * Hikaricp-keepaliveTime
     */
    private Long keepaliveTime;
    /**
     * Hikaricp-sealed
     */
    private Boolean sealed;

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maxPoolSize = maximumPoolSize;
    }

    public void setMinimumIdle(Integer minimumIdle) {
        this.minIdle = minimumIdle;
    }
}
