package com.pivotal.cloud.boot.aop;

import com.pivotal.cloud.boot.Aspect;

/**
 * @className: com.pivotal.cloud.boot.aop.ApplicationAspect
 * @projectName: 封装PivotalCloud项目-ApplicationAspect类
 * @module: PivotalCloud项目-ApplicationAspect类，主要位于ApplicationAspect模块的业务场景
 * @content: ApplicationAspect类，主要用于完成ApplicationAspect的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-27 23:27
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface  ApplicationAspect extends Aspect {
    /**
     * aspect切面-切入点
     */
    void aspectPoint();
}
