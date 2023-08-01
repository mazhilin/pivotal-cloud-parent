package com.pivotal.cloud.application.security.config;

import com.pivotal.cloud.application.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: com.pivotal.cloud.application.security.config.SecurityConfiguration
 * @projectName: 封装PivotalCloud项目-SecurityConfiguration类
 * @module: PivotalCloud项目-SecurityConfiguration类，主要位于SecurityConfiguration模块的业务场景
 * @content: SecurityConfiguration类，主要用于完成SecurityConfiguration的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-02 03:36
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityConfiguration {

    @Autowired
    private SecurityProperties securityProperties;
}
