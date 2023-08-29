package com.pivotal.cloud.boot.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @className: com.pivotal.cloud.boot.holder.SpringContextHolder
 * @projectName: 封装PivotalCloud项目-SpringContextHolder类
 * @module: PivotalCloud项目-SpringContextHolder类，主要位于SpringContextHolder模块的业务场景
 * @content: SpringContextHolder类，主要用于完成SpringContextHolder的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-02 03:13
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Service
@Lazy(false)
@SuppressWarnings("ALL")
public class SpringContextHolder implements BeanFactoryPostProcessor, ApplicationContextAware, DisposableBean {
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext = null;

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static ListableBeanFactory getBeanFactory() {
        return null == beanFactory ? applicationContext : beanFactory;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) getBeanFactory().getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, Map<Bean名称，实现类></>
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        return getBeanFactory().getBean(requiredType);
    }


    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }


    @Override
    public void destroy() throws Exception {
        this.clearHolder();
    }
}
