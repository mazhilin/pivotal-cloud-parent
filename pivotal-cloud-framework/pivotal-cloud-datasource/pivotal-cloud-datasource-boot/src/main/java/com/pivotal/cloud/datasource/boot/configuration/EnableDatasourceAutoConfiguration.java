package com.pivotal.cloud.datasource.boot.configuration;

import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @className: com.pivotal.cloud.datasource.boot.configuration.EnableDatasourceAutoConfiguration
 * @title: 封装SamrtCloud项目-EnableDatasourceAutoConfiguration类
 * @description: <p>
 *         SamrtCloud项目-EnableDatasourceAutoConfiguration
 *         </p>
 * @content: EnableDatasourceAutoConfiguration
 * @author: Powered by marklin
 * @datetime: 2023-05-27 03:31
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 SamrtCloud Systems Incorporated. All rights reserved.
 */
@EnableAutoConfiguration
@EnableConfigurationProperties(value = DatasourceProperties.class)
public class EnableDatasourceAutoConfiguration {

    @Autowired
    private DatasourceProperties properties;

}
