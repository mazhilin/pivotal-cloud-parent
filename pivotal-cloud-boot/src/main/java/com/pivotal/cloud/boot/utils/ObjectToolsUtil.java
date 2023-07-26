package com.pivotal.cloud.boot.utils;

import lombok.experimental.UtilityClass;

/**
 * @className: com.pivotal.cloud.boot.utils.ObjectToolsUtil
 * @projectName: 封装项目-ObjectToolsUtil类
 * @module: 项目-ObjectToolsUtil类，主要位于ObjectToolsUtil模块的业务场景
 * @content: ObjectToolsUtil类，主要用于完成ObjectToolsUtil的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 20:40
 * @version: 1.0.
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
@UtilityClass
public class ObjectToolsUtil {

    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
