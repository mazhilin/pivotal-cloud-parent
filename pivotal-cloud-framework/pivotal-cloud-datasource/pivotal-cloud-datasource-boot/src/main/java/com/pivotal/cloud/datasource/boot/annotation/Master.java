package com.pivotal.cloud.datasource.boot.annotation;

import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;

import java.lang.annotation.*;

/**
 * @className: com.pivotal.cloud.datasource.boot.annotation.Master
 * @title: 封装pivotalCloud项目-Master类
 * @description: <p>
 *         pivotalCloud项目-Master
 *         </p>
 * @content: Master-主数据源自定义注解
 * @author: Powered by marklin
 * @datetime: 2023-06-02 22:09
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DynamicDatasource(DatasourceGroup.MASTER)
public @interface Master {
}
