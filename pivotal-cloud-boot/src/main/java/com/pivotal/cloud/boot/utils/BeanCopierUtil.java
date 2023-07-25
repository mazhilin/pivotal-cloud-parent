package com.pivotal.cloud.boot.utils;

import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;

/**
 * @className: com.pivotal.cloud.boot.utils.BeanCopierUtil
 * @projectName: 封装PivotalCloud项目-BeanCopierUtil类
 * @module: PivotalCloud项目-BeanCopierUtil类，主要位于BeanCopierUtil模块的业务场景
 * @content: BeanCopierUtil类，主要用于完成BeanCopierUtil的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-25 22:16
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@UtilityClass
public class BeanCopierUtil {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(BeanCopierUtil.class);

    /**
     * BeanCopier缓存
     */
    public static Map<String, BeanCopier> beanCopierCacheMap = Maps.newConcurrentMap();


    /**
     * 将source对象的属性拷贝到target对象中去
     *
     * @param source source对象
     * @param target target对象
     */
    public static void copyProperties(Object source, Object target) {
        String cacheKey = source.getClass().toString() + target.getClass().toString();

        BeanCopier beanCopier = null;

        if (!beanCopierCacheMap.containsKey(cacheKey)) {
            synchronized (BeanCopierUtil.class) {
                if (!beanCopierCacheMap.containsKey(cacheKey)) {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    beanCopierCacheMap.put(cacheKey, beanCopier);
                } else {
                    beanCopier = beanCopierCacheMap.get(cacheKey);
                }
            }
        } else {
            beanCopier = beanCopierCacheMap.get(cacheKey);
        }

        try {
            beanCopier.copy(source, target, null);
        } catch (Exception e) {
            logger.error("copy error", e);
            throw e;
        }

    }
}
