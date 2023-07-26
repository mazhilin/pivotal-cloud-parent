package com.pivotal.cloud.boot.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.boot.exception.ErrorMessage
 * @projectName: 封装PivotalCloud项目-ErrorMessage类
 * @module: PivotalCloud项目-ErrorMessage类，主要位于ErrorMessage模块的业务场景
 * @content: ErrorMessage类，主要用于完成ErrorMessage的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-25 21:45
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = -3625974231533756736L;

    /**
     * 错误码-code
     */
    private final Integer code;
    /**
     * 错误提示-desc
     */
    private final String desc;

    public ErrorMessage(Integer code, String message) {
        this.code = code;
        this.desc = message;
    }
}
