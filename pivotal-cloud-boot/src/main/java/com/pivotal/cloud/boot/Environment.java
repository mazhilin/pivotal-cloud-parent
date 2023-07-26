package com.pivotal.cloud.boot;

import org.springframework.beans.factory.DisposableBean;

/**
 * @className: com.pivotal.cloud.boot.Environment
 * @projectName: 封装PivotalCloud项目-Environment类
 * @module: PivotalCloud项目-Environment类，主要位于Environment模块的业务场景
 * @content: Environment类，主要用于完成Environment的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 21:36
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface  Environment extends DisposableBean {
    /**
     * 清空上下文内容
     */
    void clearContext();
}
