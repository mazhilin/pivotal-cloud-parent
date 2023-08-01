package com.pivotal.cloud.datasource.boot.processor;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @className: com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceProcessor
 * @title: 封装pivotalCloud项目-DynamicDatasourceProcessor类
 * @description: <p>
 *         pivotalCloud项目-DynamicDatasourceProcessor
 *         </p>
 * @content: DynamicDatasourceProcessor
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:38
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public abstract class DynamicDatasourceProcessor {
    private DynamicDatasourceProcessor nextProcessor;

    public void setNextProcessor(DynamicDatasourceProcessor dsProcessor) {
        this.nextProcessor = dsProcessor;
    }

    /**
     * 抽象匹配条件 匹配才会走当前执行器否则走下一级执行器
     *
     * @param key DS注解里的内容
     * @return 是否匹配
     */
    public abstract boolean matches(String key);

    /**
     * 决定数据源
     * <pre>
     *     调用底层doDetermineDatasource，
     *     如果返回的是null则继续执行下一个，否则直接返回
     * </pre>
     *
     * @param invocation 方法执行信息
     * @param key        DS注解里的内容
     * @return 数据源名称
     */
    public String definite(MethodInvocation invocation, String key) {
        if (matches(key)) {
            String datasource = finalize(invocation, key);
            if (datasource == null && nextProcessor != null) {
                return nextProcessor.definite(invocation, key);
            }
            return datasource;
        }
        if (nextProcessor != null) {
            return nextProcessor.definite(invocation, key);
        }
        return null;
    }

    /**
     * 抽象最终决定数据源
     *
     * @param invocation 方法执行信息
     * @param key        DS注解里的内容
     * @return 数据源名称
     */
    public abstract String finalize(MethodInvocation invocation, String key);
}
