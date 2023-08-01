package com.pivotal.cloud.boot.constant;

import java.io.File;

/**
 * @className: com.pivotal.cloud.boot.constant.Constants
 * @projectName: 封装PivotalCloud项目-Constants类
 * @module: PivotalCloud项目-Constants类，主要位于Constants模块的业务场景
 * @content: Constants类，主要用于完成Constants的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 20:32
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public class Constants {
    /**
     * 并且
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 并且
     */
    public static final String AND = "&";
    /**
     * 并且
     */
    public static final String EMPTY = " ";
    /**
     * 下划线
     */
    public static final String LINE = "-";
    /**
     * 下划线
     */
    public static final String UNDERLINE = "_";

    /**
     * 分隔符
     */
    public static final String URL_SPLITOR = "/";

    /**
     * 竖线
     */
    public static final String VLINE = "|";

    /**
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 句号
     */
    public static final String STOP = "。";

    /**
     * 点
     */
    public static final String DOT = ".";

    /**
     * http url冒号
     */
    public static final String COLON = ":";

    /**
     * 百分号
     */
    public static final String PERCENT = "%";

    /**
     * 路径分隔符
     */
    public static final String S = File.separator;

    /**
     * 换行符
     */
    public static final String R = "\n";

    /**
     * 转义字符
     */
    public static final String escape = "\\";

    /**
     * 不可见字符0x01，用于分割字符串
     */
    public static final String unsee0x01 = String.valueOf((char) 0x01);

    /**
     * 点分隔符\\.
     */
    public static final String DOT_SPLITOR = Constants.escape + Constants.DOT;

    /**
     * 问号
     */
    public static final String question = "?";

    /**
     * 等号
     */
    public static final String equal = "=";

    /**
     * 井号
     */
    public static final String well = "#";

    /**
     * at号
     */
    public static final String at = "@";

    /**
     * !号
     */
    public static final String exclamation = "!";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 0;
}
