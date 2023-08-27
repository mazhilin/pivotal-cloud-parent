package com.pivotal.cloud.datasource.boot.processor;


import org.aopalliance.intercept.MethodInvocation;

/**
 * @packageName com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceProcessor
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceProcessor
 * @title: 封装pivotalCloud项目-DynamicDatasourceProcessor类
 * @content: DynamicDatasourceProcessor
 * @description: pivotalCloud项目-DynamicDatasourceProcessor类,主要用作DynamicDatasourceProcessor。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 15:13
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public abstract class DynamicDatasourceProcessor implements DatasourceProcessor {

    private DynamicDatasourceProcessor nextProcessor;

    public void setNextProcessor(DynamicDatasourceProcessor processor) {
        this.nextProcessor = processor;
    }

    /**
     * 抽象匹配条件 匹配才会走当前执行器否则走下一级执行器
     *
     * @param identifier @DynamicDatasource注解里的内容
     * @return 是否匹配
     */
    public abstract boolean matches(String identifier);

    /**
     * 确定数据源
     * @param invocation 调用方
     * @param identifier 数据源
     * @return 返回结果
     */
    @Override
    public String definiteDatasource(MethodInvocation invocation, String identifier) {
        if (matches(identifier)) {
            String datasource = finalizeDatasource(invocation, identifier);
            if (datasource == null && nextProcessor != null) {
                return nextProcessor.definiteDatasource(invocation, identifier);
            }
            return datasource;
        }
        if (nextProcessor != null) {
            return nextProcessor.definiteDatasource(invocation, identifier);
        }
        return null;
    }


    /**
     * 抽象最终决定数据源
     *
     * @param invocation 方法执行信息
     * @param identifier        DS注解里的内容
     * @return 数据源名称
     */
    public abstract String finalizeDatasource(MethodInvocation invocation, String identifier);
}
