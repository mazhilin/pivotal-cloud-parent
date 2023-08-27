package com.pivotal.cloud.datasource.druid.configuration;


import com.pivotal.cloud.datasource.boot.dynamic.DynamicRoutingSwitchDatasource;
import com.pivotal.cloud.datasource.druid.config.DruidDatasourceAspectAutoConfig;
import com.pivotal.cloud.datasource.druid.config.DruidDatasourceAssistAutoConfig;
import com.pivotal.cloud.datasource.druid.config.DruidDatasourceCreatorAutoConfig;
import com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceCustomizer;
import com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.druid.configuration.EnableDruidAutoConfiguration
 * @projectName: pivotalCloud
 * @className: EnableDruidAutoConfiguration
 * @title: 封装pivotalCloud项目-EnableDynamicDruidDatasourceAutoConfiguration类
 * @content: EnableDruidAutoConfiguration
 * @description:
 *     pivotalCloud项目-EnableDynamicDruidDatasourceAutoConfiguration类,主要用作EnableDynamicDruidDatasourceAutoConfiguration。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 17:50
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DynamicDruidDatasourceProperties.class)
@AutoConfigureBefore(value = DataSourceAutoConfiguration.class, name = "com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure")
@Import(value = {DruidDatasourceCreatorAutoConfig.class, DruidDatasourceAssistAutoConfig.class, DruidDatasourceAspectAutoConfig.class})
@ConditionalOnProperty(prefix = DynamicDruidDatasourceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class EnableDruidAutoConfiguration implements InitializingBean {

  private final DynamicDruidDatasourceProperties properties;

  private final List<DynamicDruidDatasourceCustomizer> customizers;


  public EnableDruidAutoConfiguration(
          DynamicDruidDatasourceProperties properties,
          ObjectProvider<List<DynamicDruidDatasourceCustomizer>> customizers) {
    this.properties = properties;
    this.customizers = customizers.getIfAvailable();
  }


  @Bean
  @ConditionalOnMissingBean
  public DataSource dataSource() {
    DynamicRoutingSwitchDatasource dataSource = new DynamicRoutingSwitchDatasource();
    dataSource.setPrimary(properties.getPrimary());
    dataSource.setStrict(properties.getStrict());
    dataSource.setStrategy(properties.getStrategy());
    dataSource.setP6spy(properties.getP6spy());
    dataSource.setSeata(properties.getSeata());
    return dataSource;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (!CollectionUtils.isEmpty(customizers)) {
      for (DynamicDruidDatasourceCustomizer customizer : customizers) {
        customizer.customize(properties);
      }
    }
  }
}
