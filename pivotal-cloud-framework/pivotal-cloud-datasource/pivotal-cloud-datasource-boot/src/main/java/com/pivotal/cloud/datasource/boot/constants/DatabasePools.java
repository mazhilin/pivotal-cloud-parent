package com.pivotal.cloud.datasource.boot.constants;

/**
 * @className: com.pivotal.cloud.datasource.boot.constants.Databses
 * @title: 封装pivotalCloud项目-Databses类
 * @description: <p>
 *         pivotalCloud项目-Databses
 *         </p>
 * @content: Databses
 * @author: Powered by marklin
 * @datetime: 2023-06-02 02:27
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
