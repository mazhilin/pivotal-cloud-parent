package com.pivotal.cloud.datasource.druid.config;

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

/**
 * @className: com.pivotal.cloud.datasource.druid.config.DynamicDruidDatasourceAssistAutoConfig
 * @title: 封装pivotalCloud项目-DynamicDruidDatasourceAssistAutoConfig类
 * @description: <p>
 *         pivotalCloud项目-DynamicDruidDatasourceAssistAutoConfig
 *         </p>
 * @content: DynamicDruidDatasourceAssistAutoConfig
 * @author: Powered by marklin
 * @datetime: 2023-06-05 21:39
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicDruidDatasourceProperties.class)
public class DruidDatasourceAssistAutoConfig {
    private final   DynamicDruidDatasourceProperties properties;

    @Bean
    @Order(0)
    public DynamicDatasourceProvider datasourceProvider() {
        DefaultDatasourceCreator creator = new DefaultDatasourceCreator();
        return new DynamicDatasourceBootProvider(creator,properties.getDatasource());
    }

    @Bean
    @ConditionalOnMissingBean
    public DatasourceInitializrEvent dataSourceInitEvent() {
        return new EncDatasourceInitializrEvent();
    }

}
