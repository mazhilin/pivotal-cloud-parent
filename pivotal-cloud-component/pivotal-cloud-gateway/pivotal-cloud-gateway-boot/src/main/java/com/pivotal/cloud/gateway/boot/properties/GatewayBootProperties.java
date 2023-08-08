package com.pivotal.cloud.gateway.boot.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className: com.pivotal.cloud.gateway.boot.properties.GatewayBootProperties
 * @projectName: 封装PivotalCloud项目-PivotalCloudGatewayProperties类
 * @module: PivotalCloud项目-PivotalCloudGatewayProperties类，主要位于PivotalCloudGatewayProperties模块的业务场景
 * @content: PivotalCloudGatewayProperties类，主要用于完成PivotalCloudGatewayProperties的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-08 02:24
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
@Component
@Slf4j
@ConfigurationProperties(prefix = GatewayBootProperties.PREFIX)
public class GatewayBootProperties {
    /**
     * Druid数据库连接池-属性前缀
     */
    public static final String PREFIX = "pivotal.cloud.gateway";

    /**
     * 是否校验总开关：true : 开启   false:关闭
     */
    private Boolean authEnabled;

    /**
     * 排除的鉴权接口路径
     */
    private List<String> excludePath;
}
