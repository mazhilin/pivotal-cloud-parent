package com.pivotal.cloud.datasource.boot.plugin;

import com.pivotal.cloud.datasource.boot.constants.DatasourceGroup;
import com.pivotal.cloud.datasource.boot.holder.DynamicDatasourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @packageName com.pivotal.cloud.datasource.boot.plugin.MasterSlaveAutoRoutingPlugin
 * @projectName: pivotalCloud
 * @className: MasterSlaveAutoRoutingPlugin
 * @title: 封装pivotalCloud项目-MasterSlaveAutoRoutingPlugin类
 * @content: MasterSlaveAutoRoutingPlugin
 * @description:
 *     pivotalCloud项目-MasterSlaveAutoRoutingPlugin类,主要用作MasterSlaveAutoRoutingPlugin。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 16:21
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Slf4j
public class MasterSlaveAutoRoutingPlugin implements Interceptor {

    protected DataSource dynamicDataSource;

    public MasterSlaveAutoRoutingPlugin(DataSource dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        String pushedDataSource =null;
        try {
            String dataSource = SqlCommandType.SELECT == ms.getSqlCommandType() ? DatasourceGroup.SLAVE : DatasourceGroup.MASTER;

            pushedDataSource = DynamicDatasourceContextHolder.push(dataSource);

            return invocation.proceed();
        } finally {
            if (pushedDataSource != null) {
                DynamicDatasourceContextHolder.poll();
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
