package com.pivotal.cloud.boot.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @className: com.pivotal.cloud.boot.pojo.Pair
 * @projectName: 封装PivotalCloud项目-Pair类
 * @module: PivotalCloud项目-Pair类，主要位于Pair模块的业务场景
 * @content: Pair类，主要用于完成Pair的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-28 00:56
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@Data
public class Pair<S, T> implements Serializable {

    private static final long serialVersionUID = -8332125264097179278L;

    private S first;
    private T second;

    public Pair() {
    }

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair<>(first, second);
    }
}
