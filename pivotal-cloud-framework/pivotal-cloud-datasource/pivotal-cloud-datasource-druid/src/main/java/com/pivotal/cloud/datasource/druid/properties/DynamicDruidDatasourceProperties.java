package com.pivotal.cloud.datasource.druid.properties;

import cn.hutool.crypto.SecureUtil;
import com.pivotal.cloud.datasource.boot.config.DruidConfig;
import com.pivotal.cloud.datasource.boot.constants.SeataMode;
import com.pivotal.cloud.datasource.boot.properties.DatasourceAopProperties;
import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceLoadBalanceStrategy;
import com.pivotal.cloud.datasource.boot.strategy.DynamicDatasourceStrategy;
import com.pivotal.cloud.datasource.boot.utils.SecretCryptoUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className: com.pivotal.cloud.datasource.druid.properties.pivotalCloudDruidDatasourceProperties
 * @title: 封装pivotalCloud项目-pivotalCloudDruidDatasourceProperties类
 * @description: <p>
 *         pivotalCloud项目-pivotalCloudDruidDatasourceProperties
 *         </p>
 * @content: pivotalCloudDruidDatasourceProperties
 * @author: Powered by marklin
 * @datetime: 2023-06-02 00:18
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicDruidDatasourceProperties.PREFIX)
public class DynamicDruidDatasourceProperties {
    /**
     * Druid数据库连接池-属性前缀
     */
    public static final String PREFIX = "pivotal.cloud.dynamic.druid.datasource";

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
    private DruidConfig poolConfig = new DruidConfig();

    private DatasourceAopProperties aop =new DatasourceAopProperties();
}
