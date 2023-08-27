package com.pivotal.cloud.datasource.boot.annotation;

import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;

import java.lang.annotation.*;

/**
 * @packageName com.pivotal.cloud.datasource.boot.annotation.Master
 * @projectName: pivotalCloud
 * @className: Master
 * @title: 封装pivotalCloud项目-Master类
 * @content: Master
 * @description: pivotalCloud项目-Master类,主要用作Master。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:35
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@com.pivotal.cloud.datasource.boot.annotation.DynamicDatasource(DatasourceGroup.MASTER)
public @interface Master {

}
