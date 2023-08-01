package com.pivotal.cloud.datasource.boot.properties;

import lombok.Data;
import org.springframework.core.Ordered;

/**
 * @className: com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties
 * @title: 封装pivotalCloud项目-DatasourceAopProperties类
 * @description: <p>
 *         pivotalCloud项目-DatasourceAopProperties
 *         </p>
 * @content: DatasourceAopProperties
 * @author: Powered by marklin
 * @datetime: 2023-06-06 01:04
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
