package com.pivotal.cloud.datasource.druid.properties;


import com.pivotal.cloud.datasource.boot.config.DruidConfig;
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
 * @packageName com.pivotal.cloud.datasource.druid.properties.DynamicDruidDatasourceProperties
 * @projectName: pivotalCloud
 * @className: DynamicDruidDatasourceProperties
 * @title: 封装pivotalCloud项目-DruidDatasourceProperties类
 * @content: DynamicDruidDatasourceProperties
 * @description: pivotalCloud项目-DruidDatasourceProperties类,主要用作DruidDatasourceProperties。
 * @author: Powered by Marklin
 * @datetime: 2023-06-05 19:37
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicDruidDatasourceProperties.PREFIX)
public class DynamicDruidDatasourceProperties {

    public static final String PREFIX = "pivotal.cloud.dynamic.druid.datasource";

    /**
     * 必须设置默认的库,默认master
     */
    private String primary = "master";
    /**
     * 是否启用严格模式,默认不启动. 严格模式下未匹配到数据源直接报错, 非严格模式下则使用默认数据源primary所设置的数据源
     */
    private Boolean strict = false;
    /**
     * 是否使用p6spy输出，默认不输出
     */
    private Boolean p6spy = false;
    /**
     * 是否使用开启seata，默认不开启
     */
    private Boolean seata = false;
    /**
     * 是否懒加载数据源
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
    private Map<String, DatasourceProperties> database = new LinkedHashMap<>();
    /**
     * 多数据源选择算法clazz，默认负载均衡算法
     */
    private Class<? extends DynamicDatasourceStrategy> strategy = DynamicDatasourceLoadBalanceStrategy.class;
    /**
     * Druid全局参数配置
     */
    /**
     *  Druid数据库连接池-属性配置
     */
    private DruidConfig poolConfig = new DruidConfig();
    /**
     * Druid-aop参数配置
     */
    private DatasourceAopProperties aop = new DatasourceAopProperties();
}
