package com.pivotal.cloud.datasource.hikari.properties;

/**
 * @packageName com.coocaa.cloud.datasource.hikari.properties.DynamicHikariDatasourceCustomizer
 * @projectName: CoocaaCloud
 * @className: DynamicHikariDatasourceCustomizer
 * @title: 封装CoocaaCloud项目-DynamicHikariDatasourceCustomizer类
 * @content: DynamicHikariDatasourceCustomizer
 * @description: CoocaaCloud项目-DynamicHikariDatasourceCustomizer类,主要用作DynamicHikariDatasourceCustomizer。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 15:15
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CoocaaCloud Systems Incorporated. All rights reserved.
 */
public interface DynamicHikariDatasourceCustomizer {
    /**
     * Hikari-动态数据源属性自定义配置
     * @param properties 属性配置文件
     */
    void customize(DynamicHikariDatasourceProperties properties);
}
