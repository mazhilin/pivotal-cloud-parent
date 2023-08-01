package com.pivotal.cloud.datasource.boot.exception;

/**
 * @className: com.pivotal.cloud.datasource.boot.exception.DatasourceException
 * @title: 封装pivotalCloud项目-DatasourceException类
 * @description: <p>
 *         pivotalCloud项目-DatasourceException
 *         </p>
 * @content: DatasourceException
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:23
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DatasourceException extends RuntimeException {
    public DatasourceException(String message) {
        super(message);
    }

    public DatasourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
