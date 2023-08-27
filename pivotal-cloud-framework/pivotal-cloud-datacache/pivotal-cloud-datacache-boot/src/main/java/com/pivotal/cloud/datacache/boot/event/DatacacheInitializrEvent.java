package com.pivotal.cloud.datacache.boot.event;

import com.pivotal.cloud.datacache.boot.dynamic.DataCache;
import com.pivotal.cloud.datacache.boot.properties.DatacacheProperties;

import javax.sql.DataSource;

/**
 * @className: com.pivotal.cloud.datacache.boot.event.DatacacheInitializrEvent
 * @projectName: 封装PivotalCloud项目-DatacacheInitializrEvent类
 * @module: PivotalCloud项目-DatacacheInitializrEvent类，主要位于DatacacheInitializrEvent模块的业务场景
 * @content: DatacacheInitializrEvent类，主要用于完成DatacacheInitializrEvent的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-28 02:10
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DatacacheInitializrEvent {

    /**
     * 连接池创建前执行（可用于参数解密）
     *
     * @param properties 数据源基础信息
     */
    void beforeCreate(DatacacheProperties properties);

    /**
     * 连接池创建后执行
     *
     * @param dataCache 连接池
     */
    void afterCreate(DataCache dataCache);
}
