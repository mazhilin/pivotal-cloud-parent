package com.pivotal.cloud.datasource.boot.holder;
/**
 * @packageName com.pivotal.cloud.datasource.boot.holder.DynamicDataSourceHolder
 * @projectName: pivotalCloud
 * @className: DynamicDataSourceHolder
 * @title: 封装pivotalCloud项目-DynamicDataSourceHolder类
 * @content: DynamicDataSourceHolder
 * @description: pivotalCloud项目-DynamicDataSourceHolder类,主要用作DynamicDataSourceHolder。
 * @author: Powered by Marklin
 * @datetime: 2023-05-29 16:00
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceKey(String dataSourceKey) {
        CONTEXT_HOLDER.set(dataSourceKey);
    }

    public static String getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }
}
