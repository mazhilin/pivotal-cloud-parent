package com.pivotal.cloud.application.boot.handler;

import com.pivotal.cloud.application.boot.domain.UserInfo;

/**
 * @className: com.pivotal.cloud.application.boot.handler.AbstractLoginHandler
 * @projectName: 封装PivotalCloud项目-AbstractLoginHandler类
 * @module: PivotalCloud项目-AbstractLoginHandler类，主要位于AbstractLoginHandler模块的业务场景
 * @content: AbstractLoginHandler类，主要用于完成AbstractLoginHandler的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-02 03:55
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public abstract class AbstractLoginHandler implements LoginHandler  {

    /***
     * 数据合法性校验
     * @param identifier 通过用户传入获取唯一标识
     * @return 默认不校验
     */
    @Override
    public Boolean check(String identifier) {
        return true;
    }

    /**
     * 处理方法
     * @param identifier 登录参数
     * @return
     */
    @Override
    public UserInfo handle(String identifier) {
        if (!check(identifier)) {
            return null;
        }

        String identify = identify(identifier);
        return info(identify);
    }
}
