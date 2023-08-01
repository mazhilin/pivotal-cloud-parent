package com.pivotal.cloud.datasource.boot.config;

import com.pivotal.cloud.datasource.boot.constants.SeataMode;
import com.pivotal.cloud.datasource.boot.utils.SecretCryptoUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: com.pivotal.cloud.datasource.boot.config.GenericGlobalConfig
 * @title: 封装pivotalCloud项目-GenericGlobalConfig类
 * @description: <p>
 *         pivotalCloud项目-GenericGlobalConfig
 *         </p>
 * @content: GenericGlobalConfig-通用全局配置类
 * @author: Powered by marklin
 * @datetime: 2023-06-03 01:13
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
@Getter
@Setter
public class GenericGlobalConfig {
    /**
     * 是否懒加载数据源
     */
    private Boolean lazy = false;
    /**
     * /**
     * 是否使用p6spy输出，默认不输出
     */
    private Boolean p6spy = false;
    /**
     * 是否使用开启seata，默认不开启
     */
    private Boolean seata = false;
    /**
     * seata使用模式，默认AT
     */
    private SeataMode seataMode = SeataMode.AT;
    /**
     * 全局默认publicKey
     */
    private String publicKey = SecretCryptoUtil.DEFAULT_PUBLIC_KEY_STRING;
}
