package com.pivotal.cloud.datasource.boot.provider;
import com.pivotal.cloud.datasource.boot.creator.DefaultDatasourceCreator;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.boot.provider.AbstractJdbcDatasourceProvider
 * @projectName: pivotalCloud
 * @className: AbstractJdbcDatasourceProvider
 * @title: 封装pivotalCloud项目-AbstractJdbcDatasourceProvider类
 * @content: AbstractJdbcDatasourceProvider
 * @description: pivotalCloud项目-AbstractJdbcDatasourceProvider类,主要用作AbstractJdbcDatasourceProvider。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 14:10
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public abstract class AbstractJdbcDatasourceProvider extends AbstractDatasourceProvider implements DynamicDatasourceProvider {
    /**
     * JDBC driver
     */
    private final String driverClassName;
    /**
     * JDBC url 地址
     */
    private final String url;
    /**
     * JDBC 用户名
     */
    private final String username;
    /**
     * JDBC 密码
     */
    private final String password;

    public AbstractJdbcDatasourceProvider(DefaultDatasourceCreator defaultDataSourceCreator, String url,
                                          String username, String password) {
        this(defaultDataSourceCreator, null, url, username, password);
    }

    public AbstractJdbcDatasourceProvider(DefaultDatasourceCreator defaultDataSourceCreator, String driverClassName, String url, String username, String password) {
        super(defaultDataSourceCreator);
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private static void closeResource(AutoCloseable con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                log.debug("Could not close ", ex);
            } catch (Throwable ex) {
                log.debug("Unexpected exception on closing", ex);
            }
        }
    }

    @Override
    public Map<String, DataSource> loadDatasources() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 由于 SPI 的支持，现在已无需显示加载驱动了
            // 但在用户显示配置的情况下，进行主动加载
            if (!StringUtils.isEmpty(driverClassName)) {
                Class.forName(driverClassName);
                log.info("成功加载数据库驱动程序");
            }
            conn = DriverManager.getConnection(url, username, password);
            log.info("成功获取数据库连接");
            stmt = conn.createStatement();
            Map<String, DatasourceProperties> dataSourcePropertiesMap = executeStmt(stmt);
            return createDataSourceMap(dataSourcePropertiesMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(conn);
            closeResource(stmt);
        }
        return null;
    }

    /**
     * 执行语句获得数据源参数
     *
     * @param statement 语句
     * @return 数据源参数
     * @throws SQLException sql异常
     */
    protected abstract Map<String, DatasourceProperties> executeStmt(Statement statement)
            throws SQLException;
}
