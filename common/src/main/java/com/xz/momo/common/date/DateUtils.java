package com.xz.momo.common.date;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 【xinzhong】 on 2017/5/22.
 */

public class DateUtils {

    /**
     * 两个时间相差的分钟数
     * @param date1
     * @param date2
     * @return
     */
    public static int diffMinite(Date date1, Date date2){
        if (date1 == null || date2 == null){
            return 0;
        }
        long l = date1.getTime() - date2.getTime();
        long abs = Math.abs(l);
        int min = (int) (abs/1000/60);
        return min;
    }

    /**比较两个时间**/

    public static boolean isEarly(Date date1, Date date2){
        long diffValue = getDiffValue(date1, date2);
        if (diffValue<0){
            return true;
        }
        return false;
    }

    public static long getDiffValue(Date date1, Date date2){
        if (date1==null || date2 ==null){
            return Long.MAX_VALUE;
        }
        long diffValue= date1.getTime()-date2.getTime();
        return diffValue;
    }

    /**String 转 Date **/
    public static Date stringToDate(String dateStr){
        return stringToDate(dateStr, Format.YMDHMS);
    }
    public static Date stringToDate(String dateStr, Format format){
        return stringToDate(dateStr,format.getFormat());
    }
    public static Date stringToDate(String dateStr, String format){

        if (dateStr == null){
            return null;
        }

        if (!isDateFormat(format)){
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**Date 转 String **/
    public static String dateToString(){
        return dateToString(new Date());
    }
    public static String dateToString(Format df){
        return dateToString(new Date(),df);
    }
    public static String dateToString(Date date){
        return dateToString(date, Format.YMDHMS);
    }

    public static String dateToString(Date date, Format df){
        if (df == null){
            df= Format.YMDHMS;
        }
        return dateToString(date,df.getFormat());
    }

    public static String dateToString(Date date, String formate){

        if (date==null){
            date =new Date();
        }

        if (!isDateFormat(formate)){
            return null;
        }
        DateFormat formatter = new SimpleDateFormat(formate);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 所给类型是否在枚举中
     * @param format
     * @return
     */
    private static boolean isDateFormat(String format){
        Format[] values = Format.values();
        for (int i = 0; i < values.length; i++){
            Format value = values[i];
            String format1 = value.getFormat();
            if (TextUtils.equals(format,format1)){
                return true;
            }
        }
        return false;
    }

    /**
     * 枚举类型，所有的时间格式
     */
    public enum Format {

        YMD("yyyy-MM-dd"),
        HMS("HH:mm:ss"),
        YMDHM("yyyy-MM-dd HH:mm"),
        YMDHMS("yyyy-MM-dd HH:mm:ss");

        private String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}
