package com.pivotal.cloud.datacache.boot.holder;

import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @className: com.pivotal.cloud.datacache.boot.holder.DynamicDatacacheContextHolder
 * @projectName: 封装PivotalCloud项目-DynamicDatacacheContextHolder类
 * @module: PivotalCloud项目-DynamicDatacacheContextHolder类，主要位于DynamicDatacacheContextHolder模块的业务场景
 * @content: DynamicDatacacheContextHolder类，主要用于完成DynamicDatacacheContextHolder的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-08-28 01:57
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public final class DynamicDatacacheContextHolder {
    /**
     * 为什么要用链表存储(准确的是栈)
     * <pre>
     * 为了支持嵌套切换，如ABC三个service都是不同的数据源
     * 其中A的某个业务要调B的方法，B的方法需要调用C的方法。一级一级调用切换，形成了链。
     * 传统的只设置当前线程的方式不能满足此业务需求，必须使用栈，后进先出。
     * </pre>
     */
    private static final ThreadLocal<Deque<String>> LOOKUP_KEY_HOLDER = new NamedThreadLocal<Deque<String>>("dynamic-datacache") {
        @Override
        protected Deque<String> initialValue() {
            return new ArrayDeque<>();
        }
    };

    private DynamicDatacacheContextHolder() {
    }

    /**
     * 获得当前线程数据源
     *
     * @return 数据源名称
     */
    public static String peek() {
        return LOOKUP_KEY_HOLDER.get().peek();
    }

    /**
     * 设置当前线程数据源
     * <p>
     * 如非必要不要手动调用，调用后确保最终清除
     * </p>
     *
     * @param ds 数据源名称
     */
    public static String push(String ds) {
        String datacache = StringUtils.isEmpty(ds) ? "" : ds;
        LOOKUP_KEY_HOLDER.get().push(datacache);
        return datacache;
    }

    /**
     * 清空当前线程数据源
     * <p>
     * 如果当前线程是连续切换数据源 只会移除掉当前线程的数据源名称
     * </p>
     */
    public static void poll() {
        Deque<String> deque = LOOKUP_KEY_HOLDER.get();
        deque.poll();
        if (deque.isEmpty()) {
            LOOKUP_KEY_HOLDER.remove();
        }
    }

    /**
     * 强制清空本地线程
     * <p>
     * 防止内存泄漏，如手动调用了push可调用此方法确保清除
     * </p>
     */
    public static void clear() {
        LOOKUP_KEY_HOLDER.remove();
    }
}
