package com.pivotal.cloud.datacache.boot.annotation;

import java.lang.annotation.*;

/**
 * @className: com.pivotal.cloud.datacache.boot.annotation.DynamicDatacache
 * @projectName: 封装PivotalCloud项目-DynamicDatacache类
 * @module: PivotalCloud项目-DynamicDatacache类，主要位于DynamicDatacache模块的业务场景
 * @content: DynamicDatacache类，主要用于完成DynamicDatacache的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-28 01:39
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDatacache {
}
