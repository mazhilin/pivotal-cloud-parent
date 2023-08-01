package com.pivotal.cloud.datasource.boot.config;

import lombok.Data;

import java.util.Properties;

/**
 * @className: com.pivotal.cloud.datasource.boot.config.HikaricpConfig
 * @title: 封装pivotalCloud项目-HikaricpConfig类
 * @description: <p>
 *         pivotalCloud项目-HikaricpConfig
 *         </p>
 * @content: HikaricpConfig
 * @author: Powered by marklin
 * @datetime: 2023-06-02 03:28
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class HikaricpConfig {
    private String catalog;
    private Long connectionTimeout;
    private Long validationTimeout;
    private Long idleTimeout;
    private Long leakDetectionThreshold;
    private Long maxLifetime;
    private Integer maxPoolSize;
    private Integer maximumPoolSize;
    private Integer minIdle;
    private Integer minimumIdle;

    private Long initializationFailTimeout;
    private String connectionInitSql;
    private String connectionTestQuery;
    private String dataSourceClassName;
    private String dataSourceJndiName;
    private String transactionIsolationName;
    private Boolean isAutoCommit;
    private Boolean isReadOnly;
    private Boolean isIsolateInternalQueries;
    private Boolean isRegisterMbeans;
    private Boolean isAllowPoolSuspension;
    private Properties dataSourceProperties;
    private Properties healthCheckProperties;

    /**
     * 高版本才有
     */
    private String schema;
    private String exceptionOverrideClassName;
    private Long keepaliveTime;
    private Boolean sealed;

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maxPoolSize = maximumPoolSize;
    }

    public void setMinimumIdle(Integer minimumIdle) {
        this.minIdle = minimumIdle;
    }
}
