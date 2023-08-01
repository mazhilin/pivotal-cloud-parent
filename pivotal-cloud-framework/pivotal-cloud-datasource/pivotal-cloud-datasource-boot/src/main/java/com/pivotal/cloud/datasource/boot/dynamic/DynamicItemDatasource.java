package com.pivotal.cloud.datasource.boot.dynamic;


import com.pivotal.cloud.datasource.boot.constants.SeataMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @packageName com.coocaa.cloud.datasource.boot.dynamic.DynamicItemDatasource
 * @projectName: pivotalCloud
 * @className: DynamicItemDatasource
 * @title: 封装pivotalCloud项目-DynamicItemDatasource类
 * @content: DynamicItemDatasource
 * @description: pivotalCloud项目-DynamicItemDatasource类,主要用作DynamicItemDatasource。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 15:02
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicItemDatasource extends AbstractDatasource implements Closeable {

    private String name;

    private DataSource realDataSource;

    private DataSource dataSource;

    private Boolean p6spy;

    private Boolean seata;

    private SeataMode seataMode;

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return dataSource.getConnection(username, password);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return super.isWrapperFor(iface) || iface.isInstance(realDataSource) || iface.isInstance(dataSource);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrap(Class<T> iface) {
        if (iface.isInstance(this)) {
            return (T) this;
        }
        if (iface.isInstance(realDataSource)) {
            return (T) realDataSource;
        }
        if (iface.isInstance(dataSource)) {
            return (T) dataSource;
        }
        return null;
    }

    @Override
    public void close() {
        Class<? extends DataSource> clazz = realDataSource.getClass();
        try {
            Method closeMethod = ReflectionUtils.findMethod(clazz, "close");
            if (closeMethod != null) {
                closeMethod.invoke(realDataSource);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("dynamic-datasource close the datasource named [{}] failed,", name, e);
        }
    }
}
