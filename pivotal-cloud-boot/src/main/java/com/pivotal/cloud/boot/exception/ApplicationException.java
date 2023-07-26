package com.pivotal.cloud.boot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.boot.exception.ApplicationException
 * @projectName: 封装PivotalCloud项目-ApplicationException类
 * @module: PivotalCloud项目-ApplicationException类，主要位于ApplicationException模块的业务场景
 * @content: ApplicationException类，主要用于完成ApplicationException的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 21:39
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException implements Serializable {
    /**
     * Instantiates a new Shenyu exception.
     *
     * @param e the e
     */
    public ApplicationException(final Throwable e) {
        super(e);
    }

    /**
     * Instantiates a new Shenyu exception.
     *
     * @param message the message
     */
    public ApplicationException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new Shenyu exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public ApplicationException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
