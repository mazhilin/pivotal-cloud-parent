package com.pivotal.cloud.datasource.boot.annotation;

import java.lang.annotation.*;

/**
 * @className: com.pivotal.cloud.datasource.boot.annotation.DynamicDatasource
 * @title: 封装pivotalCloud项目-DynamicDatasource类
 * @description: <p>
 *         pivotalCloud项目-DynamicDatasource
 *         </p>
 * @content: DynamicDatasource
 * @author: Powered by marklin
 * @datetime: 2023-06-02 01:51
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDatasource {
    /**
     * 数据库组或者数据值- groupName or specific database name or spring SPEL name.
     * @return 返回数据库组
     */
    String value();
}
