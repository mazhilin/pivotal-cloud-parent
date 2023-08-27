package com.pivotal.cloud.datasource.boot.transaction;

import org.springframework.util.StringUtils;

/**
 * @packageName com.pivotal.cloud.datasource.boot.transaction.TransactionContext
 * @projectName: pivotalCloud
 * @className: TransactionContext
 * @title: 封装pivotalCloud项目-TransactionContext类
 * @content: TransactionContext
 * @description: pivotalCloud项目-TransactionContext类,主要用作TransactionContext。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 12:49
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class TransactionContext {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * Gets xid.
     *
     * @return the xid
     */
    public static String getXID() {
        String xid = CONTEXT_HOLDER.get();
        if (!StringUtils.isEmpty(xid)) {
            return xid;
        }
        return null;
    }

    /**
     * Unbind string.
     *
     * @return the string
     */
    public static String unbind(String xid) {
        CONTEXT_HOLDER.remove();
        return xid;
    }

    /**
     * bind string.
     *
     * @return the string
     */
    public static String bind(String xid) {
        CONTEXT_HOLDER.set(xid);
        return xid;
    }

    /**
     * remove
     */
    public static void remove() {
        CONTEXT_HOLDER.remove();
    }
}
