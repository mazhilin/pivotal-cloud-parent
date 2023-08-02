package com.pivotal.cloud.datacache.jetcache.config;

import com.pivotal.cloud.datacache.jetcache.properties.DynamicJetcacheDatacacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @className: com.pivotal.cloud.datacache.jetcache.config.JetcacheDatacacheAssistAutoConfig
 * @projectName: 封装PivotalCloud项目-JetcacheDatacacheAssistAutoConfig类
 * @module: PivotalCloud项目-JetcacheDatacacheAssistAutoConfig类，主要位于JetcacheDatacacheAssistAutoConfig模块的业务场景
 * @content: JetcacheDatacacheAssistAutoConfig类，主要用于完成JetcacheDatacacheAssistAutoConfig的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-03 02:54
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicJetcacheDatacacheProperties.class)
public class JetcacheDatacacheAssistAutoConfig {
    private final DynamicJetcacheDatacacheProperties properties;
}
