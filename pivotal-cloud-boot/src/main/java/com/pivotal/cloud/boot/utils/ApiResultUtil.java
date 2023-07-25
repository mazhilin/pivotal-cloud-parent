package com.pivotal.cloud.boot.utils;

import com.pivotal.cloud.boot.pojo.Payload;
import com.pivotal.cloud.boot.timestamp.Timestamp;
import lombok.experimental.UtilityClass;

/**
 * @className: com.smart.cloud.boot.utils.ApiResultUtil
 * @title: 封装SmartCloud项目-ApiResultUtil类
 * @description: <p>
 *         SmartCloud项目-ApiResultUtil
 *         </p>
 * @content: ApiResultUtil
 * @author: Powered by marklin
 * @datetime: 2023-04-30 19:28
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 SmartCloud Systems Incorporated. All rights reserved.
 */
@UtilityClass
public class ApiResultUtil {
    /**
     * 构建结果
     * @param code 编码
     * @param success 状态
     * @param message 消息
     * @param data 数据
     * @param <T> 数据实体
     * @return 返回结果
     */
    public static <T> Payload<T> restResult(int code, boolean success, String message, T data) {
        Payload<T> payload = new Payload<>();
        payload.setCode(code);
        payload.setSuccess(success);
        payload.setData(data);
        payload.setMessage(message);
        payload.setTimestamp(Timestamp.currentTime());
        return payload;
    }

    /**
     * 构建结果
     * @param code 编码
     * @param success 状态
     * @param message 消息
     * @param <T> 数据实体
     * @return 返回结果
     */
    public static <T> Payload<T> restResult(int code,boolean success, String message) {
        Payload<T> payload = new Payload<>();
        payload.setCode(code);
        payload.setSuccess(success);
        payload.setData(null);
        payload.setMessage(message);
        payload.setTimestamp(Timestamp.currentTime());
        return payload;
    }
}
