package com.pivotal.cloud.boot.pojo;

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
}
