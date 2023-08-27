package com.pivotal.cloud.datasource.boot.processor;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @packageName com.pivotal.cloud.datasource.boot.processor.DatasourceProcessor
 * @projectName: pivotalCloud
 * @className: DatasourceProcessor
 * @title: 封装pivotalCloud项目-DatasourceProcessor类
 * @content: DatasourceProcessor
 * @description: pivotalCloud项目-DatasourceProcessor类,主要用作DatasourceProcessor。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 15:06
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@FunctionalInterface
public interface DatasourceProcessor {
    /**
     * 确定数据源
     * @param invocation 调用方
     * @param identifier 数据源标识符
     * @return 返回结果
     */
    String definiteDatasource(MethodInvocation invocation, String identifier);
}
