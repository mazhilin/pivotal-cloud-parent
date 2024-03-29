package com.pivotal.cloud.datasource.boot.config;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @packageName com.pivotal.cloud.datasource.boot.config.Dbcp2Config
 * @projectName: pivotalCloud
 * @className: Dbcp2Config
 * @title: 封装pivotalCloud项目-Dbcp2Config类
 * @content: Dbcp2Config-Dbcp2数据库连接池属性类
 * @description: pivotalCloud项目-Dbcp2Config类,主要用作Dbcp2Config-Dbcp2数据库连接池属性类。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:00
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class Dbcp2Config {
    private Boolean defaultAutoCommit;
    private Boolean defaultReadOnly;
    private Integer defaultTransactionIsolation;
    private Integer defaultQueryTimeoutSeconds;
    private String defaultCatalog;
    private String defaultSchema;

    private Boolean cacheState;
    private Boolean lifo;

    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer initialSize;
    private Long maxWaitMillis;

    private Boolean poolPreparedStatements;
    private Boolean clearStatementPoolOnReturn;
    private Integer maxOpenPreparedStatements;
    private Boolean testOnCreate;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;

    private Long timeBetweenEvictionRunsMillis;
    private Integer numTestsPerEvictionRun;
    private Long minEvictableIdleTimeMillis;
    private Long softMinEvictableIdleTimeMillis;
    private String evictionPolicyClassName;
    private Boolean testWhileIdle;

    private String validationQuery;
    private Integer validationQueryTimeoutSeconds;
    private String connectionFactoryClassName;

    private List<String> connectionInitSqls;
    private Boolean accessToUnderlyingConnectionAllowed;
    private Long maxConnLifetimeMillis;
    private Boolean logExpiredConnections;
    private String jmxName;
    private Boolean autoCommitOnReturn;
    private Boolean rollbackOnReturn;
    private Set<String> disconnectionSqlCodes;
    private Boolean fastFailValidation;
    private String connectionProperties;
}
