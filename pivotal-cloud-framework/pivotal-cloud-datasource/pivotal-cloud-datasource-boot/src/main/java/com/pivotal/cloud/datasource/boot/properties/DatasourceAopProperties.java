package com.pivotal.cloud.datasource.boot.properties;

import lombok.Data;
import org.springframework.core.Ordered;

/**
 * @packageName com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties
 * @projectName: pivotalCloud
 * @className: DatasourceAopProperties
 * @title: 封装pivotalCloud项目-DynamicDatasourceAopProperties类
 * @content: DatasourceAopProperties
 * @description: pivotalCloud项目-DynamicDatasourceAopProperties类,主要用作DynamicDatasourceAopProperties。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 19:47
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class DatasourceAopProperties {

    /**
     * enabled default DS annotation default true
     */
    private Boolean enabled = true;
    /**
     * aop order
     */
    private Integer order = Ordered.HIGHEST_PRECEDENCE;
    /**
     * aop allowedPublicOnly
     */
    private Boolean allowedPublicOnly = true;
}
