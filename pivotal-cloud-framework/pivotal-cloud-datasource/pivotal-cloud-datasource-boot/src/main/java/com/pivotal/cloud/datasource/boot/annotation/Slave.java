package com.pivotal.cloud.datasource.boot.annotation;

import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;

import java.lang.annotation.*;

/**
 * @packageName com.pivotal.cloud.datasource.boot.annotation.Slave
 * @projectName: pivotalCloud
 * @className: Slave
 * @title: 封装pivotalCloud项目-Slave类
 * @content: Slave
 * @description: pivotalCloud项目-Slave类,主要用作Slave。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:36
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DynamicDatasource(DatasourceGroup.SLAVE)
public @interface Slave {}
