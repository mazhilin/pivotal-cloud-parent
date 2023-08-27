package com.pivotal.cloud.datacache.boot.creator;

import com.pivotal.cloud.datacache.boot.dynamic.DataCache;
import com.pivotal.cloud.datacache.boot.properties.DatacacheProperties;

/**
 * @className: com.pivotal.cloud.datacache.boot.creator.BasicDatacacheCreator
 * @projectName: 封装PivotalCloud项目-BasicDatacacheCreator类
 * @module: PivotalCloud项目-BasicDatacacheCreator类，主要位于BasicDatacacheCreator模块的业务场景
 * @content: BasicDatacacheCreator类，主要用于完成BasicDatacacheCreator的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-28 03:09
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public class BasicDatacacheCreator implements DatacacheCreator  {

    /**
     * 通过属性文件创建数据源
     *
     * @param properties
     *         数据源属性
     *
     * @return 被创建的数据源
     */
    @Override
    public DataCache createDatacache(DatacacheProperties properties) {
        return null;
    }

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties
     *         数据源属性
     *
     * @return 是否支持
     */
    @Override
    public boolean support(DatacacheProperties properties) {
        return false;
    }
}
