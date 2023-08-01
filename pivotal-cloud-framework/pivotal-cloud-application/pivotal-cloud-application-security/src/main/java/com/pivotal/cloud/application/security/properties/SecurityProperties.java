package com.pivotal.cloud.application.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className: com.pivotal.cloud.application.security.properties.SecurityProperties
 * @projectName: 封装PivotalCloud项目-SecurityProperties类
 * @module: PivotalCloud项目-SecurityProperties类，主要位于SecurityProperties模块的业务场景
 * @content: SecurityProperties类，主要用于完成SecurityProperties的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-02 03:22
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {

    public static final String PREFIX = "pivotal.cloud.security.oauth";
}
