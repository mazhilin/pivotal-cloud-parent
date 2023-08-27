package com.pivotal.cloud.datacache.boot.holder;

/**
 * @className: com.pivotal.cloud.datacache.boot.holder.DynamicDatacacheHolder
 * @projectName: 封装PivotalCloud项目-DynamicDatacacheHolder类
 * @module: PivotalCloud项目-DynamicDatacacheHolder类，主要位于DynamicDatacacheHolder模块的业务场景
 * @content: DynamicDatacacheHolder类，主要用于完成DynamicDatacacheHolder的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-28 02:02
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public class DynamicDatacacheHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataCacheKey(String dataSourceKey) {
        CONTEXT_HOLDER.set(dataSourceKey);
    }

    public static String setDataCacheKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataCacheKey() {
        CONTEXT_HOLDER.remove();
    }
}
