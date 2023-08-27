package com.pivotal.cloud.datasource.boot.config;

import lombok.Data;

import java.util.Properties;

/**
 * @packageName com.pivotal.cloud.datasource.boot.config.BeecpConfig
 * @projectName: pivotalCloud
 * @className: BeecpConfig
 * @title: 封装pivotalCloud项目-BeecpConfig类
 * @content: BeecpConfig-Beecp数据库连接池配置属性类
 * @description: pivotalCloud项目-BeecpConfig类,主要用作BeecpConfig-Beecp数据库连接池配置属性类。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 9:59
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class BeecpConfig {
    private String defaultCatalog;
    private String defaultSchema;
    private Boolean defaultReadOnly;
    private Boolean defaultAutoCommit;
    private Integer defaultTransactionIsolationCode;
    private String defaultTransactionIsolationName;

    private Boolean fairMode;
    private Integer initialSize;
    private Integer maxActive;
    private Integer borrowSemaphoreSize;
    private Long maxWait;
    private Long idleTimeout;
    private Long holdTimeout;
    private String connectionTestSql;
    private Integer connectionTestTimeout;
    private Long connectionTestIntegererval;
    private Long idleCheckTimeIntegererval;
    private Boolean forceCloseUsingOnClear;
    private Long delayTimeForNextClear;

    private String connectionFactoryClassName;
    private String xaConnectionFactoryClassName;
    private Properties connectProperties;
    private String poolImplementClassName;
    private Boolean enableJmx;
}
