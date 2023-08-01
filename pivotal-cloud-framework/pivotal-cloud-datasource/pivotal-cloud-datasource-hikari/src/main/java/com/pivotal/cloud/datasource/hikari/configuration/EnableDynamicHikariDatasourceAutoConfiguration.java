package com.pivotal.cloud.datasource.hikari.configuration;

import com.pivotal.cloud.datasource.boot.dynamic.DynamicRoutingSwitchDatasource;
import com.pivotal.cloud.datasource.hikari.config.HikariDatasourceAspectAutoConfig;
import com.pivotal.cloud.datasource.hikari.config.HikariDatasourceAssistAutoConfig;
import com.pivotal.cloud.datasource.hikari.config.HikariDatasourceCreatorAutoConfig;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * @className: com.pivotal.cloud.datasource.hikari.configuration.EnableDynamicHikariDatasourceAutoConfiguration
 * @title: 封装pivotalCloud项目-EnableDynamicHikariDatasourceAutoConfiguration类
 * @description: <p>
 *         pivotalCloud项目-EnableDynamicHikariDataSourceAutoConfiguration
 *         </p>
 * @content: EnableDynamicHikariDataSourceAutoConfiguration
 * @author: Powered by marklin
 * @datetime: 2023-06-02 00:25
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */

@EnableAutoConfiguration
@Configuration
@EnableConfigurationProperties(DynamicHikariDatasourceProperties.class)
@Import(value = {HikariDatasourceCreatorAutoConfig.class, HikariDatasourceAssistAutoConfig.class, HikariDatasourceAspectAutoConfig.class})
public class EnableDynamicHikariDatasourceAutoConfiguration implements InitializingBean {

    protected final DynamicHikariDatasourceProperties properties;

    public EnableDynamicHikariDatasourceAutoConfiguration(DynamicHikariDatasourceProperties properties) {
        this.properties = properties;
    }


    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DynamicRoutingSwitchDatasource datasource = new DynamicRoutingSwitchDatasource();
        datasource.setPrimary(properties.getPrimary());
        datasource.setStrict(properties.getStrict());
        datasource.setStrategy(properties.getStrategy());
        datasource.setP6spy(properties.getP6spy());
        datasource.setSeata(properties.getSeata());
        return datasource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
