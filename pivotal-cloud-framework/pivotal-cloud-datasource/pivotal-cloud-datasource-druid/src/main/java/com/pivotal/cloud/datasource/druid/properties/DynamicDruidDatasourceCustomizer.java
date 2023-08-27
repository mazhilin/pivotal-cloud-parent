package com.pivotal.cloud.datasource.druid.properties;

/**
 * @packageName com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceCustomizer
 * @projectName: pivotalCloud
 * @className: DynamicDruidDatasourceCustomizer
 * @title: 封装pivotalCloud项目-DynamicDruidDatasourceCustomizer类
 * @content: DynamicDruidDatasourceCustomizer
 * @description: pivotalCloud项目-DynamicDruidDatasourceCustomizer类,主要用作DynamicDruidDatasourceCustomizer。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 15:14
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DynamicDruidDatasourceCustomizer {
    /**
     * Druid-动态数据源属性自定义配置
     * @param properties 属性配置文件
     */
    void customize(DynamicDruidDatasourceProperties properties);
}
