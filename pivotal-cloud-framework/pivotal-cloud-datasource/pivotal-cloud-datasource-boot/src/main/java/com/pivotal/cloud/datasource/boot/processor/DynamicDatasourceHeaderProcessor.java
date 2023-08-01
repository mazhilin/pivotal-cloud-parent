package com.pivotal.cloud.datasource.boot.processor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceHeaderProcessor
 * @title: 封装pivotalCloud项目-DynamicDatasourceHeaderProcessor类
 * @description: <p>
 *         pivotalCloud项目-DynamicDatasourceHeaderProcessor
 *         </p>
 * @content: DynamicDatasourceHeaderProcessor
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:42
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatasourceHeaderProcessor extends DynamicDatasourceProcessor{
    /**
     * header prefix
     */
    private static final String HEADER_PREFIX = "#header";

    @Override
    public boolean matches(String key) {
        return key.startsWith(HEADER_PREFIX);
    }

    @Override
    public String finalize(MethodInvocation invocation, String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(key.substring(8));
    }
}
