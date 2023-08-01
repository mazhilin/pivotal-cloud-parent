package com.pivotal.cloud.boot.holder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }


    @Override
    public void destroy() throws Exception {

    }
}
