package com.pivotal.cloud.datasource.boot.strategy;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @packageName com.pivotal.cloud.datasource.boot.strategy.DynamicDataSourceRandomStrategy
 * @projectName: pivotalCloud
 * @className: DynamicDataSourceRandomStrategy
 * @title: 封装pivotalCloud项目-DynamicDataSourceRandomStrategy类
 * @content: DynamicDataSourceRandomStrategy
 * @description: pivotalCloud项目-DynamicDataSourceRandomStrategy类,主要用作DynamicDataSourceRandomStrategy。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 19:02
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDataSourceRandomStrategy implements DynamicDatasourceStrategy {
    /**
     * determine a database from the given dataSources
     *
     * @param identifiers given dataSources
     * @return final dataSource
     */
    @Override
    public String defineDatasource(List<String> identifiers) {
        return identifiers.get(ThreadLocalRandom.current().nextInt(identifiers.size()));
    }
}
