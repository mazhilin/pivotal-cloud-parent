package com.pivotal.cloud.datasource.boot.transaction;

/**
 * @packageName com.pivotal.cloud.datasource.boot.transaction.TransactionalExecutor
 * @projectName: pivotalCloud
 * @className: TransactionalExecutor
 * @title: 封装pivotalCloud项目-TransactionalExecutor类
 * @content: TransactionalExecutor
 * @description: pivotalCloud项目-TransactionalExecutor类,主要用作TransactionalExecutor。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 10:15
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface TransactionalExecutor {
    Object execute() throws Throwable;

    TransactionInfo getTransactionInfo();
}
