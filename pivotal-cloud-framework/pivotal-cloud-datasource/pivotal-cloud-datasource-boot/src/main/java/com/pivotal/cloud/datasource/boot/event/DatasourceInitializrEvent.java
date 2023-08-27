package com.pivotal.cloud.datasource.boot.event;



import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;

import javax.sql.DataSource;

/**
 * @packageName com.pivotal.cloud.datasource.boot.event.DatasourceInitializrEvent
 * @projectName: pivotalCloud
 * @className: DatasourceInitializrEvent
 * @title: 封装pivotalCloud项目-DatasourceInitializrEvent类
 * @content: DatasourceInitializrEvent
 * @description: pivotalCloud项目-DatasourceInitializrEvent类,主要用作DatasourceInitializrEvent。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:20
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DatasourceInitializrEvent {
    /**
     * 连接池创建前执行（可用于参数解密）
     *
     * @param properties 数据源基础信息
     */
    void beforeCreate(DatasourceProperties properties);

    /**
     * 连接池创建后执行
     *
     * @param dataSource 连接池
     */
    void afterCreate(DataSource dataSource);
}
