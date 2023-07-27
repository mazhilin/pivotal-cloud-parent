package com.pivotal.cloud.application.boot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.application.boot.domain.JwtUserInfo
 * @projectName: 封装PivotalCloud项目-JwtUserInfo类
 * @module: PivotalCloud项目-JwtUserInfo类，主要位于JwtUserInfo模块的业务场景
 * @content: JwtUserInfo类，主要用于完成JwtUserInfo的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-27 23:07
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class JwtUserInfo implements Serializable {
    private Long appId;
    private String tenantId;
    private String identifier;
    private String isolationId;
    private String accountId;
    private String userId;
    private String username;
}
