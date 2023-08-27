package com.pivotal.cloud.datasource.boot.annotation;


import com.pivotal.cloud.datasource.boot.constants.Propagation;

import java.lang.annotation.*;

/**
 * @packageName com.pivotal.cloud.datasource.boot.annotation.DynamicDatasourceTransactional
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceTransactional
 * @title: 封装pivotalCloud项目-DynamicDatasourceTransactional类
 * @content: DynamicDatasourceTransactional
 * @description: pivotalCloud项目-DynamicDatasourceTransactional类,主要用作DynamicDatasourceTransactional。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 10:28
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDatasourceTransactional {
    Class<? extends Throwable>[] rollbackFor() default {Exception.class};

    Class<? extends Throwable>[] noRollbackFor() default {};

    Propagation propagation() default Propagation.REQUIRED;
}
