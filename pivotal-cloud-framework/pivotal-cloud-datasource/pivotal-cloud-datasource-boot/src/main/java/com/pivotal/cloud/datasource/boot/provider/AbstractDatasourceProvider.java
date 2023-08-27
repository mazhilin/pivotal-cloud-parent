package com.pivotal.cloud.datasource.boot.provider;



import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.boot.provider.AbstractDatasourceProvider
 * @projectName: pivotalCloud
 * @className: AbstractDatasourceProvider
 * @title: 封装pivotalCloud项目-AbstractDatasourceProvider类
 * @content: AbstractDatasourceProvider
 * @description: pivotalCloud项目-AbstractDatasourceProvider类,主要用作AbstractDatasourceProvider。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:58
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@AllArgsConstructor
public abstract class AbstractDatasourceProvider implements DynamicDatasourceProvider {
    private final DefaultDatasourceCreator defaultDataSourceCreator;

    protected Map<String, DataSource> createDataSourceMap(Map<String, DatasourceProperties> datasourcePropertiesMap) {
        Map<String, DataSource> dataSourceMap = new HashMap<>(datasourcePropertiesMap.size() * 2);
        for (Map.Entry<String, DatasourceProperties> item : datasourcePropertiesMap.entrySet()) {
            String identifier = item.getKey();
            DatasourceProperties properties = item.getValue();
            String poolName = properties.getPoolName();
            if (poolName == null || "".equals(poolName)) {
                poolName = identifier;
            }
            properties.setPoolName(poolName);
            dataSourceMap.put(identifier, defaultDataSourceCreator.createDataSource(properties));
        }
        return dataSourceMap;
    }

}
