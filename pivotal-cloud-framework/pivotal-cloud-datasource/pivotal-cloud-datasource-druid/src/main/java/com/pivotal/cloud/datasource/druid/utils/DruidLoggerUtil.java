package com.pivotal.cloud.datasource.druid.utils;

import com.alibaba.druid.filter.logging.LogFilter;
import com.pivotal.cloud.datasource.boot.utils.DatasourceToolsUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.druid.utils.DruidLoggerUtil
 * @projectName: pivotalCloud
 * @className: DruidLoggerUtil
 * @title: 封装pivotalCloud项目-DruidLoggerUtil类
 * @content: DruidLoggerUtil
 * @description: pivotalCloud项目-DruidLoggerUtil类,主要用作DruidLoggerUtil。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 16:25
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DruidLoggerUtil {
    private static final Map<String, Method> METHODS = DatasourceToolsUtil.getSetterMethods(LogFilter.class);

    /**
     * 根据当前的配置和全局的配置生成druid的日志filter
     *
     * @param c 当前配置
     * @param g 全局配置
     * @return 日志filter
     */
    public static LogFilter initFilter(Class<? extends LogFilter> clazz, Map<String, Object> c, Map<String, Object> g) {
        try {
            LogFilter filter = clazz.newInstance();
            Map<String, Object> params = DatasourceToolsUtil.mergeConfig(c, g);
            for (Map.Entry<String, Object> item : params.entrySet()) {
                String key = DatasourceToolsUtil.lineToUpper(item.getKey());
                Method method = METHODS.get(key);
                if (method != null) {
                    try {
                        method.invoke(filter, DatasourceToolsUtil.convertValue(method, item.getValue()));
                    } catch (Exception e) {
                        log.warn("druid {} set param {} error", clazz.getName(), key, e);
                    }
                } else {
                    log.warn("druid {} does not have param {}", clazz.getName(), key);
                }
            }
            return filter;
        } catch (Exception e) {
            return null;
        }
    }
}
