package com.pivotal.cloud.datasource.boot.creator;

import com.p6spy.engine.spy.P6DataSource;
import com.pivotal.cloud.datasource.boot.constants.SeataMode;
import com.pivotal.cloud.datasource.boot.dynamic.DynamicItemDatasource;
import com.pivotal.cloud.datasource.boot.event.DatasourceInitializrEvent;
import com.pivotal.cloud.datasource.boot.properties.DatasourceInitProperties;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.support.SqlScriptCommandRunner;
import com.pivotal.cloud.datasource.boot.utils.SecretCryptoUtil;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @packageName com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator
 * @projectName: pivotalCloud
 * @className: DefaultDatasourceCreator
 * @title: 封装pivotalCloud项目-DefaultDatasourceCreator类
 * @content: DefaultDatasourceCreator
 * @description: pivotalCloud项目-DefaultDatasourceCreator类,主要用作DefaultDatasourceCreator。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 11:11
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Setter
public class DefaultDatasourceCreator {
  private List<DatasourceCreator> creators;

  /** 是否懒加载数据源 */
  private Boolean lazy = false;
  /** /** 是否使用p6spy输出，默认不输出 */
  private Boolean p6spy = false;
  /** 是否使用开启seata，默认不开启 */
  private Boolean seata = false;

  /**
   * seata使用模式，默认AT
   */
  private SeataMode seataMode = SeataMode.AT;

  /**
   * 全局默认publicKey
   */
  private String publicKey = SecretCryptoUtil.DEFAULT_PUBLIC_KEY_STRING;

  private DatasourceInitializrEvent initializrEvent;

  public DataSource createDataSource(DatasourceProperties properties) {
    DatasourceCreator datasource = null;
    for (DatasourceCreator creator : this.creators) {
      if (creator.support(properties)) {
        datasource = creator;
        break;
      }
    }
    if (datasource == null) {
      throw new IllegalStateException("creator must not be null,please check the DataSourceCreator");
    }
    String propertyPublicKey = properties.getPublicKey();
    if (StringUtils.isEmpty(propertyPublicKey)) {
      properties.setPublicKey(publicKey);
    }
    Boolean propertyLazy = properties.getLazy();
    if (propertyLazy == null) {
      properties.setLazy(lazy);
    }
    if (initializrEvent != null) {
      initializrEvent.beforeCreate(properties);
    }
    DataSource dataSource = datasource.createDatasource(properties);
    if (initializrEvent != null) {
      initializrEvent.afterCreate(dataSource);
    }
    this.runScript(dataSource, properties);
    return wrapDataSource(dataSource, properties);
  }

  private void runScript(DataSource dataSource, DatasourceProperties properties) {
    DatasourceInitProperties initProperty = properties.getInit();
    String schema = initProperty.getSchema();
    String data = initProperty.getData();
    if (StringUtils.hasText(schema) || StringUtils.hasText(data)) {
      SqlScriptCommandRunner command = new SqlScriptCommandRunner(initProperty.isContinueOnError(), initProperty.getSeparator());
      if (StringUtils.hasText(schema)) {
        command.executeScript(dataSource, schema);
      }
      if (StringUtils.hasText(data)) {
        command.executeScript(dataSource, data);
      }
    }
  }

  private DataSource wrapDataSource(DataSource dataSource, DatasourceProperties properties) {
    String name = properties.getPoolName();
    DataSource targetDataSource = dataSource;

    Boolean enabledP6spy = p6spy && properties.getP6spy();
    if (enabledP6spy) {
      targetDataSource = new P6DataSource(dataSource);
      log.debug("dynamic-datasource [{}] wrap p6spy plugin", name);
    }

    Boolean enabledSeata = seata && properties.getSeata();
    if (enabledSeata) {
      if (SeataMode.XA == seataMode) {
        targetDataSource = new DataSourceProxyXA(targetDataSource);
      } else {
        targetDataSource = new DataSourceProxy(targetDataSource);
      }
      log.debug("dynamic-datasource [{}] wrap seata plugin transaction mode ", name);
    }
    return new DynamicItemDatasource(name, dataSource, targetDataSource, enabledP6spy, enabledSeata, seataMode);
  }
}
