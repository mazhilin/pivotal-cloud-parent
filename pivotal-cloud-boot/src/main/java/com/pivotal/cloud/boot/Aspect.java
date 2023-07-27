package com.pivotal.cloud.boot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;

/**
 * @className: com.pivotal.cloud.boot.Aspect
 * @projectName: 封装PivotalCloud项目-Aspect类
 * @module: PivotalCloud项目-Aspect类，主要位于Aspect模块的业务场景
 * @content: Aspect类，主要用于完成Aspect的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-27 23:25
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface Aspect extends Ordered {
    /**
     * 切面环绕通知-@Around
     *
     * @param point 切入点
     * @return 返回结果
     * @throws Throwable 异常消息
     */
    default Object aspectAround(ProceedingJoinPoint point) throws Throwable {
        return null;
    }

    /**
     * 切面切入之前-@Before
     *
     * @param point 切入点
     * @return 返回结果
     * @throws Throwable 异常消息
     */
    default void aspectBefore(ProceedingJoinPoint point) throws Throwable {
    }

    /**
     * 切面切入之后(返回)-@AfterReturning
     *
     * @param object 返回对象
     */
    default void aspectReturn(Object object) {
    }

    /**
     * 切面切入之后(错误)-@AfterThrowing
     *
     * @param exception 返回对象
     */
    default void aspectError(Throwable exception) {
    }

    /**
     * 切面切入之后-@After
     *
     * @throws Throwable 异常消息
     */
    default void aspectAfter() throws Throwable {
    }
}
