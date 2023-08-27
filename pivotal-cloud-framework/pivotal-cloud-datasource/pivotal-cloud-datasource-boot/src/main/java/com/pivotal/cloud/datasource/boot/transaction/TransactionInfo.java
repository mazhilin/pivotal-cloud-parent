package com.pivotal.cloud.datasource.boot.transaction;


import com.pivotal.cloud.datasource.boot.constants.Propagation;

/**
 * @packageName com.pivotal.cloud.datasource.boot.transaction.TransactionInfo
 * @projectName: pivotalCloud
 * @className: TransactionInfo
 * @title: 封装pivotalCloud项目-TransactionInfo类
 * @content: TransactionInfo
 * @description: pivotalCloud项目-TransactionInfo类,主要用作TransactionInfo。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 10:15
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class TransactionInfo {
    public Class<? extends Throwable>[] rollbackFor;

    public Class<? extends Throwable>[] noRollbackFor;

    public Propagation propagation;

    public Class<? extends Throwable>[] getRollbackFor() {
        return rollbackFor;
    }

    public void setRollbackFor(Class<? extends Throwable>[] rollbackFor) {
        this.rollbackFor = rollbackFor;
    }

    public Class<? extends Throwable>[] getNoRollbackFor() {
        return noRollbackFor;
    }

    public void setNoRollbackFor(Class<? extends Throwable>[] noRollbackFor) {
        this.noRollbackFor = noRollbackFor;
    }

    public Propagation getPropagation() {
        return propagation;
    }

    public void setPropagation(Propagation propagation) {
        this.propagation = propagation;
    }
}
