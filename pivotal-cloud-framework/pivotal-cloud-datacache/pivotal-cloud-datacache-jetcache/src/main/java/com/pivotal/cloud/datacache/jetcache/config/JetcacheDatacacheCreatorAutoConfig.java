package com.pivotal.cloud.datacache.jetcache.config;

import com.pivotal.cloud.datacache.jetcache.properties.DynamicJetcacheDatacacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: com.pivotal.cloud.datacache.jetcache.config.JetcacheDatacacheCreatorAutoConfig
 * @projectName: 封装PivotalCloud项目-JetcacheDatacacheCreatorAutoConfig类
 * @module: PivotalCloud项目-JetcacheDatacacheCreatorAutoConfig类，主要位于JetcacheDatacacheCreatorAutoConfig模块的业务场景
 * @content: JetcacheDatacacheCreatorAutoConfig类，主要用于完成JetcacheDatacacheCreatorAutoConfig的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-03 02:55
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicJetcacheDatacacheProperties.class)
public class JetcacheDatacacheCreatorAutoConfig {
    private final DynamicJetcacheDatacacheProperties properties;
}
