package com.pivotal.cloud.datasource.boot.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceProvider
 * @projectName: pivotalCloud
 * @className: DynamicDatasourceProvider
 * @title: 封装pivotalCloud项目-DynamicDatasourceProvider类
 * @content: DynamicDatasourceProvider
 * @description: pivotalCloud项目-DynamicDatasourceProvider类,主要用作DynamicDatasourceProvider。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 10:55
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DynamicDatasourceProvider extends DatasourceProvider{
    /**
     * 加载所有数据源
     *
     * @return 所有数据源，key为数据源名称
     */
    Map<String, DataSource> loadDatasources();
}
