package com.xz.momo.common.http;


import com.xz.momo.common.log.RLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;

/**
 * Created by 【xinzhong】 on 2017/6/21.
 */

public class HeaderUtils {

    public static final Map<String,String> cookieMap = new HashMap<>();

    public static void clearCookie(){
        cookieMap.clear();
    }

    public static void parseCookie(Headers headers){

        List<String> values = headers.values("Set-Cookie");
        for (String str : values){
            String[] split = str.split(";");
            for (String ss :split){
                String s = ss.trim();
                if (s.contains("=")){

                    //不采用split方法是防止value中包含=
                    int position = s.indexOf("=");
                    String key = s.substring(0,position);
                    String value = s.substring(position+1,s.length());
                    cookieMap.put(key,value);

                }
            }
        }
    }


    public static String getCookie(){

        StringBuffer sb= new StringBuffer();

        Set<String> strings = cookieMap.keySet();
        for (String key :strings){
            String value = cookieMap.get(key);
            String str=key+"="+value+";";
            RLog.d("xin",str);
            sb.append(str);
        }

        return sb.toString();
    }

    public static Map<String, String> getCookieMap(){
        String cookie = getCookie();
        Map<String, String> map = new HashMap<>();
        map.put("Cookie",cookie);
        return map;
    }
}
