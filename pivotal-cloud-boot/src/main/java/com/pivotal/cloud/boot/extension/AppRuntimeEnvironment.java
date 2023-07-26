package com.pivotal.cloud.boot.extension;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.pivotal.cloud.boot.Environment;
import com.pivotal.cloud.boot.exception.ApplicationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @className: com.pivotal.cloud.boot.extension.AppRuntimeEnvironment
 * @projectName: 封装PivotalCloud项目-AppRuntimeEnvironment类
 * @module: PivotalCloud项目-AppRuntimeEnvironment类，主要位于AppRuntimeEnvironment模块的业务场景
 * @content: AppRuntimeEnvironment类，主要用于完成AppRuntimeEnvironment的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 21:37
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Component
@Configuration
public class AppRuntimeEnvironment implements Environment {
    /**
     * 系统租户id
     */
    private ThreadLocal<String> tenantId = TransmittableThreadLocal.withInitial(() -> null);

    /**
     * accountId 账号ID
     */
    private ThreadLocal<Long> accountId = ThreadLocal.withInitial(() -> null);

    /**
     * 系统租户id
     */
    private ThreadLocal<String> isolationId = TransmittableThreadLocal.withInitial(() -> null);

    /**
     * 系统组织id
     */
    private ThreadLocal<String> identifierCode = TransmittableThreadLocal.withInitial(() -> null);

    /**
     * 系统Token
     */
    private ThreadLocal<String> token = TransmittableThreadLocal.withInitial(() -> null);

    /**
     * 系统应用Id
     */
    private ThreadLocal<Long> appId = TransmittableThreadLocal.withInitial(() -> null);

    /**
     * userId信息
     */
    private ThreadLocal<Long> userId = TransmittableThreadLocal.withInitial(() -> null);
    /**
     * 用户名
     */
    private ThreadLocal<String> username = TransmittableThreadLocal.withInitial(() -> "ANONYMOUS");

    /**
     * 用户昵称
     */
    private ThreadLocal<String> nickName = TransmittableThreadLocal.withInitial(() -> null);

    /**
     * requestId 请求ID
     */
    private ThreadLocal<String> requestId = TransmittableThreadLocal.withInitial(() -> null);

    public AppRuntimeEnvironment ensureToken(String token) {
        if (null == token) {
            throw new ApplicationException("");
        }
        this.token.set(token);
        return this;
    }

    public AppRuntimeEnvironment ensureTenantId(String tenantId) {
        if (null == tenantId) {
            throw new ApplicationException("");
        }
        this.tenantId.set(tenantId);
        return this;
    }

    public AppRuntimeEnvironment ensureIdentifierCode(String identifier) {
        if (null == identifier) {
            throw new ApplicationException("");
        }
        this.identifierCode.set(identifier);
        return this;
    }

    public AppRuntimeEnvironment ensureUsername(String username) {
        if (null == username) {
            throw new ApplicationException("");
        }
        this.username.set(username);
        return this;
    }

    public AppRuntimeEnvironment ensureRequestId(String requestId) {
        if (null == requestId) {
            throw new ApplicationException("");
        }
        this.requestId.set(requestId);
        return this;
    }

    public String getTenantId() {
        return tenantId.get();
    }

    public void setTenantId(String tenantId) {
        this.tenantId.set(tenantId);
    }

    public String getToken() {
        return token.get();
    }

    public void setToken(String token) {
        this.token.set(token);
    }

    public Long getUserId() {
        return userId.get();
    }

    public void setUserId(Long userId) {
        this.userId.set(userId);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getNickName() {
        return nickName.get();
    }

    public void setNickName(String nickName) {
        this.nickName.set(nickName);
    }

    public String getRequestId() {
        return requestId.get();
    }

    public void setRequestId(String requestId) {
        this.requestId.set(requestId);
    }

    public Long getAppId() {
        return appId.get();
    }

    public void setAppId(Long appId) {
        this.appId.set(appId);
    }

    /**
     * 清空上下文内容
     */
    @Override
    public void clearContext() {
        this.setRequestId(null);
        this.setAppId(null);
        this.setToken(null);
        this.setUserId(null);
        this.setUsername(null);
        this.setNickName(null);
    }

    @Override
    public void destroy() throws Exception {
        this.clearContext();
    }
}
