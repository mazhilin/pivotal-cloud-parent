package com.pivotal.cloud.datasource.druid.config;


import com.pivotal.cloud.datasource.boot.annotation.DynamicDatasource;
import com.pivotal.cloud.datasource.boot.aspect.DynamicDatasourceAnnotationAdvisor;
import com.pivotal.cloud.datasource.boot.aspect.DynamicDatasourceAnnotationInterceptor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceExpressionProcessor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceHeaderProcessor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceProcessor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceSessionProcessor;
import com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties;
import com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.context.expression.BeanFactoryResolver;

/**
 * @packageName com.pivotal.cloud.datasource.druid.config.DruidDatasourceAspectAutoConfig
 * @projectName: pivotalCloud
 * @className: DruidDatasourceAspectAutoConfig
 * @title: 封装pivotalCloud项目-DruidDatasourceAspectAutoConfig类
 * @content: DruidDatasourceAspectAutoConfig
 * @description: pivotalCloud项目-DruidDatasourceAspectAutoConfig类,主要用作DruidDatasourceAspectAutoConfig。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 9:52
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicDruidDatasourceProperties.class)
public class DruidDatasourceAspectAutoConfig {
    private final DynamicDruidDatasourceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public DynamicDatasourceProcessor dynamicDatasourceProcessor(BeanFactory beanFactory) {
        DynamicDatasourceProcessor header = new DynamicDatasourceHeaderProcessor();
        DynamicDatasourceProcessor session = new DynamicDatasourceSessionProcessor();
        DynamicDatasourceExpressionProcessor expression = new DynamicDatasourceExpressionProcessor();
        expression.setBeanResolver(new BeanFactoryResolver(beanFactory));
        header.setNextProcessor(session);
        session.setNextProcessor(expression);
        return session;
    }

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    @ConditionalOnProperty(prefix = DynamicDruidDatasourceProperties.PREFIX + ".aop", name = "enabled", havingValue = "true", matchIfMissing = true)
    public Advisor dynamicDatasourceAnnotationAdvisor(DynamicDatasourceProcessor dynamicDatasourceProcessor) {
        DatasourceAopProperties aop = properties.getAop();
        DynamicDatasourceAnnotationInterceptor interceptor = new DynamicDatasourceAnnotationInterceptor(aop.getAllowedPublicOnly(), dynamicDatasourceProcessor);
        DynamicDatasourceAnnotationAdvisor advisor = new DynamicDatasourceAnnotationAdvisor(interceptor, DynamicDatasource.class);
        advisor.setOrder(aop.getOrder());
        return advisor;
    }
}
