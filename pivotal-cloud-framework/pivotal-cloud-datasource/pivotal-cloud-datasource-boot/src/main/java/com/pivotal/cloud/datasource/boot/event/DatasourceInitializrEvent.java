package com.pivotal.cloud.datasource.boot.event;

import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;

import javax.sql.DataSource;

/**
 * @className: com.pivotal.cloud.datasource.boot.event.DatasourceInitializrEvent
 * @title: 封装pivotalCloud项目-DatasourceInitializrEvent类
 * @description: <p>
 *         pivotalCloud项目-DatasourceInitializrEvent
 *         </p>
 * @content: DatasourceInitializrEvent
 * @author: Powered by marklin
 * @datetime: 2023-06-03 01:26
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */

public interface DatasourceInitializrEvent {
    /**
     * 连接池创建前执行（可用于参数解密）
     *
     * @param properties 数据源基础配置信息
     */
    void beforeCreate(DatasourceProperties properties);

    /**
     * 连接池创建后执行
     *
     * @param datasource 连接池
     */
    void afterCreate(DataSource datasource);
}
