package com.pivotal.cloud.boot.pojo;

import java.io.Serializable;

/**
 * @className: com.pivotal.cloud.boot.pojo.Metadata
 * @projectName: 封装PivotalCloud项目-Metadata类
 * @module: PivotalCloud项目-Metadata类，主要位于Metadata模块的业务场景
 * @content: Metadata类，主要用于完成Metadata的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-28 01:55
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */

public final class Metadata implements Serializable {

    public static final String METADATA_VERSION = "VERSION";
    public static final String METADATA_CONTEXT = "CONTEXT";

    private static final long serialVersionUID = -2174604817152173741L;

    private String key;

    private String value;

    public Metadata(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
