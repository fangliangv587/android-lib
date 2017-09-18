package com.xz.momo.common.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 【xinzhong】 on 2017/5/19.
 * 正则工具类
 */

public class RegularUtil {

    /**
     * 判断字符串是否为纯数字
     * 字符串强转int时最好调用判断下
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
