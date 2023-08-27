package com.pivotal.cloud.datasource.druid.config;


import com.pivotal.cloud.datasource.boot.creator.DatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.event.DatasourceInitializrEvent;
import com.pivotal.cloud.datasource.boot.event.EncDatasourceInitializrEvent;
import com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceBootProvider;
import com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceProvider;
import com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.druid.config.DruidDatasourceAssistAutoConfig
 * @projectName: pivotalCloud
 * @className: DruidDatasourceAssistAutoConfig
 * @title: 封装pivotalCloud项目-DruidDatasourceAssistConfig类
 * @content: DruidDatasourceAssistAutoConfig
 * @description: pivotalCloud项目-DruidDatasourceAssistConfig类,主要用作DruidDatasourceAssistConfig。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 20:03
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicDruidDatasourceProperties.class)
public class DruidDatasourceAssistAutoConfig {

    private final DynamicDruidDatasourceProperties properties;

    @Bean
    @Order(0)
    public DynamicDatasourceProvider datasourceProvider(DefaultDatasourceCreator creator) {
        return new DynamicDatasourceBootProvider(creator, properties.getDatabase());
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


    @Bean
    @ConditionalOnMissingBean
    public DatasourceInitializrEvent datasourceInitializrEvent() {
        return new EncDatasourceInitializrEvent();
    }

}
