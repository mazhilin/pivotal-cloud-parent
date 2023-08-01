package com.pivotal.cloud.datasource.boot.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceSessionProcessor
 * @title: 封装pivotalCloud项目-DynamicDatasourceSessionProcessor类
 * @description: <p>
 *         pivotalCloud项目-DynamicDatasourceSessionProcessor
 *         </p>
 * @content: DynamicDatasourceSessionProcessor
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:45
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatasourceSessionProcessor extends DynamicDatasourceProcessor{
    /**
     * session开头
     */
    private static final String SESSION_PREFIX = "#session";

    @Override
    public boolean matches(String key) {
        return key.startsWith(SESSION_PREFIX);
    }

    @Override
    public String finalize(MethodInvocation invocation, String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession().getAttribute(key.substring(9)).toString();
    }
}
