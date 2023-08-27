package com.pivotal.cloud.datasource.boot.exception;
/**
 * @packageName com.pivotal.cloud.datasource.boot.exception.DynamicDatasourceRoutingException
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceRoutingException
 * @title: 封装pivotalCloud项目-DynamicDatasourceException类
 * @content: DynamicDatasourceRoutingException
 * @description: pivotalCloud项目-DynamicDatasourceException类,主要用作DynamicDatasourceException。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 19:55
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatasourceRoutingException extends RuntimeException {
    public DynamicDatasourceRoutingException(String message) {
        super(message);
    }

    public DynamicDatasourceRoutingException(String message, Throwable cause) {
        super(message, cause);
    }
}
