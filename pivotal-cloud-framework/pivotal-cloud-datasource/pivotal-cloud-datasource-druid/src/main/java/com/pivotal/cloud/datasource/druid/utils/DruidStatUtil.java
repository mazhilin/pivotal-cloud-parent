package com.pivotal.cloud.datasource.druid.utils;

import com.alibaba.druid.filter.stat.StatFilter;

import com.pivotal.cloud.datasource.boot.utils.DatasourceToolsUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.druid.utils.DruidStatUtil
 * @projectName: pivotalCloud
 * @className: DruidStatUtil
 * @title: 封装pivotalCloud项目-DruidStatConfigUtil类
 * @content: DruidStatUtil
 * @description: pivotalCloud项目-DruidStatConfigUtil类,主要用作DruidStatConfigUtil。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 16:23
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DruidStatUtil {

    private static final Map<String, Method> METHODS = DatasourceToolsUtil.getSetterMethods(StatFilter.class);

    static {
        try {
            METHODS.put("dbType", StatFilter.class.getDeclaredMethod("setDbType", String.class));
        } catch (Exception ignore) {
        }
    }

    /**
     * 根据当前的配置和全局的配置生成druid防火墙配置
     *
     * @param c 当前配置
     * @param g 全局配置
     * @return StatFilter
     */
    public static StatFilter toStatFilter(Map<String, Object> c, Map<String, Object> g) {
        StatFilter filter = new StatFilter();
        Map<String, Object> map = DatasourceToolsUtil.mergeConfig(c, g);
        for (Map.Entry<String, Object> item : map.entrySet()) {
            String key = DatasourceToolsUtil.lineToUpper(item.getKey());
            Method method = METHODS.get(key);
            if (method != null) {
                try {
                    method.invoke(filter, DatasourceToolsUtil.convertValue(method, item.getValue()));
                } catch (Exception e) {
                    log.warn("druid stat set param {} error", key, e);
                }
            } else {
                log.warn("druid stat does not have param {}", key);
            }
        }
        return filter;
    }
}
