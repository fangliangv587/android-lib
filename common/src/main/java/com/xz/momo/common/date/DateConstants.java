package com.xz.momo.common.date;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/03/13
 *     desc  : 时间相关常量
 * </pre>
 */
public final class DateConstants {

    /**
     * 毫秒与毫秒的倍数
     */
    public static final int MSEC = 1;
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC  = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN  = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY  = 86400000;

    @IntDef({MSEC, SEC, MIN, HOUR, DAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }


    /**
     * 日期格式
     */
    public static final String YMD = "yyyy-MM-dd";
    public static final String HMS = "HH:mm:ss";
    public static final String YMDHM = "yyyy-MM-dd HH:mm";
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    @StringDef({YMD, HMS, YMDHM, YMDHMS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Format{

    }


    /**
     * 空
     */
    public static final int NULL = 0;
    /**
     * 天
     */
    public static final int D = 1;
    /**
     * 天、小时
     */
    public static final int DH = 2;
    /**
     * 天、小时、分钟
     */
    public static final int DHM = 3;
    /**
     * 天、小时、分钟、秒
     */
    public static final int DHMS = 4;
    /**
     * 天、小时、分钟、秒和毫秒
     */
    public static final int DHMSM = 5;

    @IntDef({NULL, D, DH, DHM, DHMS, DHMSM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Precision {
    }
}
