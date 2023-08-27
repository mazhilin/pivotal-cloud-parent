package com.pivotal.cloud.datasource.hikari.config;

import com.pivotal.cloud.datasource.boot.creator.DatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.event.DatasourceInitializrEvent;
import com.pivotal.cloud.datasource.boot.event.EncDatasourceInitializrEvent;
import com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceBootProvider;
import com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceProvider;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.hikari.config.HikariDatasourceAssistAutoConfig
 * @projectName: pivotalCloud
 * @className: HikariDatasourceAssistAutoConfig
 * @title: 封装pivotalCloud项目-HikariDatasourceAssistAutoConfig类
 * @content: HikariDatasourceAssistAutoConfig
 * @description: pivotalCloud项目-HikariDatasourceAssistAutoConfig类,主要用作HikariDatasourceAssistAutoConfig。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 12:40
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicHikariDatasourceProperties.class)
public class HikariDatasourceAssistAutoConfig {

    private final  DynamicHikariDatasourceProperties properties;

    @Bean
    @Order(0)
    public DynamicDatasourceProvider datasourceProvider(DefaultDatasourceCreator creator) {
        return new DynamicDatasourceBootProvider(creator,properties.getDatabase());
    }

    @Bean
    @ConditionalOnMissingBean
    public DatasourceInitializrEvent dataSourceInitEvent() {
        return new EncDatasourceInitializrEvent();
    }


    @Bean
    @ConditionalOnMissingBean
    public DefaultDatasourceCreator datasourceCreator(List<DatasourceCreator> dataSourceCreators) {
        DefaultDatasourceCreator creator = new DefaultDatasourceCreator();
        creator.setCreators(dataSourceCreators);
        creator.setPublicKey(properties.getPublicKey());
        creator.setLazy(properties.getLazy());
        creator.setP6spy(properties.getP6spy());
        creator.setSeata(properties.getSeata());
        creator.setSeataMode(properties.getSeataMode());
        return creator;
    }


}
