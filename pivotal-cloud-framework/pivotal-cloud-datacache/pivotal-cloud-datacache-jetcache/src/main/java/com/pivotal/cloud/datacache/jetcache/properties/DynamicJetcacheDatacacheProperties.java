package com.pivotal.cloud.datacache.jetcache.properties;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className: com.pivotal.cloud.datacache.jetcache.properties.DynamicJetcacheDatacacheProperties
 * @projectName: 封装PivotalCloud项目-DynamicJetcacheDatacacheProperties类
 * @module: PivotalCloud项目-DynamicJetcacheDatacacheProperties类，主要位于DynamicJetcacheDatacacheProperties模块的业务场景
 * @content: DynamicJetcacheDatacacheProperties类，主要用于完成DynamicJetcacheDatacacheProperties的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-03 02:57
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicJetcacheDatacacheProperties.PREFIX)
public class DynamicJetcacheDatacacheProperties {

    /**
     * Druid数据库连接池-属性前缀
     */
    public static final String PREFIX = "pivotal.cloud.dynamic.jetcache.datacache";


}
