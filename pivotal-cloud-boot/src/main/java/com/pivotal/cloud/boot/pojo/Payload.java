package com.pivotal.cloud.boot.pojo;

import com.pivotal.cloud.boot.exception.ErrorMessage;
import com.pivotal.cloud.boot.timestamp.Timestamp;
import com.pivotal.cloud.boot.utils.ApiResultUtil;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.boot.pojo.Payload
 * @projectName: 封装PivotalCloud项目-Payload类
 * @module: PivotalCloud项目-Payload类，主要位于Payload模块的业务场景
 * @content: Payload类，主要用于完成Payload的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-11 20:31
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Builder
@ToString
@AllArgsConstructor
@Accessors(chain = true)
public class Payload<T> implements Serializable {

    private static final long serialVersionUID = -3782237229011631148L;

    /**
     * 编码-code
     */
    @Getter
    @Setter
    private Integer code = 200;

    /**
     * 状态-success
     */
    @Getter
    @Setter
    private Boolean success = true;

    /**
     * 消息-message
     */
    @Getter
    @Setter
    private String message = "成功";

    /**
     * 数据-data
     */
    @Getter
    @Setter
    private T data;

    /**
     * 接口调用时间-timestamp
     */
    @Getter
    @Setter
    private long timestamp;

    public Payload (){
        this.timestamp = Timestamp.currentTime();
    }

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     * <p>
     * 因为 A 方法返回的 Payload 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param <T>     返回的泛型
     * @param payload 传入的 result 对象
     * @return Payload 对象
     */
    public static <T> Payload<T> error(Payload<?> payload) {
        return ApiResultUtil.restResult(payload.getCode(),false,payload.getMessage());
    }


    /**
     * @param code
     * @param message
     * @param <T>     返回的泛型
     * @return Payload 对象
     */
    public static <T> Payload<T> error(Integer code, String message) {
        return ApiResultUtil.restResult(code, false, message, null);
    }

    /**
     * @param error 错误对象
     * @param <T>   返回的泛型
     * @return Payload 对象
     */
    public static <T> Payload<T> error(ErrorMessage error) {
        return ApiResultUtil.restResult(error.getCode(), false, error.getDesc());
    }

    /**
     * @param payload 错误对象
     * @param <T>     返回的泛型
     * @return Payload 对象
     */
    public static <T> Payload<T> success(Payload<T> payload) {
        return ApiResultUtil.restResult(payload.getCode(), true, payload.getMessage(), payload.getData());
    }

    /**
     * @param <T> 返回的泛型
     * @return Payload 对象
     */
    public static <T> Payload<T> success() {
        return ApiResultUtil.restResult(200, true, "请求成功！", null);
    }

}
