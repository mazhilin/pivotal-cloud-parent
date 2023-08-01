package com.pivotal.cloud.datasource.boot.utils;

import com.pivotal.cloud.datasource.boot.constants.Propagation;

/**
 * @className: com.pivotal.cloud.datasource.boot.utils.TransactionInfo
 * @title: 封装pivotalCloud项目-TransactionInfo类
 * @description: <p>
 *         pivotalCloud项目-TransactionInfo
 *         </p>
 * @content: TransactionInfo
 * @author: Powered by marklin
 * @datetime: 2023-06-06 00:42
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
