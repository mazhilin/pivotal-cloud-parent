package com.pivotal.cloud.datasource.boot.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * @packageName com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceHeaderProcessor
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceHeaderProcessor
 * @title: 封装pivotalCloud项目-DynamicDatasourceHeaderProcessor类
 * @content: DynamicDatasourceHeaderProcessor
 * @description:
 *     pivotalCloud项目-DynamicDatasourceHeaderProcessor类,主要用作DynamicDatasourceHeaderProcessor。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 15:31
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatasourceHeaderProcessor extends DynamicDatasourceProcessor{
    /**
     * header prefix
     */
    private static final String HEADER_PREFIX = "#header";

    /**
     * 抽象匹配条件 匹配才会走当前执行器否则走下一级执行器
     *
     * @param identifier @DynamicDatasource注解里的内容
     * @return 是否匹配
     */
    @Override
    public boolean matches(String identifier) {
        return identifier.startsWith(HEADER_PREFIX);
    }

    /**
     * 抽象最终决定数据源
     *
     * @param invocation 方法执行信息
     * @param identifier        DS注解里的内容
     * @return 数据源名称
     */
    @Override
    public String finalizeDatasource(MethodInvocation invocation, String identifier) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader(identifier.substring(8));
    }
}
