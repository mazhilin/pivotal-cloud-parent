package com.pivotal.cloud.datasource.boot.aspect;

import com.pivotal.cloud.datasource.boot.annotation.DynamicDatasourceTransactional;
import com.pivotal.cloud.datasource.boot.transaction.TransactionInfo;
import com.pivotal.cloud.datasource.boot.transaction.TransactionTemplate;
import com.pivotal.cloud.datasource.boot.transaction.TransactionalExecutor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @packageName com.pivotal.cloud.datasource.boot.aspect.DynamicDatasourceTransactionInterceptor
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceTransactionInterceptor
 * @title: 封装pivotalCloud项目-DynamicDatasourceTransactionInterceptor 类
 * @content: DynamicDatasourceTransactionInterceptor
 * @description: pivotalCloud项目-DynamicDatasourceTransactionInterceptor类,主要用作DynamicDatasourceTransactionInterceptor。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 10:27
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DynamicDatasourceTransactionInterceptor implements MethodInterceptor {
    private final TransactionTemplate template = new TransactionTemplate();

    @Override
    public Object invoke(final MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        final DynamicDatasourceTransactional transaction = method.getAnnotation(DynamicDatasourceTransactional.class);

        TransactionalExecutor transactionalExecutor = new TransactionalExecutor() {
            @Override
            public Object execute() throws Throwable {
                return methodInvocation.proceed();
            }

            @Override
            public TransactionInfo getTransactionInfo() {
                TransactionInfo transactionInfo = new TransactionInfo();
                transactionInfo.setPropagation(transaction.propagation());
                transactionInfo.setNoRollbackFor(transaction.noRollbackFor());
                transactionInfo.setRollbackFor(transaction.rollbackFor());
                return transactionInfo;
            }
        };
        return template.execute(transactionalExecutor);
    }
}
