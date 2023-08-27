package com.pivotal.cloud.datasource.hikari.config;

import com.pivotal.cloud.datasource.boot.annotation.DynamicDatasource;
import com.pivotal.cloud.datasource.boot.annotation.DynamicDatasourceTransactional;
import com.pivotal.cloud.datasource.boot.aspect.DynamicDatasourceAnnotationAdvisor;
import com.pivotal.cloud.datasource.boot.aspect.DynamicDatasourceAnnotationInterceptor;
import com.pivotal.cloud.datasource.boot.aspect.DynamicDatasourceTransactionInterceptor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceExpressionProcessor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceHeaderProcessor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceProcessor;
import com.pivotal.cloud.datasource.boot.processor.DynamicDatasourceSessionProcessor;
import com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties;
import com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties;
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
 * @packageName com.pivotal.cloud.datasource.hikari.config.HikariDatasourceAspectAutoConfig
 * @projectName: SmartCloud
 * @className: HikariDatasourceAspectAutoConfig
 * @title: 封装SmartCloud项目-HikariDatasourceAspectAutoConfig类
 * @content: HikariDatasourceAspectAutoConfig
 * @description: SmartCloud项目-HikariDatasourceAspectAutoConfig类,主要用作HikariDatasourceAspectAutoConfig。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 12:41
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 SmartCloud Systems Incorporated. All rights reserved.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DynamicHikariDatasourceProperties.class)
public class HikariDatasourceAspectAutoConfig {

    private final DynamicHikariDatasourceProperties properties;


    @Bean
    @ConditionalOnMissingBean
    public DynamicDatasourceProcessor datasourceProcessor(BeanFactory beanFactory) {
        DynamicDatasourceProcessor headerProcessor = new DynamicDatasourceHeaderProcessor();
        DynamicDatasourceProcessor sessionProcessor = new DynamicDatasourceSessionProcessor();
        DynamicDatasourceExpressionProcessor expressionProcessor = new DynamicDatasourceExpressionProcessor();
        expressionProcessor.setBeanResolver(new BeanFactoryResolver(beanFactory));
        headerProcessor.setNextProcessor(sessionProcessor);
        sessionProcessor.setNextProcessor(expressionProcessor);
        return headerProcessor;
    }

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    @ConditionalOnProperty(prefix = DynamicHikariDatasourceProperties.PREFIX + ".aop", name = "enabled", havingValue = "true", matchIfMissing = true)
    public Advisor dynamicDatasourceAnnotationAdvisor(DynamicDatasourceProcessor datasourceProcessor) {
        DatasourceAopProperties aop = properties.getAop();
        DynamicDatasourceAnnotationInterceptor interceptor = new DynamicDatasourceAnnotationInterceptor(aop.getAllowedPublicOnly(), datasourceProcessor);
        DynamicDatasourceAnnotationAdvisor advisor = new DynamicDatasourceAnnotationAdvisor(interceptor, DynamicDatasource.class);
        advisor.setOrder(aop.getOrder());
        return advisor;
    }

    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @Bean
    @ConditionalOnProperty(prefix = DynamicHikariDatasourceProperties.PREFIX, name = "seata", havingValue = "false", matchIfMissing = true)
    public Advisor dynamicTransactionAdvisor() {
        DynamicDatasourceTransactionInterceptor interceptor = new DynamicDatasourceTransactionInterceptor();
        return new DynamicDatasourceAnnotationAdvisor(interceptor, DynamicDatasourceTransactional.class);
    }
}
