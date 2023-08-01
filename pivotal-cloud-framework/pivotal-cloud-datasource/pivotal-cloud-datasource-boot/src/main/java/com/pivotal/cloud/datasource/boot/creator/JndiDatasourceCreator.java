package com.pivotal.cloud.datasource.boot.creator;


import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * @packageName com.coocaa.cloud.datasource.boot.creator.JndiDatasourceCreator
 * @projectName: CoocaaCloud
 * @className: JndiDatasourceCreator
 * @title: 封装CoocaaCloud项目-JndiDatasourceCreator类
 * @content: JndiDatasourceCreator-JNDI数据源创建实现器
 * @description: CoocaaCloud项目-JndiDatasourceCreator类,主要用作JndiDatasourceCreator-JNDI数据源创建实现器。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 11:07
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 CoocaaCloud Systems Incorporated. All rights reserved.
 */
public class JndiDatasourceCreator implements DatasourceCreator{

    private static final JndiDataSourceLookup LOOKUP = new JndiDataSourceLookup();

    public DataSource createDataSource(String jndiName) {
        return LOOKUP.getDataSource(jndiName);
    }


    /**
     * 通过属性文件创建数据源
     *
     * @param properties 数据源属性
     * @return 被创建的数据源
     */
    @Override
    public DataSource createDatasource(DatasourceProperties properties) {
        return createDataSource(properties.getJndiName());
    }

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties 数据源属性
     * @return 是否支持
     */
    @Override
    public boolean support(DatasourceProperties properties) {
        String jndiName = properties.getJndiName();
        return jndiName != null && !jndiName.isEmpty();
    }
}
