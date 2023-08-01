package com.pivotal.cloud.datasource.boot.creator;

import com.pivotal.cloud.datasource.boot.exception.DatasourceException;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.lang.reflect.Method;

/**
 * @className: com.pivotal.cloud.datasource.boot.builder.BasicDatasourceCreatorBuilder
 * @title: 封装pivotalCloud项目-BasicDatasourceCreatorBuilder类
 * @description: <p>
 *         pivotalCloud项目-BasicDatasourceCreatorBuilder
 *         </p>
 * @content: BasicDatasourceCreatorBuilder
 * @author: Powered by marklin
 * @datetime: 2023-06-02 04:15
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class BasicDatasourceCreator implements DatasourceCreator {
    private static Method createMethod;
    private static Method typeMethod;
    private static Method urlMethod;
    private static Method usernameMethod;
    private static Method passwordMethod;
    private static Method driverClassNameMethod;
    private static Method buildMethod;

    static {
        //to support springboot 1.5 and 2.x
        Class<?> builderClass = null;
        try {
            builderClass = Class.forName("org.springframework.boot.jdbc.DataSourceBuilder");
        } catch (Exception ignored) {
        }
        if (builderClass == null) {
            try {
                builderClass = Class.forName("org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder");
            } catch (Exception e) {
                log.warn("not in springBoot ENV,could not create BasicDataSourceCreator");
            }
        }
        if (builderClass != null) {
            try {
                createMethod = builderClass.getDeclaredMethod("create");
                typeMethod = builderClass.getDeclaredMethod("type", Class.class);
                urlMethod = builderClass.getDeclaredMethod("url", String.class);
                usernameMethod = builderClass.getDeclaredMethod("username", String.class);
                passwordMethod = builderClass.getDeclaredMethod("password", String.class);
                driverClassNameMethod = builderClass.getDeclaredMethod("driverClassName", String.class);
                buildMethod = builderClass.getDeclaredMethod("build");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过属性创建数据源
     *
     * @param properties
     *         数据源属性
     *
     * @return 被创建的数据源
     */
    @Override
    public DataSource createDatasource(DatasourceProperties properties) {
        try {
            Object o1 = createMethod.invoke(null);
            Object o2 = typeMethod.invoke(o1, properties.getType());
            Object o3 = urlMethod.invoke(o2, properties.getUrl());
            Object o4 = usernameMethod.invoke(o3, properties.getUsername());
            Object o5 = passwordMethod.invoke(o4, properties.getPassword());
            Object o6 = driverClassNameMethod.invoke(o5, properties.getDriverClassName());
            return (DataSource) buildMethod.invoke(o6);
        } catch (Exception e) {
            throw new DatasourceException(
                    "dynamic-datasource create basic database named " + properties.getPoolName() + " error");
        }
    }

    /**
     * 当前创建器是否支持根据此属性创建
     *
     * @param properties
     *         数据源属性
     *
     * @return 是否支持
     */
    @Override
    public boolean support(DatasourceProperties properties) {
        return Boolean.TRUE;
    }
}
