package com.pivotal.cloud.datasource.boot.dynamic;

import com.pivotal.cloud.datasource.boot.holder.DynamicDatasourceContextHolder;
import com.pivotal.cloud.datasource.boot.transaction.ConnectionProxy;
import com.pivotal.cloud.datasource.boot.transaction.ConnectionFactory;
import com.pivotal.cloud.datasource.boot.transaction.TransactionContext;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @packageName com.pivotal.cloud.datasource.boot.dynamic.AbstractDynamicDatasource
 * @projectName: pivotalCloud
 * @className: AbstractDynamicDatasource
 * @title: 封装pivotalCloud项目-AbstractDynamicDatasource类
 * @content: AbstractDynamicDatasource
 * @description: pivotalCloud项目-AbstractDynamicDatasource类,主要用作AbstractDynamicDatasource。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 12:36
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public abstract class AbstractDynamicDatasource extends AbstractDatasource{

    /**
     * 抽象获取连接池
     *
     * @return 连接池
     */
    protected abstract DataSource defineDatasource();

    /**
     * 获取默认数据源名称
     *
     * @return 名称
     */
    protected abstract String getPrimary();

    @Override
    public Connection getConnection() throws SQLException {
        String xid = TransactionContext.getXID();
        if (StringUtils.isEmpty(xid)) {
            return defineDatasource().getConnection();
        } else {
            String ds = DynamicDatasourceContextHolder.peek();
            ds = StringUtils.isEmpty(ds) ? getPrimary() : ds;
            ConnectionProxy connection = ConnectionFactory.getConnection(xid, ds);
            return connection == null ? getConnectionProxy(xid, ds, defineDatasource().getConnection()) : connection;
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        String xid = TransactionContext.getXID();
        if (StringUtils.isEmpty(xid)) {
            return defineDatasource().getConnection(username, password);
        } else {
            String ds = DynamicDatasourceContextHolder.peek();
            ds = StringUtils.isEmpty(ds) ? getPrimary() : ds;
            ConnectionProxy connection = ConnectionFactory.getConnection(xid, ds);
            return connection == null ? getConnectionProxy(xid, ds, defineDatasource().getConnection(username, password))
                    : connection;
        }
    }

    private Connection getConnectionProxy(String xid, String ds, Connection connection) {
        ConnectionProxy connectionProxy = new ConnectionProxy(connection, ds);
        ConnectionFactory.putConnection(xid, ds, connectionProxy);
        return connectionProxy;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        return defineDatasource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return (iface.isInstance(this) || defineDatasource().isWrapperFor(iface));
    }
}
