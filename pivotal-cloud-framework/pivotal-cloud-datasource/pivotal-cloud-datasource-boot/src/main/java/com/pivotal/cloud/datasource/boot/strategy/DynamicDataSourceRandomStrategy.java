package com.pivotal.cloud.datasource.boot.strategy;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @packageName com.coocaa.cloud.datasource.boot.strategy.DynamicDataSourceRandomStrategy
 * @projectName: CoocaaCloud
 * @className: DynamicDataSourceRandomStrategy
 * @title: 封装CoocaaCloud项目-DynamicDataSourceRandomStrategy类
 * @content: DynamicDataSourceRandomStrategy
 * @description: CoocaaCloud项目-DynamicDataSourceRandomStrategy类,主要用作DynamicDataSourceRandomStrategy。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 19:02
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CoocaaCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDataSourceRandomStrategy implements DynamicDatasourceStrategy{

    /**
     * determine a database from the given dataSources
     *
     * @param identifiers
     *         given dataSources
     *
     * @return final dataSource
     */
    @Override
    public String definiteDatasource(List<String> identifiers) {
        return identifiers.get(ThreadLocalRandom.current().nextInt(identifiers.size()));
    }
}
