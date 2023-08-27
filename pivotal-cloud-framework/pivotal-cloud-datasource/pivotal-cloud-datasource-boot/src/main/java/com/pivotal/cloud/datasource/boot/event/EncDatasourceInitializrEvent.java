package com.pivotal.cloud.datasource.boot.event;



import com.pivotal.cloud.datasource.boot.properties.DatasourceProperties;
import com.pivotal.cloud.datasource.boot.utils.SecretCryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @packageName com.pivotal.cloud.datasource.boot.event.EncDatasourceInitializrEvent
 * @projectName: pivotalCloud
 * @className: EncDatasourceInitializrEvent
 * @title: 封装pivotalCloud项目-EncDatasourceInitializrEvent类
 * @content: EncDatasourceInitializrEvent
 * @description: pivotalCloud项目-EncDatasourceInitializrEvent类,主要用作EncDatasourceInitializrEvent。
 * @author: Powered by Marklin
 * @datetime: 2023-06-02 17:23
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 pivotalCloud Systems Incorporated. All rights reserved.
 */
@Slf4j
public class EncDatasourceInitializrEvent implements DatasourceInitializrEvent {

    /**
     * 加密正则
     */
    private static final Pattern ENC_PATTERN = Pattern.compile("^ENC\\((.*)\\)$");


    /**
     * 连接池创建前执行（可用于参数解密）
     *
     * @param properties 数据源基础信息
     */
    @Override
    public void beforeCreate(DatasourceProperties properties) {
        String publicKey = properties.getPublicKey();
        if (StringUtils.hasText(publicKey)) {
            properties.setUrl(decrypt(publicKey, properties.getUrl()));
            properties.setUsername(decrypt(publicKey, properties.getUsername()));
            properties.setPassword(decrypt(publicKey, properties.getPassword()));
        }
    }

    /**
     * 连接池创建后执行
     *
     * @param dataSource 连接池
     */
    @Override
    public void afterCreate(DataSource dataSource) {

    }

    /**
     * 字符串解密
     */
    private String decrypt(String publicKey, String cipherText) {
        if (StringUtils.hasText(cipherText)) {
            Matcher matcher = ENC_PATTERN.matcher(cipherText);
            if (matcher.find()) {
                try {
                    return SecretCryptoUtil.decrypt(publicKey, matcher.group(1));
                } catch (Exception e) {
                    log.error("DynamicDataSourceProperties.decrypt error ", e);
                }
            }
        }
        return cipherText;
    }
}
