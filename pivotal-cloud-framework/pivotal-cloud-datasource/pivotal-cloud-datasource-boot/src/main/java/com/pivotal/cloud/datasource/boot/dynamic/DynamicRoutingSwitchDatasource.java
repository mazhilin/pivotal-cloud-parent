package com.pivotal.cloud.datasource.boot.dynamic;


import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;
import com.pivotal.cloud.datasource.boot.exception.DynamicDatasourceException;
import com.pivotal.cloud.datasource.boot.holder.DynamicDatasourceContextHolder;
import com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceProvider;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceLoadBalanceStrategy;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy;
import com.p6spy.engine.spy.P6DataSource;
import io.seata.rm.datasource.DataSourceProxy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * @packageName com.pivotal.cloud.datasource.boot.dynamic.DynamicRoutingSwitchDatasource
 * @projectName: pivotalCloud
 * @className: DynamicRoutingSwitchDatasource
 * @title: 封装pivotalCloud项目-DynamicRoutingSwitchDatasource类
 * @content: DynamicRoutingSwitchDatasource
 * @description: pivotalCloud项目-DynamicRoutingSwitchDatasource类,主要用作DynamicRoutingSwitchDatasource。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 18:53
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DynamicRoutingSwitchDatasource extends AbstractDynamicDatasource implements InitializingBean, DisposableBean {

  private static final String UNDERLINE = "_";

  /** 所有数据库 */
  private final Map<String, DataSource> datasourceMap = new ConcurrentHashMap<>();

  /** 分组数据库 */
  private final Map<String, DynamicGroupDatasource> groupDatasources = new ConcurrentHashMap<>();

  @Autowired private List<DynamicDatasourceProvider> providers;

  @Setter
  private Class<? extends DynamicDatasourceStrategy> strategy =
      DynamicDatasourceLoadBalanceStrategy.class;

  @Setter
  private String primary = DatasourceGroup.MASTER;

  @Setter
  private Boolean strict = false;

  @Setter
  private Boolean p6spy = false;

  @Setter
  private Boolean seata = false;

  @Override
  protected DataSource defineDatasource() {
    String identifier = DynamicDatasourceContextHolder.peek();
    return buildDataSource(identifier);
  }

  /**
   * 获取默认数据源名称
   *
   * @return 名称
   */
  @Override
  protected String getPrimary() {
    return primary;
  }

  @Override
  public void destroy() throws Exception {
    log.info("dynamic-datasource start closing ....");
    for (Map.Entry<String, DataSource> item : datasourceMap.entrySet()) {
      closeDatasource(item.getKey(), item.getValue());
    }
    log.info("dynamic-datasource all closed success,bye");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    // 检查开启了配置但没有相关依赖
    checkedRuntimeEnvironment();
    // 添加并分组数据源
    Map<String, DataSource> dataSources = new HashMap<>(16);
    for (DynamicDatasourceProvider provider : providers) {
      dataSources.putAll(provider.loadDatasources());
    }
    for (Map.Entry<String, DataSource> dsItem : dataSources.entrySet()) {
      addDatasource(dsItem.getKey(), dsItem.getValue());
    }
    // 检测默认数据源是否设置
    if (groupDatasources.containsKey(primary)) {
      log.info(
          "dynamic-datasource initial loaded [{}] datasource,primary group datasource named [{}]",
          dataSources.size(),
          primary);
    } else if (datasourceMap.containsKey(primary)) {
      log.info(
          "dynamic-datasource initial loaded [{}] datasource,primary datasource named [{}]",
          dataSources.size(),
          primary);
    } else {
      log.warn(
          "dynamic-datasource initial loaded [{}] datasource,Please add your primary datasource or check your configuration",
          dataSources.size());
    }
  }

  /**
   * 获取数据源
   *
   * @param identifier 数据源名称
   * @return 数据源
   */
  public DataSource buildDataSource(String identifier) {
    if (StringUtils.isEmpty(identifier)) {
      return buildPrimaryDatasource();
    } else if (!groupDatasources.isEmpty() && groupDatasources.containsKey(identifier)) {
      log.debug("dynamic-datasource switch to the datasource named [{}]", identifier);
      return groupDatasources.get(identifier).finalizeDatasource();
    } else if (datasourceMap.containsKey(identifier)) {
      log.debug("dynamic-datasource switch to the datasource named [{}]", identifier);
      return datasourceMap.get(identifier);
    }
    if (strict) {
      throw new DynamicDatasourceException("dynamic-datasource could not find a datasource named" + identifier);
    }
    return buildPrimaryDatasource();
  }

  /**
   * 构建主数据源
   *
   * @return 主数据源
   */
  private DataSource buildPrimaryDatasource() {
    log.debug("dynamic-datasource switch to the primary datasource");
    DataSource dataSource = datasourceMap.get(primary);
    if (dataSource != null) {
      return dataSource;
    }
    DynamicGroupDatasource groupDatasource = groupDatasources.get(primary);
    if (groupDatasource != null) {
      return groupDatasource.finalizeDatasource();
    }
    throw new DynamicDatasourceException("dynamic-datasource can not find primary datasource");
  }

  /**
   * 添加数据源
   *
   * @param identifier 数据源名称
   * @param dataSource 数据源
   */
  public synchronized void addDatasource(String identifier, DataSource dataSource) {
    DataSource oldDataSource = datasourceMap.put(identifier, dataSource);
    // 新数据源添加到分组
    this.addGroupDatasource(identifier, dataSource);
    // 关闭老的数据源
    if (oldDataSource != null) {
      closeDatasource(identifier, oldDataSource);
    }
    log.info("dynamic-datasource - add a datasource named [{}] success", identifier);
  }

  /**
   * 新数据源添加到分组
   *
   * @param identifier 新数据源的名字
   * @param dataSource 新数据源
   */
  private void addGroupDatasource(String identifier, DataSource dataSource) {
    if (identifier.contains(UNDERLINE)) {
      String group = identifier.split(UNDERLINE)[0];
      DynamicGroupDatasource groupDataSource = groupDatasources.get(group);
      if (groupDataSource == null) {
        try {
          groupDataSource =
              new DynamicGroupDatasource(group, strategy.getDeclaredConstructor().newInstance());
          groupDatasources.put(group, groupDataSource);
        } catch (Exception e) {
          throw new RuntimeException(
              "dynamic-datasource - add the datasource named " + identifier + " error", e);
        }
      }
      groupDataSource.addDatasource(identifier, dataSource);
    }
  }

  /**
   * 删除数据源
   *
   * @param ds 数据源名称
   */
  public synchronized void removeDatasource(String ds) {
    if (!StringUtils.hasText(ds)) {
      throw new RuntimeException("remove parameter could not be empty");
    }
    if (primary.equals(ds)) {
      throw new RuntimeException("could not remove primary datasource");
    }
    if (datasourceMap.containsKey(ds)) {
      DataSource dataSource = datasourceMap.remove(ds);
      closeDatasource(ds, dataSource);
      if (ds.contains(UNDERLINE)) {
        String group = ds.split(UNDERLINE)[0];
        if (groupDatasources.containsKey(group)) {
          DataSource oldDataSource = groupDatasources.get(group).removeDatasource(ds);
          if (oldDataSource == null) {
            log.warn("fail for remove datasource from group. dataSource: {} ,group: {}", ds, group);
          }
        }
      }
      log.info("dynamic-datasource - remove the database named [{}] success", ds);
    } else {
      log.warn("dynamic-datasource - could not find a database named [{}]", ds);
    }
  }

  /**
   * close db
   *
   * @param ds dsName
   * @param dataSource db
   */
  private void closeDatasource(String ds, DataSource dataSource) {
    try {
      if (dataSource instanceof DynamicItemDatasource) {
        ((DynamicItemDatasource) dataSource).close();
      } else {
        if (seata) {
          if (dataSource instanceof DataSourceProxy) {
            DataSourceProxy dataSourceProxy = (DataSourceProxy) dataSource;
            dataSource = dataSourceProxy.getTargetDataSource();
          }
        }
        if (p6spy) {
          if (dataSource instanceof P6DataSource) {
            Field realDataSourceField = P6DataSource.class.getDeclaredField("realDataSource");
            realDataSourceField.setAccessible(true);
            dataSource = (DataSource) realDataSourceField.get(dataSource);
          }
        }
        Method closeMethod = ReflectionUtils.findMethod(dataSource.getClass(), "close");
        if (closeMethod != null) {
          closeMethod.invoke(dataSource);
        }
      }
    } catch (Exception e) {
      log.warn("dynamic-datasource closed datasource named [{}] failed", ds, e);
    }
  }

  /** 检查运行时环境 */
  private void checkedRuntimeEnvironment() {
    if (p6spy) {
      try {
        Class.forName("com.p6spy.engine.spy.P6DataSource");
        log.info("dynamic-datasource detect P6SPY plugin and enabled it");
      } catch (Exception e) {
        throw new RuntimeException(
            "dynamic-datasource enabled P6SPY ,however without p6spy dependency", e);
      }
    }
    if (seata) {
      try {
        Class.forName("io.seata.rm.datasource.DataSourceProxy");
        log.info("dynamic-datasource detect ALIBABA SEATA and enabled it");
      } catch (Exception e) {
        throw new RuntimeException(
            "dynamic-datasource enabled ALIBABA SEATA,however without seata dependency", e);
      }
    }
  }
}
