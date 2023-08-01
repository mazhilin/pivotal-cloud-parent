package com.pivotal.cloud.datasource.boot.creator;

import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;

import javax.sql.DataSource;

/**
 * @className: com.pivotal.cloud.datasource.boot.builder.DataSourceCreatorBuilder
 * @title: 封装pivotalCloud项目-DataSourceCreatorBuilder类
 * @description: <p>
 *         pivotalCloud项目-DataSourceCreatorBuilder
 *         </p>
 * @content: DataSourceCreatorBuilder
 * @author: Powered by marklin
 * @datetime: 2023-06-02 03:57
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DatasourceCreator {
    /**
     * 通过属性创建数据源
     *
     * @param properties 数据源属性
     * @return 被创建的数据源
     */
    DataSource createDatasource(DatasourceProperties properties);

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties 数据源属性
     * @return 是否支持
     */
    boolean support(DatasourceProperties properties);
}
