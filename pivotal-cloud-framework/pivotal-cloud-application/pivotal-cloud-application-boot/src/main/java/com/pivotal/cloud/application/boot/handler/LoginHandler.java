package com.pivotal.cloud.application.boot.handler;

import com.pivotal.cloud.application.boot.domain.CurrentUser;
import com.pivotal.cloud.application.boot.domain.UserInfo;

/**
 * @className: com.pivotal.cloud.application.boot.handler.LoginHandler
 * @projectName: 封装PivotalCloud项目-LoginHandler类
 * @module: PivotalCloud项目-LoginHandler类，主要位于LoginHandler模块的业务场景
 * @content: LoginHandler类，主要用于完成LoginHandler的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-02 03:51
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public interface LoginHandler {
    /***
     * 数据合法性校验
     * @param identifier 通过用户传入获取唯一标识
     * @return
     */
    Boolean check(String identifier);

    /**
     * 通过用户传入获取唯一标识
     * @param identifier
     * @return
     */
    String identify(String identifier);

    /**
     * 通过openId 获取用户信息
     * @param identifier
     * @return
     */
    UserInfo info(String identifier);

    /**
     * 处理方法
     * @param identifier 登录参数
     * @return
     */
    UserInfo handle(String identifier);

    /**
     * 绑定逻辑
     * @param user 用户实体
     * @param identifier 渠道返回唯一标识
     * @return
     */
    default Boolean bind(CurrentUser user, String identifier) {
        return false;
    }
}
