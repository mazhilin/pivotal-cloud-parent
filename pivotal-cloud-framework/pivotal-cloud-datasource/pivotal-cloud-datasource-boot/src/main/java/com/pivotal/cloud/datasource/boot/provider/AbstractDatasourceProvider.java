package com.pivotal.cloud.datasource.boot.provider;



import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @packageName com.coocaa.cloud.datasource.boot.provider.AbstractDatasourceProvider
 * @projectName: CoocaaCloud
 * @className: AbstractDatasourceProvider
 * @title: 封装CoocaaCloud项目-AbstractDatasourceProvider类
 * @content: AbstractDatasourceProvider
 * @description: CoocaaCloud项目-AbstractDatasourceProvider类,主要用作AbstractDatasourceProvider。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:58
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CoocaaCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public abstract class AbstractDatasourceProvider implements DynamicDatasourceProvider {

    private  DefaultDatasourceCreator defaultDataSourceCreator;

    public AbstractDatasourceProvider(DefaultDatasourceCreator defaultDataSourceCreator) {
        this.defaultDataSourceCreator = defaultDataSourceCreator;
    }

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
