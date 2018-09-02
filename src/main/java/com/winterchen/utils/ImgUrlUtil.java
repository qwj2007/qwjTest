package com.winterchen.utils;

/**
 * Created by wangxy on 2017/5/18.
 */
public class ImgUrlUtil {

    public static String getPlaceholderURL(String avatar){
        String first = avatar.substring(0,avatar.lastIndexOf("."));
        String end = avatar.substring(avatar.lastIndexOf("."));
        first += "_{0}x{1}";
        first += end;

        return first;
    }
}
