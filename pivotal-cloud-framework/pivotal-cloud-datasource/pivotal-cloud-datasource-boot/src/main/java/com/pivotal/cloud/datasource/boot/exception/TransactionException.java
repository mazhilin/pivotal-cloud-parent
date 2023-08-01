package com.pivotal.cloud.datasource.boot.exception;

/**
 * @className: com.pivotal.cloud.datasource.boot.exception.TransactionException
 * @title: 封装pivotalCloud项目-TransactionException类
 * @description: <p>
 *         pivotalCloud项目-TransactionException
 *         </p>
 * @content: TransactionException
 * @author: Powered by marklin
 * @datetime: 2023-06-06 00:53
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
