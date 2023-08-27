package com.pivotal.cloud.datasource.boot.constants;
/**
 * @packageName com.pivotal.cloud.datasource.boot.constants.DatabasePools
 * @projectName: pivotalCloud
 * @className: DatabasePools
 * @title: 封装pivotalCloud项目-DatabasePools类
 * @content: DatabasePools-数据库连接池常量类
 * @description: pivotalCloud项目-DatabasePools类,主要用作DatabasePools-数据库连接池常量类。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 9:54
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
public final class DatabasePools {
    /**
     * Druid数据源
     */
    public static final String DRUID = "com.alibaba.druid.pool.DruidDataSource";

    /**
     * Hikaricp数据源
     */
    public static final String HIKARI = "com.zaxxer.hikari.HikariDataSource";

    /**
     * BeeCp数据源
     */
    public static final String BEECP = "cn.beecp.BeeDataSource";

    /**
     * DBCP2数据源
     */
    public static final String DBCP2 = "org.apache.commons.dbcp2.BasicDataSource";
}
