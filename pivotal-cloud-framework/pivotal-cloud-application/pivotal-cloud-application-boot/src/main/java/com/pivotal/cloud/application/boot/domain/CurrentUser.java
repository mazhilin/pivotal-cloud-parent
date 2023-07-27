package com.pivotal.cloud.application.boot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.application.boot.domain.CurrentUser
 * @projectName: 封装PivotalCloud项目-CurrentUser类
 * @module: PivotalCloud项目-CurrentUser类，主要位于CurrentUser模块的业务场景
 * @content: CurrentUser类，主要用于完成CurrentUser的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-27 23:14
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class CurrentUser implements Serializable {
    private Long appId;
    private String tenantId;
    private String identifier;
    private String isolationId;
    private String accountId;
    private String userId;
    private String username;
}
