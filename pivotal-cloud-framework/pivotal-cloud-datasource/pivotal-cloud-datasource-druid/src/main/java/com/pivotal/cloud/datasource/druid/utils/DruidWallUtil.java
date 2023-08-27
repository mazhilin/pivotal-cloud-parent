package com.pivotal.cloud.datasource.druid.utils;

import com.alibaba.druid.wall.WallConfig;

import com.pivotal.cloud.datasource.boot.utils.DatasourceToolsUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.druid.utils.DruidWallUtil
 * @projectName: pivotalCloud
 * @className: DruidWallUtil
 * @title: 封装pivotalCloud项目-DruidWallConfigUtil类
 * @content: DruidWallUtil
 * @description: pivotalCloud项目-DruidWallConfigUtil类,主要用作DruidWallConfigUtil。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 16:22
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DruidWallUtil {
    private static final Map<String, Method> METHODS = DatasourceToolsUtil.getSetterMethods(WallConfig.class);

    /**
     * 根据当前的配置和全局的配置生成druid防火墙配置
     *
     * @param c 当前配置
     * @param g 全局配置
     * @return 防火墙配置
     */
    public static WallConfig toWallConfig(Map<String, Object> c, Map<String, Object> g) {
        WallConfig wallConfig = new WallConfig();
        Map<String, Object> map = DatasourceToolsUtil.mergeConfig(c, g);
        Object dir = map.get("dir");
        if (dir != null) {
            wallConfig.loadConfig(String.valueOf(dir));
        }
        for (Map.Entry<String, Object> item : map.entrySet()) {
            String key = DatasourceToolsUtil.lineToUpper(item.getKey());
            Method method = METHODS.get(key);
            if (method != null) {
                try {
                    method.invoke(wallConfig, DatasourceToolsUtil.convertValue(method, item.getValue()));
                } catch (Exception e) {
                    log.warn("druid wall set param {} error", key, e);
                }
            } else {
                log.warn("druid wall does not have param {}", key);
            }
        }
        return wallConfig;
    }
}
