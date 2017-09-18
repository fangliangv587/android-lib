package com.xz.momo.common.text;

import java.math.BigDecimal;

/**
 * Created by 【xinzhong】 on 2017/6/6.
 */

public class NumberUtils {

    /**
     * 获取float 小数点后几位数
     * 方法BigDecimal影响性能，造成卡顿。循环延时中禁用
     * @param f
     * @param places
     * @return
     */
    public static float gotFloat(float f,int places){

        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    /**
     * 取小数点后几位
     * @param f
     * @param places  位数
     * @param round  是否四舍五入
     * @return
     *
     *  double v = (new BigDecimal("0.5")).subtract(new BigDecimal("0.45")).doubleValue();
     */
    public static float getFloat(float f,int places,boolean round){
        double pow = Math.pow(10, places);
        int a =(int)(f*pow);
        if (round){
           a= (int) Math.round(f*pow);
        }
        float result= (float) (a/pow*1.0f);
        return result;
    }

    /**
     * 取小数点后几位
     * @param f
     * @param places
     * @return
     */
    public static float getFloat(float f,int places){
        return getFloat(f,places,false);
    }

    public static void main(String[] args){
        float f = 1.23456789f;
        int places = 4;
        float aFloat = getFloat(f, places);
        System.out.println(aFloat);
    }
}
