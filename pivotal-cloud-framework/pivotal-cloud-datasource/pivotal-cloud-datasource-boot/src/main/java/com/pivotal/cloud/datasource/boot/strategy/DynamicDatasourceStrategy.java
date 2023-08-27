package com.pivotal.cloud.datasource.boot.strategy;

import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceStrategy
 * @title: 封装pivotalCloud项目-DynamicDatasourceStrategy类
 * @content: DynamicDatasourceStrategy
 * @description: pivotalCloud项目-DynamicDatasourceStrategy类,主要用作DynamicDatasourceStrategy。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 15:00
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DynamicDatasourceStrategy {
    /**
     * define a database from the given dataSources
     *
     * @param identifiers  given datasources
     * @return final datasource
     */
    String defineDatasource(List<String> identifiers);
}
