package com.pivotal.cloud.datasource.boot.strategy;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @packageName com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceLoadBalanceStrategy
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceLoadBalanceStrategy
 * @title: 封装pivotalCloud项目-DynamicDatasourceLoadBalanceStrategy类
 * @content: DynamicDatasourceLoadBalanceStrategy
 * @description:
 *     pivotalCloud项目-DynamicDatasourceLoadBalanceStrategy类,主要用作DynamicDatasourceLoadBalanceStrategy。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 18:58
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatasourceLoadBalanceStrategy implements DynamicDatasourceStrategy {

    /**
     * 负载均衡计数器
     */
    private final AtomicInteger index = new AtomicInteger(0);

    /**
     * determine a database from the given dataSources
     *
     * @param identifiers
     *         given dataSources
     *
     * @return final dataSource
     */
    @Override
    public String defineDatasource(List<String> identifiers) {
        return identifiers.get(Math.abs(index.getAndAdd(1) % identifiers.size()));
    }
}
