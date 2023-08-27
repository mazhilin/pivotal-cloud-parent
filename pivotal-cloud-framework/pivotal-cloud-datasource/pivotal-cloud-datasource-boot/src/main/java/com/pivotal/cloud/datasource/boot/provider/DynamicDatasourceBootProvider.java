package com.pivotal.cloud.datasource.boot.provider;


import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceBootProvider
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceBootProvider
 * @title: 封装pivotalCloud项目-DynamicDatasourceBootProvider类
 * @content: DynamicDatasourceBootProvider
 * @description: pivotalCloud项目-DynamicDatasourceBootProvider类,主要用作DynamicDatasourceBootProvider。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 19:22
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class DynamicDatasourceBootProvider extends AbstractDatasourceProvider{

    /**
     * 所有数据源
     */
    private final Map<String, DatasourceProperties> propertiesConfigMap;

    public DynamicDatasourceBootProvider(DefaultDatasourceCreator datasourceCreator, Map<String,
            DatasourceProperties> propertiesConfigMap) {
        super(datasourceCreator);
        this.propertiesConfigMap = propertiesConfigMap;
    }

    /**
     * 加载所有数据源
     *
     * @return 所有数据源，key为数据源名称
     */
    @Override
    public Map<String, DataSource> loadDatasources() {
        return createDataSourceMap(propertiesConfigMap);
    }
}
