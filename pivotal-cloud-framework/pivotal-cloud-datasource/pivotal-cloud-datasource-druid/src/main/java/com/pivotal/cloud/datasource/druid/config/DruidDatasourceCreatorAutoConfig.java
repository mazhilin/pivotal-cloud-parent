package com.pivotal.cloud.datasource.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.pivotal.cloud.datasource.boot.creator.BasicDatasourceCreator;
import com.pivotal.cloud.datasource.boot.creator.JndiDatasourceCreator;
import com.pivotal.cloud.datasource.druid.creator.DruidDatasourceCreator;
import com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @packageName com.pivotal.cloud.datasource.druid.config.DruidDatasourceCreatorAutoConfig
 * @projectName: pivotalCloud
 * @className: DruidDatasourceCreatorAutoConfig
 * @title: 封装pivotalCloud项目-DynamicDataSourceCreatorAutoConfig类
 * @content: DruidDatasourceCreatorAutoConfig
 * @description:
 *     pivotalCloud项目-DynamicDataSourceCreatorAutoConfig类,主要用作DynamicDataSourceCreatorAutoConfig。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 19:52
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicDruidDatasourceProperties.class)
public class DruidDatasourceCreatorAutoConfig {
    private final DynamicDruidDatasourceProperties properties;

    public static final int JNDI_ORDER = 1000;
    public static final int DRUID_ORDER = 2000;
    public static final int DEFAULT_ORDER = 7000;

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
    @Order(DRUID_ORDER)
    @ConditionalOnClass(DruidDataSource.class)
    public DruidDatasourceCreator druidDataSourceCreator() {
        return new DruidDatasourceCreator(properties.getPoolConfig());
    }

}
