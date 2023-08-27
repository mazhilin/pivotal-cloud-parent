package com.pivotal.cloud.datasource.druid.utils;


import com.pivotal.cloud.datasource.boot.config.DruidConfig;
import com.pivotal.cloud.datasource.boot.utils.DatasourceToolsUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;

/**
 * @packageName com.pivotal.cloud.datasource.druid.utils.DruidConfigUtil
 * @projectName: pivotalCloud
 * @className: DruidConfigUtil
 * @title: 封装pivotalCloud项目-DruidConfigUtil类
 * @content: DruidConfigUtil
 * @description: pivotalCloud项目-DruidConfigUtil类,主要用作DruidConfigUtil。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 15:17
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DruidConfigUtil {
    private static final Map<String, Method> METHODS = DatasourceToolsUtil.getGetterMethods(DruidConfig.class);

    /**
     * 根据全局配置和本地配置结合转换为Properties
     *
     * @param g 全局配置
     * @param c 当前配置
     * @return Druid配置
     */
    @SneakyThrows
    public static Properties mergeConfig(DruidConfig g, DruidConfig c) {
        Properties properties = new Properties();
        for (Map.Entry<String, Method> entry : METHODS.entrySet()) {
            String key = entry.getKey();
            Method readMethod = entry.getValue();
            Object cValue = readMethod.invoke(c);
            if (cValue != null) {
                properties.setProperty("druid." + key, String.valueOf(cValue));
                continue;
            }
            if (g != null) {
                Object gValue = readMethod.invoke(g);
                if (gValue != null) {
                    properties.setProperty("druid." + key, String.valueOf(gValue));
                }
            }
        }
        return properties;
    }
}
