package com.pivotal.cloud.datasource.hikari.config;

import com.pivotal.cloud.datasource.boot.creator.BasicDatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.DatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.JndiDatasourceCreator;
import com.pivotal.cloud.datasource.hikari.creator.HikariDatasourceCreator;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @className: com.pivotal.cloud.datasource.hikari.config.HikariDruidDatasourceCreatorAutoConfig
 * @title: 封装pivotalCloud项目-HikariDruidDatasourceCreatorAutoConfig类
 * @description: <p>
 *         pivotalCloud项目-HikariDruidDatasourceCreatorAutoConfig
 *         </p>
 * @content: HikariDruidDatasourceCreatorAutoConfig
 * @author: Powered by marklin
 * @datetime: 2023-06-05 23:49
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

    private final  DynamicHikariDatasourceProperties properties;


    @Bean
    @ConditionalOnMissingBean
    public DefaultDatasourceCreator datasourceCreator(List<DatasourceCreator> creators) {
        DefaultDatasourceCreator creator = new DefaultDatasourceCreator();
        creator.setCreators(creators);
        return creator;
    }

    @Bean
    @Order(DEFAULT_ORDER)
    public BasicDatasourceCreator basicDatasourceCreator() {
        return new BasicDatasourceCreator();
    }

    @Bean
    @Order(JNDI_ORDER)
    public JndiDatasourceCreator jndiDatasourceCreator() {
        return new JndiDatasourceCreator();
    }

    @Bean
    @Order(HIKARI_ORDER)
    @ConditionalOnClass(HikariDataSource.class)
    public HikariDatasourceCreator hikariDatasourceCreator() {
        return new HikariDatasourceCreator(properties.getPoolConfig());
    }
}
