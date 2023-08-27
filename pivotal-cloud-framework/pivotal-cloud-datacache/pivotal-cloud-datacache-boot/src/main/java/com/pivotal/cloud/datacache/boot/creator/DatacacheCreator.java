package com.pivotal.cloud.datacache.boot.creator;

import com.pivotal.cloud.datacache.boot.dynamic.DataCache;
import com.pivotal.cloud.datacache.boot.properties.DatacacheProperties;

import javax.sql.DataSource;

/**
 * @className: com.pivotal.cloud.datacache.boot.creator.DatacacheCreator
 * @projectName: 封装PivotalCloud项目-DatacacheCreator类
 * @module: PivotalCloud项目-DatacacheCreator类，主要位于DatacacheCreator模块的业务场景
 * @content: DatacacheCreator类，主要用于完成DatacacheCreator的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-28 02:43
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DatacacheCreator {

    /**
     * 通过属性文件创建数据源
     *
     * @param properties 数据源属性
     * @return 被创建的数据源
     */
    DataCache createDatacache(DatacacheProperties properties);

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties 数据源属性
     * @return 是否支持
     */
    boolean support(DatacacheProperties properties);
}
