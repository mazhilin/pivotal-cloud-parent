package com.pivotal.cloud.boot.constant;

/**
 * @className: com.pivotal.cloud.boot.constant.Formats
 * @projectName: 封装PivotalCloud项目-Formats类
 * @module: PivotalCloud项目-Formats类，主要位于Formats模块的业务场景
 * @content: Formats类，主要用于完成Formats的封装和定义.
 * @author: Powered by Marklin
 * @datetime: 2023-07-26 20:34
 * @version: 1.0.0
 * @copyright: Copyright © 2018-2023 PivotalCloud Systems Incorporated. All rights reserved.
 */
public final class Formats {
    /**
     * 年
     */
    public static final String YEAR = "yyyy";

    /**
     * 月
     */
    public static final String MONTH = "MM";

    /**
     * 日
     */
    public static final String DAY = "dd";

    /**
     * 时
     */
    public static final String HOUR = "HH";

    /**
     * 分
     */
    public static final String MINUTE = "mm";

    /**
     * 秒
     */
    public static final String SECOND = "ss";

    /**
     * 日期
     */
    public static final String DATE = YEAR + Constants.LINE + MONTH + Constants.LINE + DAY;
    /**
     * 时间
     */
    public static final String TIME = HOUR + Constants.COLON + MINUTE + Constants.COLON + SECOND;
    /**
     * 日期+时间
     */
    public static final String DATETIME = DATE + Constants.EMPTY + TIME;


    public static void main(String[] args) {
        System.out.println(Formats.DATE);
        System.out.println(Formats.TIME);
        System.out.println(Formats.DATETIME);
        System.out.println(Formats.DATETIME.length());
        System.out.println("yyyy-MM-dd HH:mm:ss".length());
    }
}
