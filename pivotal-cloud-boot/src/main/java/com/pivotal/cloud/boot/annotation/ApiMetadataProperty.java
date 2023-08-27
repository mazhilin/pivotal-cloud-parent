package com.pivotal.cloud.boot.annotation;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: com.pivotal.cloud.boot.annotation.ApiMetadataProperty
 * @projectName: 封装PivotalCloud项目-ApiMetadataProperty类
 * @module: PivotalCloud项目-ApiMetadataProperty类，主要位于ApiMetadataProperty模块的业务场景
 * @content: ApiMetadataProperty类，主要用于完成ApiMetadataProperty的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-27 23:29
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiMetadataProperty {

    String value() default StringUtils.EMPTY;

    /**
     * 字段属性中文注释
     *
     * @return
     */
    String name() default StringUtils.EMPTY;

    /**
     * 引用
     *
     * @return
     */
    String reference() default StringUtils.EMPTY;

    /**
     * 字段属性是否为字典项代码
     *
     * @return
     */
    boolean dictionary() default false;
}
