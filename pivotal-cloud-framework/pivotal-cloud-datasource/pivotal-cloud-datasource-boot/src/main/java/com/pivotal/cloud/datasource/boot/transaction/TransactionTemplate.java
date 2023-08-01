package com.pivotal.cloud.datasource.boot.transaction;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.pivotal.cloud.datasource.boot.constants.Propagation;
import com.pivotal.cloud.datasource.boot.exception.TransactionException;
import com.pivotal.cloud.datasource.boot.utils.TransactionInfo;
import com.pivotal.cloud.datasource.boot.utils.TransactionToolsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @className: com.pivotal.cloud.datasource.boot.transaction.TransactionalTemplate
 * @title: 封装pivotalCloud项目-TransactionalTemplate类
 * @description: <p>
 *         pivotalCloud项目-TransactionalTemplate
 *         </p>
 * @content: TransactionalTemplate
 * @author: Powered by marklin
 * @datetime: 2023-06-06 00:38
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class TransactionTemplate {
    public Object execute(TransactionalExecutor transactionalExecutor) throws Throwable {
        TransactionInfo transaction = transactionalExecutor.getTransactionInfo();
        Propagation propagation = transaction.propagation;
        SuspendedResourcesHolder suspendedResourcesHolder = null;
        try {
            switch (propagation) {
                case NOT_SUPPORTED:
                    // If transaction is existing, suspend it.
                    if (existingTransaction()) {
                        suspendedResourcesHolder = suspend();
                    }
                    return transactionalExecutor.execute();
                case REQUIRES_NEW:
                    // If transaction is existing, suspend it, and then begin new transaction.
                    if (existingTransaction()) {
                        suspendedResourcesHolder = suspend();
                    }
                    // Continue and execute with new transaction
                    break;
                case SUPPORTS:
                    // If transaction is not existing, execute without transaction.
                    if (!existingTransaction()) {
                        return transactionalExecutor.execute();
                    }
                    // Continue and execute with new transaction
                    break;
                case REQUIRED:
                    // default
                    break;
                case NEVER:
                    // If transaction is existing, throw exception.
                    if (existingTransaction()) {
                        throw new TransactionException("Existing transaction found for transaction marked with propagation never");
                    } else {
                        // Execute without transaction and return.
                        return transactionalExecutor.execute();
                    }
                case MANDATORY:
                    // If transaction is not existing, throw exception.
                    if (!existingTransaction()) {
                        throw new TransactionException("No existing transaction found for transaction marked with propagation 'mandatory'");
                    }
                    // Continue and execute with current transaction.
                    break;
                case NESTED:
                    // If transaction is existing,Open a save point for child transaction rollback.
                    if (existingTransaction()) {
                        ConnectionFactory.createSavepoint(TransactionContext.getXID());
                    }
                    // Continue and execute with current transaction.
                    break;
                default:
                    throw new TransactionException("Not Supported Propagation:" + propagation);
            }
            return doExecute(transactionalExecutor);
        } finally {
            resume(suspendedResourcesHolder);
        }
    }

    private Object doExecute(TransactionalExecutor transactionalExecutor) throws Throwable {
        TransactionInfo transactionInfo = transactionalExecutor.getTransactionInfo();
        Propagation propagation = transactionInfo.propagation;
        if (!StringUtils.isEmpty(TransactionContext.getXID()) && !propagation.equals(Propagation.NESTED)) {
            return transactionalExecutor.execute();
        }
        boolean state = true;
        Object o;
        String xid = TransactionToolsUtil.startTransaction();
        try {
            o = transactionalExecutor.execute();
        } catch (Exception e) {
            state = !isRollback(e, transactionInfo);
            throw e;
        } finally {
            if (state) {
                TransactionToolsUtil.commit(xid);
            } else {
                TransactionToolsUtil.rollback(xid);
            }
        }
        return o;
    }

    private boolean isRollback(Throwable e, TransactionInfo transaction) {
        boolean isRollback = true;
        Class<? extends Throwable>[] rollbacks = transaction.rollbackFor;
        Class<? extends Throwable>[] noRollbackFor = transaction.noRollbackFor;
        if (ArrayUtils.isNotEmpty(noRollbackFor)) {
            for (Class<? extends Throwable> noRollBack : noRollbackFor) {
                int depth = getDepth(e.getClass(), noRollBack);
                if (depth >= 0) {
                    return false;
                }
            }
        }
        if (ArrayUtils.isNotEmpty(rollbacks)) {
            for (Class<? extends Throwable> rollback : rollbacks) {
                int depth = getDepth(e.getClass(), rollback);
                if (depth >= 0) {
                    return isRollback;
                }
            }
        }
        return false;
    }

    private int getDepth(Class<?> exceptionClass, Class<? extends Throwable> rollback) {
        if (rollback == Throwable.class || rollback == Exception.class) {
            return 0;
        }
        // If we've gone as far as we can go and haven't found it...
        if (exceptionClass == Throwable.class) {
            return -1;
        }
        if (Objects.equals(exceptionClass, rollback)) {
            return 0;
        }
        return getDepth(exceptionClass.getSuperclass(), rollback);
    }

    private void resume(SuspendedResourcesHolder suspendedResourcesHolder) {
        if (suspendedResourcesHolder != null) {
            String xid = suspendedResourcesHolder.getXid();
            TransactionContext.bind(xid);
        }
    }

    public SuspendedResourcesHolder suspend() {
        String xid = TransactionContext.getXID();
        if (xid != null) {
            if (log.isInfoEnabled()) {
                log.info("Suspending current transaction, xid = {}", xid);
            }
            TransactionContext.unbind(xid);
            return new SuspendedResourcesHolder(xid);
        } else {
            return null;
        }
    }

    public boolean existingTransaction() {
        return !StringUtils.isEmpty(TransactionContext.getXID());
    }
}
