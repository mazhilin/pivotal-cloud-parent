package com.pivotal.cloud.datasource.hikari.properties;

import com.pivotal.cloud.datasource.boot.config.HikaricpConfig;
import com.pivotal.cloud.datasource.boot.constants.SeataMode;
import com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceLoadBalanceStrategy;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy;
import com.pivotal.cloud.datasource.boot.utils.SecretCryptoUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @packageName com.pivotal.cloud.datasource.hikari.properties.DynamicHikariDatasourceProperties
 * @projectName: pivotalCloud
 * @className: DynamicHikariDatasourceProperties
 * @title: 封装pivotalCloud项目-DynamicHikariDatasourceProperties类
 * @content: DynamicHikariDatasourceProperties
 * @description: pivotalCloud项目-DynamicHikariDatasourceProperties类,主要用作DynamicHikariDatasourceProperties。
 * @author: Powered by Marklin
 * @datetime: 2023-06-06 12:35
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicHikariDatasourceProperties.PREFIX)
public class DynamicHikariDatasourceProperties {
    /**
     * HikariCP-属性文件配置前缀
     */
    public static final String PREFIX = "pivotal.cloud.dynamic.hikari.datasource";

    /**
     * HikariCP-必须设置默认的库,默认master
     */
    private String primary = "master";
    /**
     * HikariCP-是否启用严格模式,默认不启动. 严格模式下未匹配到数据源直接报错, 非严格模式下则使用默认数据源primary所设置的数据源
     */
    private Boolean strict = false;
    /**
     * HikariCP-是否使用p6spy输出，默认不输出
     */
    private Boolean p6spy = false;
    /**
     * HikariCP-是否使用开启seata，默认不开启
     */
    private Boolean seata = false;
    /**
     * HikariCP-是否懒加载数据源
     */
    private Boolean lazy = false;
    /**
     * HikariCP-seata使用模式，默认AT
     */
    private SeataMode seataMode = SeataMode.AT;
    /**
     * HikariCP-全局默认publicKey
     */
    private String publicKey = SecretCryptoUtil.DEFAULT_PUBLIC_KEY_STRING;
    /**
     * HikariCP-数据源
     */
    private Map<String, DatasourceProperties> database = new LinkedHashMap<>();
    /**
     * HikariCP-多数据源选择算法clazz，默认负载均衡算法
     */
    private Class<? extends DynamicDatasourceStrategy> strategy = DynamicDatasourceLoadBalanceStrategy.class;
    /**
     * HikariCP-全局参数配置
     */
    /**
     * HikariCP-数据库连接池配置
     */
    private HikaricpConfig poolConfig = new HikaricpConfig();
    /**
     *  HikariCP--aop参数配置
     */
    private DatasourceAopProperties aop = new DatasourceAopProperties();
}
