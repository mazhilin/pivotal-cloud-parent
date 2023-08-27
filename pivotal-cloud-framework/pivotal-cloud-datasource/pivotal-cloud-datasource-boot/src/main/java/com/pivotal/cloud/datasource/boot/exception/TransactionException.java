package com.pivotal.cloud.datasource.boot.exception;

/**
 * @packageName com.pivotal.cloud.datasource.boot.exception.TransactionException
 * @projectName: pivotalCloud
 * @className: TransactionException
 * @title: 封装pivotalCloud项目-TransactionException类
 * @content: TransactionException
 * @description: pivotalCloud项目-TransactionException类,主要用作TransactionException。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 10:18
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
