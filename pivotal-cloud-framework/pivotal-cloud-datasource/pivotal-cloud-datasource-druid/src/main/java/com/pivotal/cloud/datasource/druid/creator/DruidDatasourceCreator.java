package com.pivotal.cloud.datasource.druid.creator;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.pivotal.cloud.datasource.boot.config.DruidConfig;
import com.pivotal.cloud.datasource.boot.constants.DatabasePools;
import com.pivotal.cloud.datasource.boot.creator.DatasourceCreator;
import com.pivotal.cloud.datasource.boot.exception.DynamicDatasourceRoutingException;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.utils.DatasourceToolsUtil;
import com.pivotal.cloud.datasource.druid.utils.DruidConfigUtil;
import com.pivotal.cloud.datasource.druid.utils.DruidLoggerUtil;
import com.pivotal.cloud.datasource.druid.utils.DruidStatUtil;
import com.pivotal.cloud.datasource.druid.utils.DruidWallUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @packageName com.pivotal.cloud.datasource.druid.creator.DruidDatasourceCreator
 * @projectName: pivotalCloud
 * @className: DruidDatasourceCreator
 * @title: 封装pivotalCloud项目-DruidDatasourceCreator类
 * @content: DruidDatasourceCreator
 * @description: pivotalCloud项目-DruidDatasourceCreator类,主要用作DruidDatasourceCreator。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 14:38
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DruidDatasourceCreator implements DatasourceCreator {
    private static Method configMethod = null;

    private DruidConfig druid;


    static {
        fetchMethod();
    }


    /**
     * Druid since 1.2.17 use 'configFromPropeties' to copy config
     * Druid < 1.2.17 use 'configFromPropety' to copy config
     */
    @SuppressWarnings("JavaReflectionMemberAccess")
    private static void fetchMethod() {
        Class<DruidDataSource> aClass = DruidDataSource.class;
        try {
            configMethod = aClass.getMethod("configFromPropeties", Properties.class);
            return;
        } catch (NoSuchMethodException ignored) {
        }

        try {
            configMethod = aClass.getMethod("configFromPropety", Properties.class);
            return;
        } catch (NoSuchMethodException ignored) {
        }
        throw new RuntimeException("Druid does not has 'configFromPropeties' or 'configFromPropety' method!");
    }

    /**
     * 通过属性文件创建数据源
     *
     * @param datasource 数据源属性
     * @return 被创建的数据源
     */
    @Override
    public DataSource createDatasource(DatasourceProperties datasource) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(datasource.getUsername());
        dataSource.setPassword(datasource.getPassword());
        dataSource.setUrl(datasource.getUrl());
        dataSource.setName(datasource.getPoolName());
        String driverClassName = datasource.getDriverClassName();
        if (DatasourceToolsUtil.hasText(driverClassName)) {
            dataSource.setDriverClassName(driverClassName);
        }
        DruidConfig config = datasource.getDruid();
        Properties properties = DruidConfigUtil.mergeConfig(druid, config);
        List<Filter> proxyFilters = this.initFilters(datasource, properties.getProperty("druid.filters"));
        dataSource.setProxyFilters(proxyFilters);
        try {
            configMethod.invoke(dataSource, properties);
        } catch (Exception ignore) {

        }
        //连接参数单独设置
        dataSource.setConnectProperties(config.getConnectionProperties());
        //设置druid内置properties不支持的的参数
        this.setParam(dataSource, config);
        if (Boolean.FALSE.equals(datasource.getLazy())) {
            try {
                dataSource.init();
            } catch (SQLException e) {
                throw new DynamicDatasourceRoutingException("druid create error", e);
            }
        }
        return dataSource;
    }

    private List<Filter> initFilters(DatasourceProperties properties, String filters) {
        List<Filter> proxyFilters = new ArrayList<>(2);
        if (DatasourceToolsUtil.hasText(filters)) {
            String[] filterItems = filters.split(",");
            for (String filter : filterItems) {
                switch (filter) {
                    case "stat":
                        proxyFilters.add(DruidStatUtil.toStatFilter(properties.getDruid().getStat(), druid.getStat()));
                        break;
                    case "wall":
                        WallConfig wallConfig = DruidWallUtil.toWallConfig(properties.getDruid().getWall(), druid.getWall());
                        WallFilter wallFilter = new WallFilter();
                        wallFilter.setConfig(wallConfig);
                        proxyFilters.add(wallFilter);
                        break;
                    case "slf4j":
                        proxyFilters.add(DruidLoggerUtil.initFilter(Slf4jLogFilter.class, properties.getDruid().getSlf4j(), druid.getSlf4j()));
                        break;
                    case "commons-log":
                        proxyFilters.add(DruidLoggerUtil.initFilter(CommonsLogFilter.class, properties.getDruid().getCommonsLog(), druid.getCommonsLog()));
                        break;
                    case "log4j":
                        proxyFilters.add(DruidLoggerUtil.initFilter(Log4jFilter.class, properties.getDruid().getLog4j(), druid.getLog4j()));
                        break;
                    case "log4j2":
                        proxyFilters.add(DruidLoggerUtil.initFilter(Log4j2Filter.class, properties.getDruid().getLog4j2(), druid.getLog4j2()));
                        break;
                    default:
                        log.warn("dynamic-datasource current not support [{}]", filter);
                }
            }
        }
        return proxyFilters;
    }


    private void setParam(DruidDataSource dataSource, DruidConfig config) {
        String defaultCatalog = config.getDefaultCatalog() == null ? druid.getDefaultCatalog() : config.getDefaultCatalog();
        if (defaultCatalog != null) {
            dataSource.setDefaultCatalog(defaultCatalog);
        }
        Boolean defaultAutoCommit = config.getDefaultAutoCommit() == null ? druid.getDefaultAutoCommit() : config.getDefaultAutoCommit();
        if (defaultAutoCommit != null && !defaultAutoCommit) {
            dataSource.setDefaultAutoCommit(false);
        }
        Boolean defaultReadOnly = config.getDefaultReadOnly() == null ? druid.getDefaultReadOnly() : config.getDefaultReadOnly();
        if (defaultReadOnly != null) {
            dataSource.setDefaultReadOnly(defaultReadOnly);
        }
        Integer defaultTransactionIsolation = config.getDefaultTransactionIsolation() == null ? druid.getDefaultTransactionIsolation() : config.getDefaultTransactionIsolation();
        if (defaultTransactionIsolation != null) {
            dataSource.setDefaultTransactionIsolation(defaultTransactionIsolation);
        }

        Boolean testOnReturn = config.getTestOnReturn() == null ? druid.getTestOnReturn() : config.getTestOnReturn();
        if (testOnReturn != null && testOnReturn) {
            dataSource.setTestOnReturn(true);
        }
        Integer validationQueryTimeout =
                config.getValidationQueryTimeout() == null ? druid.getValidationQueryTimeout() : config.getValidationQueryTimeout();
        if (validationQueryTimeout != null && !validationQueryTimeout.equals(-1)) {
            dataSource.setValidationQueryTimeout(validationQueryTimeout);
        }

        Boolean sharePreparedStatements =
                config.getSharePreparedStatements() == null ? druid.getSharePreparedStatements() : config.getSharePreparedStatements();
        if (sharePreparedStatements != null && sharePreparedStatements) {
            dataSource.setSharePreparedStatements(true);
        }
        Integer connectionErrorRetryAttempts =
                config.getConnectionErrorRetryAttempts() == null ? druid.getConnectionErrorRetryAttempts()
                        : config.getConnectionErrorRetryAttempts();
        if (connectionErrorRetryAttempts != null && !connectionErrorRetryAttempts.equals(1)) {
            dataSource.setConnectionErrorRetryAttempts(connectionErrorRetryAttempts);
        }
        Boolean breakAfterAcquireFailure =
                config.getBreakAfterAcquireFailure() == null ? druid.getBreakAfterAcquireFailure() : config.getBreakAfterAcquireFailure();
        if (breakAfterAcquireFailure != null && breakAfterAcquireFailure) {
            dataSource.setBreakAfterAcquireFailure(true);
        }

        Integer timeout = config.getRemoveAbandonedTimeoutMillis() == null ? druid.getRemoveAbandonedTimeoutMillis()
                : config.getRemoveAbandonedTimeoutMillis();
        if (timeout != null) {
            dataSource.setRemoveAbandonedTimeoutMillis(timeout);
        }

        Boolean abandoned = config.getRemoveAbandoned() == null ? druid.getRemoveAbandoned() : config.getRemoveAbandoned();
        if (abandoned != null) {
            dataSource.setRemoveAbandoned(abandoned);
        }

        Boolean logAbandoned = config.getLogAbandoned() == null ? druid.getLogAbandoned() : config.getLogAbandoned();
        if (logAbandoned != null) {
            dataSource.setLogAbandoned(logAbandoned);
        }

        Integer queryTimeOut = config.getQueryTimeout() == null ? druid.getQueryTimeout() : config.getQueryTimeout();
        if (queryTimeOut != null) {
            dataSource.setQueryTimeout(queryTimeOut);
        }

        Integer transactionQueryTimeout =
                config.getTransactionQueryTimeout() == null ? druid.getTransactionQueryTimeout() : config.getTransactionQueryTimeout();
        if (transactionQueryTimeout != null) {
            dataSource.setTransactionQueryTimeout(transactionQueryTimeout);
        }

        Long timeBetweenConnectErrorMillis =
                config.getTimeBetweenConnectErrorMillis() == null ? druid.getTimeBetweenConnectErrorMillis() : config.getTimeBetweenConnectErrorMillis();
        if (timeBetweenConnectErrorMillis != null) {
            dataSource.setTimeBetweenConnectErrorMillis(timeBetweenConnectErrorMillis);
        }

        // since druid 1.2.12
        Integer connectTimeout = config.getConnectTimeout() == null ? druid.getConnectTimeout() : config.getConnectTimeout();
        if (connectTimeout != null) {
            try {
                DruidDataSource.class.getMethod("setConnectTimeout", int.class);
                dataSource.setConnectTimeout(connectTimeout);
            } catch (NoSuchMethodException e) {
                log.warn("druid current not support connectTimeout,please update druid 1.2.12 +");
            }
        }

        Integer socketTimeout = config.getSocketTimeout() == null ? druid.getSocketTimeout() : config.getSocketTimeout();
        if (socketTimeout != null) {
            try {
                DruidDataSource.class.getMethod("setSocketTimeout", int.class);
                dataSource.setSocketTimeout(socketTimeout);
            } catch (NoSuchMethodException e) {
                log.warn("druid current not support setSocketTimeout,please update druid 1.2.12 +");
            }
        }
    }

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties 数据源属性
     * @return 是否支持
     */
    @Override
    public boolean support(DatasourceProperties properties) {
        Class<? extends DataSource> type = properties.getType();
        return type == null || DatabasePools.DRUID.equals(type.getName());
    }

}
