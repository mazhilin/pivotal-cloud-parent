package com.pivotal.cloud.boot.aop;

import com.pivotal.cloud.boot.Proxy;
import org.springframework.aop.framework.AopContext;

/**
 * @className: com.pivotal.cloud.boot.aop.ApplicationAopProxy
 * @projectName: 封装PivotalCloud项目-ApplicationAopProxy类
 * @module: PivotalCloud项目-ApplicationAopProxy类，主要位于ApplicationAopProxy模块的业务场景
 * @content: ApplicationAopProxy类，主要用于完成ApplicationAopProxy的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-28 02:02
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface ApplicationAopProxy <T> extends Proxy {

    @SuppressWarnings("unchecked")
    default T self() {
        return (T) AopContext.currentProxy();
    }
}
