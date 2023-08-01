package com.pivotal.cloud.datasource.boot.transaction;

import com.pivotal.cloud.datasource.boot.utils.TransactionInfo;

/**
 * @className: com.pivotal.cloud.datasource.boot.transaction.TransactionalExecutor
 * @title: 封装pivotalCloud项目-TransactionalExecutor类
 * @description: <p>
 *         pivotalCloud项目-TransactionThreadExecutor
 *         </p>
 * @content: TransactionThreadExecutor
 * @author: Powered by marklin
 * @datetime: 2023-06-06 00:44
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface TransactionalExecutor {

    Object execute() throws Throwable;

    TransactionInfo getTransactionInfo();
}
