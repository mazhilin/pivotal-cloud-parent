package com.pivotal.cloud.datasource.boot.annotation;

import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;

import java.lang.annotation.*;

/**
 * @packageName com.pivotal.cloud.datasource.boot.annotation.Replica
 * @projectName: pivotalCloud
 * @className: Replica
 * @title: 封装pivotalCloud项目-Replica类
 * @content: Replica
 * @description: pivotalCloud项目-Replica类,主要用作Replica。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:37
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DynamicDatasource(DatasourceGroup.REPLICA)
public @interface Replica {}
