package com.pivotal.cloud.datasource.boot.creator;

import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;

import javax.sql.DataSource;

/**
 * @packageName com.pivotal.cloud.datasource.boot.creator.DatasourceCreator
 * @projectName: pivotalCloud
 * @className: DatasourceCreator
 * @title: 封装pivotalCloud项目-DatasourceCreator类
 * @content: DatasourceCreator
 * @description: pivotalCloud项目-DatasourceCreator类,主要用作DatasourceCreator。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 11:04
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DatasourceCreator {

    /**
     * 通过属性文件创建数据源
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
