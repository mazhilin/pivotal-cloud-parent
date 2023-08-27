package com.pivotal.cloud.datasource.hikari.config;

import com.pivotal.cloud.datasource.boot.creator.BasicDatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.JndiDatasourceCreator;
import com.pivotal.cloud.datasource.hikari.creator.HikariDatasourceCreator;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @packageName com.pivotal.cloud.datasource.hikari.config.HikariDatasourceCreatorAutoConfig
 * @projectName: pivotalCloud
 * @className: HikariDatasourceCreatorAutoConfig
 * @title: 封装pivotalCloud项目-HikariDatasourceCreatorAutoConfig类
 * @content: HikariDatasourceCreatorAutoConfig
 * @description: pivotalCloud项目-HikariDatasourceCreatorAutoConfig类,主要用作HikariDatasourceCreatorAutoConfig。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 12:40
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicHikariDatasourceProperties.class)
public class HikariDatasourceCreatorAutoConfig {

    public static final int JNDI_ORDER = 1000;
    public static final int HIKARI_ORDER = 3000;
    public static final int DEFAULT_ORDER = 7000;

    private final DynamicHikariDatasourceProperties properties;


    @Bean
    @Order(DEFAULT_ORDER)
    public BasicDatasourceCreator basicDataSourceCreator() {
        return new BasicDatasourceCreator();
    }

    @Bean
    @Order(JNDI_ORDER)
    public JndiDatasourceCreator jndiDataSourceCreator() {
        return new JndiDatasourceCreator();
    }


    /**
     * 存在Druid数据源时, 加入创建器
     */

    @Bean
    @Order(HIKARI_ORDER)
    @ConditionalOnClass(HikariDataSource.class)
    public HikariDatasourceCreator hikariDatasourceCreator() {
        return new HikariDatasourceCreator(properties.getPoolConfig());
    }
}
