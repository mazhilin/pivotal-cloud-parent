package com.pivotal.cloud.datasource.boot.annotation;

import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;

import java.lang.annotation.*;

/**
 * @packageName com.pivotal.cloud.datasource.boot.annotation.DynamicDatasource
 * @projectName: pivotalCloud
 * @className: DynamicDatasource
 * @title: 封装pivotalCloud项目-DynamicDataSource类
 * @content: DynamicDatasource
 * @description: pivotalCloud项目-DynamicDataSource类,主要用作DynamicDataSource。
 * @author: Powered by Marklin
 * @datetime: 2023-05-29 15:42
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDatasource {
    String value() default DatasourceGroup.MASTER;
}
