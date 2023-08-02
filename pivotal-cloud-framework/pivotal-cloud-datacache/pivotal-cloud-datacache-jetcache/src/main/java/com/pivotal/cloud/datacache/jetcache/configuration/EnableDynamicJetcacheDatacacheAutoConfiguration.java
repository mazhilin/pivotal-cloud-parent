package com.pivotal.cloud.datacache.jetcache.configuration;

import com.pivotal.cloud.datacache.jetcache.config.JetcacheDatacacheAspectAutoConfig;
import com.pivotal.cloud.datacache.jetcache.config.JetcacheDatacacheAssistAutoConfig;
import com.pivotal.cloud.datacache.jetcache.config.JetcacheDatacacheCreatorAutoConfig;
import com.pivotal.cloud.datacache.jetcache.properties.DynamicJetcacheDatacacheProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @className: com.pivotal.cloud.datacache.jetcache.configuration.EnableDynamicJetcacheDatacacheAutoConfiguration
 * @projectName: 封装PivotalCloud项目-EnableDynamicJetcacheDatacacheAutoConfiguration类
 * @module: PivotalCloud项目-EnableDynamicJetcacheDatacacheAutoConfiguration类，主要位于EnableDynamicJetcacheDatacacheAutoConfiguration模块的业务场景
 * @content: EnableDynamicJetcacheDatacacheAutoConfiguration类，主要用于完成EnableDynamicJetcacheDatacacheAutoConfiguration的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-03 03:01
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@EnableAutoConfiguration
@Configuration
@EnableConfigurationProperties(DynamicJetcacheDatacacheProperties.class)
@Import(value = {JetcacheDatacacheCreatorAutoConfig.class, JetcacheDatacacheAssistAutoConfig.class, JetcacheDatacacheAspectAutoConfig.class})
public class EnableDynamicJetcacheDatacacheAutoConfiguration implements InitializingBean {

    protected final DynamicJetcacheDatacacheProperties properties;

    public EnableDynamicJetcacheDatacacheAutoConfiguration(DynamicJetcacheDatacacheProperties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
