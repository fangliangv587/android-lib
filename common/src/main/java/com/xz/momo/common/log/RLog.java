package com.xz.momo.common.log;

import android.util.Log;

/**
 * Created by Administrator on 2017/5/13.
 */

public class RLog {
    public static final String LIB_TGA="xin";
    public static void d(String tag, String mes){
        Log.d(tag,mes);
    }
    public static void i(String tag, String mes){
        Log.i(tag,mes);
    }
    public static void d(String mes){
        Log.d(LIB_TGA,mes);
    }
    public static void i(String mes){
        Log.i(LIB_TGA,mes);
    }
    public static void e(String mes){
        e(LIB_TGA,mes);
    }
    public static void e(String tag, String mes){
        Log.e(tag,mes);
    }
}
