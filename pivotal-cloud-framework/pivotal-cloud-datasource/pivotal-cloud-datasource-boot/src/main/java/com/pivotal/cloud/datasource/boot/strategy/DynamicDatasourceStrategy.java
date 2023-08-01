package com.pivotal.cloud.datasource.boot.strategy;

import java.util.List;

/**
 * @className: com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy
 * @title: 封装pivotalCloud项目-DynamicDatasourceStrategy类
 * @description: <p>
 *         pivotalCloud项目-DynamicDatasourceStrategy
 *         </p>
 * @content: DynamicDatasourceStrategy
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:48
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@FunctionalInterface
public interface DynamicDatasourceStrategy {

    /**
     * determine a database from the given dataSources
     *
     * @param identifiers
     *         given dataSources
     *
     * @return final dataSource
     */
    String definiteDatasource(List<String> identifiers);
}
