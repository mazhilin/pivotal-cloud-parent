package com.pivotal.cloud.datasource.hikari.configuration;


import com.pivotal.cloud.datasource.boot.dynamic.DynamicRoutingSwitchDatasource;
import com.pivotal.cloud.datasource.hikari.config.HikariDatasourceAspectAutoConfig;
import com.pivotal.cloud.datasource.hikari.config.HikariDatasourceAssistAutoConfig;
import com.pivotal.cloud.datasource.hikari.config.HikariDatasourceCreatorAutoConfig;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceCustomizer;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.hikari.configuration.EnableHikariAutoConfiguration
 * @projectName: pivotalCloud
 * @className: EnableHikariAutoConfiguration
 * @title: 封装pivotalCloud项目-EnableHikariAutoConfiguration类
 * @content: EnableHikariAutoConfiguration
 * @description: pivotalCloud项目-EnableHikariAutoConfiguration类,主要用作EnableHikariAutoConfiguration。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 12:38
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DynamicHikariDatasourceProperties.class)
@AutoConfigureBefore(value = DataSourceAutoConfiguration.class, name = "com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure")
@Import(value = {
        HikariDatasourceCreatorAutoConfig.class,
        HikariDatasourceAspectAutoConfig.class,
        HikariDatasourceAssistAutoConfig.class})
@ConditionalOnProperty(prefix = DynamicHikariDatasourceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class EnableHikariAutoConfiguration implements InitializingBean {

    private final DynamicHikariDatasourceProperties properties;


    private final List<DynamicHikariDatasourceCustomizer> customizers;

    public EnableHikariAutoConfiguration(
            DynamicHikariDatasourceProperties properties,
            ObjectProvider<List<DynamicHikariDatasourceCustomizer>> customizers) {
        this.properties = properties;
        this.customizers = customizers.getIfAvailable();
    }


    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DynamicRoutingSwitchDatasource dataSource = new DynamicRoutingSwitchDatasource();
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(properties.getStrategy());
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!CollectionUtils.isEmpty(customizers)) {
            for (DynamicHikariDatasourceCustomizer customizer : customizers) {
                customizer.customize(properties);
            }
        }
    }
}
