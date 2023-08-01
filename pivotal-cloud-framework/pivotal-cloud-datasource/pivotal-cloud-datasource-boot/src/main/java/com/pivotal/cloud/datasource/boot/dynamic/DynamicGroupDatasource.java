package com.pivotal.cloud.datasource.boot.dynamic;

import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy;
import lombok.Data;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @packageName com.pivotal.cloud.datasource.boot.dynamic.DynamicGroupDatasource
 * @projectName: pivotalCloud
 * @className: DynamicGroupDatasource
 * @title: 封装pivotalCloud项目-DynamicGroupDatasource类
 * @content: DynamicGroupDatasource
 * @description: pivotalCloud项目-DynamicGroupDatasource类,主要用作DynamicGroupDatasource。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 14:59
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class DynamicGroupDatasource {

    private String groupName;

    private DynamicDatasourceStrategy dynamicStrategy;

    private Map<String, DataSource> datasourceMap = new ConcurrentHashMap<>();

    public DynamicGroupDatasource(String groupName, DynamicDatasourceStrategy dynamicStrategy) {
        this.groupName = groupName;
        this.dynamicStrategy = dynamicStrategy;
    }

    /**
     * add a new datasource to this group
     *
     * @param identifier  the name of the datasource
     * @param datasource datasource
     */
    public DataSource addDatasource(String identifier , DataSource datasource) {
        return datasourceMap.put(identifier , datasource);
    }

    /**
     * @param identifier the name of the datasource
     */
    public DataSource removeDatasource(String identifier) {
        return datasourceMap.remove(identifier);
    }

    public String defineDatasource() {
        return dynamicStrategy.definiteDatasource(new ArrayList<>(datasourceMap.keySet()));
    }

    public DataSource finalizeDatasource() {
        return datasourceMap.get(defineDatasource());
    }

    public int size() {
        return datasourceMap.size();
    }
}
