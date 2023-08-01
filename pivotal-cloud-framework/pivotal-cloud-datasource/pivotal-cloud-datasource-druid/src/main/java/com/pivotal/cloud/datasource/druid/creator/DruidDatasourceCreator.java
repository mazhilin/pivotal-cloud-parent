package com.pivotal.cloud.datasource.druid.creator;

import com.pivotal.cloud.datasource.boot.config.DruidConfig;
import com.pivotal.cloud.datasource.boot.creator.DatasourceCreator;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Method;

/**
 * @className: com.pivotal.cloud.datasource.druid.creator.DynamicDruidDatasourceCreator
 * @title: 封装CoocaaCloud项目-DynamicDruidDatasourceCreator类
 * @description: <p>
 *         CoocaaCloud项目-DynamicDruidDatasourceCreator
 *         </p>
 * @content: DynamicDruidDatasourceCreator
 * @author: Powered by marklin
 * @datetime: 2023-06-05 21:30
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CoocaaCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DruidDatasourceCreator implements DatasourceCreator {
    private static Method configMethod = null;

    private DruidConfig druidConfig;
    /**
     * 通过属性创建数据源
     *
     * @param properties
     *         数据源属性
     *
     * @return 被创建的数据源
     */
    @Override
    public DataSource createDatasource(DatasourceProperties properties) {
        return null;
    }

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties
     *         数据源属性
     *
     * @return 是否支持
     */
    @Override
    public boolean support(DatasourceProperties properties) {
        return false;
    }
}
