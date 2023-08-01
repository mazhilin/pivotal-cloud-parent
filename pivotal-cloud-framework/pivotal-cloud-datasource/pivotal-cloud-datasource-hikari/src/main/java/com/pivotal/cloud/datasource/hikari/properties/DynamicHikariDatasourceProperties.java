package com.pivotal.cloud.datasource.hikari.properties;

import com.pivotal.cloud.datasource.boot.config.DruidConfig;
import com.pivotal.cloud.datasource.boot.config.HikaricpConfig;
import com.pivotal.cloud.datasource.boot.constants.SeataMode;
import com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceLoadBalanceStrategy;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy;
import com.pivotal.cloud.datasource.boot.utils.SecretCryptoUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className: com.pivotal.cloud.datasource.hikari.properties.pivotalCloudHikariDatasourceProperties
 * @title: 封装pivotalCloud项目-pivotalCloudHikariDatasourceProperties类
 * @description: <p>
 *         pivotalCloud项目-pivotalCloudHikariDatasourceProperties
 *         </p>
 * @content: pivotalCloudHikariDatasourceProperties
 * @author: Powered by marklin
 * @datetime: 2023-06-02 00:22
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
@ConfigurationProperties(prefix = DynamicHikariDatasourceProperties.PREFIX)
public class DynamicHikariDatasourceProperties {
    public static final String PREFIX = "pivotal.cloud.dynamic.hikari.datasource";

    /**
     * Druid数据库连接池-默认数据库
     */
    private String primary = "master";

    /**
     * Druid数据库连接池-是否启用严格模式,默认不启动. 严格模式下未匹配到数据源直接报错, 非严格模式下则使用默认数据源primary所设置的数据源
     */
    private Boolean strict = false;

    /**
     * Druid数据库连接池-是否使用p6spy输出，默认不输出
     */
    private Boolean p6spy = false;
    /**
     *  Druid数据库连接池-是否使用开启seata，默认不开启
     */
    private Boolean seata = false;
    /**
     *  Druid数据库连接池-是否懒加载数据源
     */
    private Boolean lazy = false;
    /**
     * seata使用模式，默认AT
     */
    private SeataMode seataMode = SeataMode.AT;
    /**
     * 全局默认publicKey
     */
    private String publicKey = SecretCryptoUtil.DEFAULT_PUBLIC_KEY_STRING;

    /**
     * 每一个数据源
     */
    private Map<String, DatasourceProperties> datasource = new LinkedHashMap<>();
    /**
     * 多数据源选择算法clazz，默认负载均衡算法
     */
    private Class<? extends DynamicDatasourceStrategy> strategy = DynamicDatasourceLoadBalanceStrategy.class;

    /**
     *  Druid数据库连接池-属性配置
     */
    private HikaricpConfig poolConfig = new HikaricpConfig();

    private DatasourceAopProperties aop =new DatasourceAopProperties();
}
