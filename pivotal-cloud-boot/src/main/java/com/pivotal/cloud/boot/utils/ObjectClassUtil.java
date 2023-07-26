package com.pivotal.cloud.boot.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @className: com.pivotal.cloud.boot.utils.ObjectClassUtil
 * @projectName: 封装PivotalCloud项目-ObjectClassUtil类
 * @module: PivotalCloud项目-ObjectClassUtil类，主要位于ObjectClassUtil模块的业务场景
 * @content: ObjectClassUtil类，主要用于完成ObjectClassUtil的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 20:40
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@UtilityClass
public class ObjectClassUtil extends ClassUtils {
    private final ParameterNameDiscoverer PARAMETERNAMEDISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * 获取方法参数信息
     * @param constructor 构造器
     * @param parameter 参数序号
     * @return {MethodParameter}
     */
    public MethodParameter getMethodParameter(Constructor<?> constructor, int parameter) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(constructor, parameter);
        methodParameter.initParameterNameDiscovery(PARAMETERNAMEDISCOVERER);
        return methodParameter;
    }

    /**
     * 获取方法参数信息
     * @param method 方法
     * @param parameter 参数序号
     * @return {MethodParameter}
     */
    public MethodParameter getMethodParameter(Method method, int parameter) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameter);
        methodParameter.initParameterNameDiscovery(PARAMETERNAMEDISCOVERER);
        return methodParameter;
    }

    /**
     * 获取Annotation
     * @param handlerMethod HandlerMethod
     * @param annotationType 注解类
     * @param <A> 泛型标记
     * @return {Annotation}
     */
    public <A extends Annotation> A getAnnotation(HandlerMethod handlerMethod, Class<A> annotationType) {
        // 先找方法，再找方法上的类
        A annotation = handlerMethod.getMethodAnnotation(annotationType);
        if (null != annotation) {
            return annotation;
        }
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        Class<?> beanType = handlerMethod.getBeanType();
        return AnnotatedElementUtils.findMergedAnnotation(beanType, annotationType);
    }

    /**
     * 获取Annotation
     * @param method Method
     * @param annotationType 注解类
     * @param <A> 泛型标记
     * @return {Annotation}
     */
    public <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
        Class<?> targetClass = method.getDeclaringClass();
        // The method may be on an interface, but we need attributes from the target
        // class.
        // If the target class is null, the method will be unchanged.
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the original
        // method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        // 先找方法，再找方法上的类
        A annotation = AnnotatedElementUtils.findMergedAnnotation(specificMethod, annotationType);

        if (null != annotation) {
            return annotation;
        }
        // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
        return AnnotatedElementUtils.findMergedAnnotation(specificMethod.getDeclaringClass(), annotationType);
    }
}
