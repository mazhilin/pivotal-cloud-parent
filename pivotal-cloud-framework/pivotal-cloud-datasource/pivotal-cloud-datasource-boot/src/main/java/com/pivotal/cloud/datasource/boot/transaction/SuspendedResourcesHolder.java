package com.pivotal.cloud.datasource.boot.transaction;

import javax.annotation.Nonnull;

/**
 * @packageName com.pivotal.cloud.datasource.boot.transaction.SuspendedResourcesHolder
 * @projectName: pivotalCloud
 * @className: SuspendedResourcesHolder
 * @title: 封装pivotalCloud项目-SuspendedResourcesHolder类
 * @content: SuspendedResourcesHolder
 * @description: pivotalCloud项目-SuspendedResourcesHolder类,主要用作SuspendedResourcesHolder。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 10:12
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class SuspendedResourcesHolder {
    /**
     * The xid
     */
    private String xid;

    public SuspendedResourcesHolder(String xid) {
        if (xid == null) {
            throw new IllegalArgumentException("xid must be not null");
        }
        this.xid = xid;
    }

    @Nonnull
    public String getXid() {
        return xid;
    }
}
