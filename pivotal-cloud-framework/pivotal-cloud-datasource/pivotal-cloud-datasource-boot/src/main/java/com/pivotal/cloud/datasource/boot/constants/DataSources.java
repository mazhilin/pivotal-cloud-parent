package com.pivotal.cloud.datasource.boot.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @packageName com.pivotal.cloud.datasource.boot.constants.DataSources
 * @projectName: pivotalCloud
 * @className: DataSources
 * @title: 封装pivotalCloud项目-DataSources类
 * @content: DataSources
 * @description: pivotalCloud项目-DataSources类,主要用作DataSources。
 * @author: Powered by Marklin
 * @datetime: 2023-05-29 16:36
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Getter
@AllArgsConstructor
public enum DataSources {
    /**
     * mysql 数据库
     */
    MYSQL("mysql",
            "jdbc:mysql://%s:%s/%s?characterEncoding=utf8"
                    + "&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true"
                    + "&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true",
            "select 1", "mysql8 链接"),

    /**
     * mariadb 数据库
     */
    MARIADB("mariadb",
            "jdbc:mariadb://%s:%s/%s?characterEncoding=utf8"
                    + "&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true"
                    + "&useLegacyDatetimeCode=false&allowMultiQueries=true&allowPublicKeyRetrieval=true",
            "select 1", "mariadb 链接"),

    /**
     * postgresql 数据库
     */
    POSTGRESQL("postgresql", "jdbc:postgresql://%s:%s/%s", "select 1", "postgresql 链接"),

    /**
     * SQL SERVER
     */
    MSSQL("mssql", "jdbc:sqlserver://%s:%s;database=%s;characterEncoding=UTF-8", "select 1", "sqlserver 链接"),

    /**
     * oracle
     */
    ORACLE("oracle", "jdbc:oracle:thin:@%s:%s:%s", "select 1 from dual", "oracle 链接"),

    /**
     * db2
     */
    DB2("db2", "jdbc:db2://%s:%s/%s", "select 1 from sysibm.sysdummy1", "DB2 TYPE4 连接");

    private final String database;

    private final String url;

    private final String validationQuery;

    private final String description;


    /**
     * 匹配数据库
     * @param dsType 数据库类型
     * @return 返回结果
     */
    public static DataSources matchDatabase(String dsType) {
        return Arrays.stream(DataSources.values()).filter(dsJdbcUrlEnum -> dsType.equals(dsJdbcUrlEnum.getDatabase()))
                .findFirst().get();
    }
}
