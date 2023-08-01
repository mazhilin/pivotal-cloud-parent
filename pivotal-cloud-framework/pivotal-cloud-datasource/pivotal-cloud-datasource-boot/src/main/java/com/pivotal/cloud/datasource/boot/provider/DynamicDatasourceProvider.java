package com.pivotal.cloud.datasource.boot.provider;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @className: com.pivotal.cloud.datasource.boot.provider.DynamicDatasourceProvider
 * @title: 封装pivotalCloud项目-DynamicDatasourceProvider类
 * @description: <p>
 *         pivotalCloud项目-DynamicDatasourceProvider
 *         </p>
 * @content: DynamicDatasourceProvider
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:50
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public interface DynamicDatasourceProvider {
    /**
     * 加载所有数据源
     *
     * @return 所有数据源，key为数据源名称
     */
    Map<String, DataSource> loadDatasources();
}
