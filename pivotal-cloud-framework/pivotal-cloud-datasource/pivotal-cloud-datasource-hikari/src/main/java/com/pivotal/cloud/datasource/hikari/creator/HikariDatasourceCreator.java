package com.pivotal.cloud.datasource.hikari.creator;

import com.pivotal.cloud.boot.utils.ObjectToolsUtil;
import com.pivotal.cloud.datasource.boot.config.HikaricpConfig;
import com.pivotal.cloud.datasource.boot.constants.DatabasePools;
import com.pivotal.cloud.datasource.boot.creator.DatasourceCreator;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.utils.MergeConfigCreator;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @className: com.pivotal.cloud.datasource.hikari.creator.HikariDatasourceCreator
 * @title: 封装CoocaaCloud项目-HikariDatasourceCreator类
 * @description: <p>
 *         CoocaaCloud项目-HikariDatasourceCreator
 *         </p>
 * @content: HikariDatasourceCreator
 * @author: Powered by marklin
 * @datetime: 2023-06-05 23:39
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CoocaaCloud Systems Incorporated. All rights reserved.
 */
@NoArgsConstructor
@AllArgsConstructor
public class HikariDatasourceCreator implements DatasourceCreator {
    private static final MergeConfigCreator<HikaricpConfig, HikariConfig> MERGE_CREATOR = new MergeConfigCreator<>("HikariCp", HikaricpConfig.class, HikariConfig.class);
    private static Method configCopyMethod = null;

    static {
        fetchMethod();
    }

    private HikaricpConfig hikaricp;

    /**
     * to support springboot 1.5 and 2.x
     * HikariConfig 2.x use 'copyState' to copy config
     * HikariConfig 3.x use 'copyStateTo' to copy config
     */
    @SuppressWarnings("JavaReflectionMemberAccess")
    private static void fetchMethod() {
        Class<HikariConfig> hikariConfigClass = HikariConfig.class;
        try {
            configCopyMethod = hikariConfigClass.getMethod("copyState", hikariConfigClass);
            return;
        } catch (NoSuchMethodException ignored) {
        }

        try {
            configCopyMethod = hikariConfigClass.getMethod("copyStateTo", hikariConfigClass);
            return;
        } catch (NoSuchMethodException ignored) {
        }
        throw new RuntimeException("HikariConfig does not has 'copyState' or 'copyStateTo' method!");
    }
    /**
     * 通过属性创建数据源
     *
     * @param properties
     *         数据源属性
     *
     * @return 被创建的数据源
     */
    @Override
    public DataSource createDatasource(DatasourceProperties properties) {
        HikariConfig config = MERGE_CREATOR.create(hikaricp, properties.getHikari());
        config.setUsername(properties.getUsername());
        config.setPassword(properties.getPassword());
        config.setJdbcUrl(properties.getUrl());
        config.setPoolName(properties.getPoolName());
        String driverClassName = properties.getDriverClassName();
        if (ObjectToolsUtil.hasText(driverClassName)) {
            config.setDriverClassName(driverClassName);
        }
        if (Boolean.FALSE.equals(properties.getLazy())) {
            return new HikariDataSource(config);
        }
        config.validate();
        HikariDataSource dataSource = new HikariDataSource();
        try {
            configCopyMethod.invoke(config, dataSource);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("HikariConfig failed to copy to HikariDataSource", e);
        }
        return dataSource;
    }

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties
     *         数据源属性
     *
     * @return 是否支持
     */
    @Override
    public boolean support(DatasourceProperties properties) {
        Class<? extends DataSource> type = properties.getType();
        return type == null || DatabasePools.HIKARI.equals(type.getName());
    }
}
