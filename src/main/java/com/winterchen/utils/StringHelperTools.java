package com.winterchen.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @Describe 字符串处理工具类
 * Created by liugeng on 2016/3/15.
 */
public class StringHelperTools {
    /**
     * null 或者'null' 值转换成空字符串
     *
     * @param args
     * @return 返回转换后数组
     */
    public static Object[] nullStrToString(Object args[]) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null || args[i].toString().equalsIgnoreCase("null")) {
                args[i] = "";
            }
        }
        return args;
    }

    /**
     * null值转换成空字符串
     *
     * @param args
     * @return 返回转换后数组
     */
    public static Object[] nullToString(Object args[]) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                args[i] = "";
            }
        }
        return args;
    }

    /**
     * NULL字符串转换，参数对象为null时，返回空字符串
     *
     * @param object 转换原对象
     * @return 字符串
     */
    public static String nvl(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString().trim();
    }

    /**
     * NULL字符串转换，参数对象为null时，返回默认值
     *
     * @param object 转换原对象
     * @param result 默认值
     * @return 字符串
     */
    public static String nvl(Object object, String result) {
        if (object == null) {
            return result;
        }
        return object.toString().trim();
    }

    /**
     * warlock.deng 2016-03-21
     * 判断是否为空
     *
     * @param str
     * @return true表示为空，false表示不为空
     */
    public static boolean isNvl(String str) {
        boolean b = true;
        if (str != null && !"".equals(str.trim())) {
            b = false;
        }
        return b;
    }

    /**
     * 检查是否为数字。可以包含小数点，但是小数点个数不能多于一个； 可以包含负号，但是不能只有负号而没有其他数字； 不允许包含逗号
     *
     * @param s 被检查的字符串
     * @return true 表示是数字, false 表示不是数字
     */
    public static boolean isNumber(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }

        /**标识是否为小数*/
        boolean pointfirsttime = true;
        /**标识是否为负数*/
        boolean negative = false;
        int i = 0;

        if (s.charAt(0) == '-') {
            i++;
            negative = true;
        }

        while (i < s.length()) {
            if (!Character.isDigit(s.charAt(i))) {
                if ('.' == s.charAt(i) && pointfirsttime) {
                    pointfirsttime = false;
                } else {
                    return false;
                }
            }
            i++;
        }

        if (negative && (i == 1)) {
            return false;
        }

        return true;
    }

    /**
     * 检查是否为整数。可以为负整数，但是不能只有负号而没有其他数字
     *
     * @param s 被检查的字符串
     * @return true 表示是整数, false 表示不是整数
     */
    public static boolean isInteger(String s) {
        if (s == null || s.trim().length() < 1) {
            return false;
        }

        boolean negative = false;
        int i = 0;

        if (s.charAt(0) == '-') {
            i++;
            negative = true;
        }

        while (i < s.length()) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
            i++;
        }
        if (negative && (i == 1)) {
            return false;
        }

        return true;
    }

    /**
     * 检查是否为合法的Email
     *
     * @param mail 字符串
     * @return true 合法，false 非法
     */
    public static boolean checkMailAddress(String mail) {
        if (mail == null) {
            return false;
        }

        String mailstr = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern pn = Pattern.compile(mailstr);

        boolean b = pn.matcher(mail).matches();

        System.out.println(b);

        return b;

    }

    /**
     * 对GBK字符串进行转码成UTF-8
     *
     * @param str 待解码字符串
     * @return 字符串
     * @throws Exception
     */
    public static String strEncoding(String str, String fromEncoding, String toEncoding) throws Exception {
        if (str != null && !"".equals(str)) {
            try {
                str = new String(str.getBytes(fromEncoding), toEncoding);
            } catch (Exception e) {
                throw e;
            }
        }

        return str;
    }

    /**
     * 把字符串转化成整型
     *
     * @param content 要转化的字符串
     * @return
     */
    public static int StringConInteger(String content) {
        content = nvl(content);
        if (!"".equals(content) && isInteger(content)) {
            return Integer.parseInt(content);
        }
        return 0;
    }

    /**
     * warlock.deng 2016-03-21
     * 将objet类型数据转换为long类型
     *
     * @param obj
     * @return 转换失败返回0，成功返回有效值
     */
    public static long ObjectToLong(Object obj) {
        long l = 0L;
        if (obj != null) {
            try {
                l = Long.parseLong(obj.toString().trim());
            } catch (Exception ex) {
                throw ex;
            }
        }
        return l;
    }

    /**
     * warlock.deng 2016-03-22
     * long转日期格式
     *
     * @param mins     时间戳
     * @param formater 日期格式
     */
    public static String GetFormatDate(long mins, String formater) {
        if (isNvl(formater))
            formater = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(formater);
        Date date = new Date(mins);
        return format.format(date);
    }

    /**
     * warlock.deng 2016-03-22
     * 将时间转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                format = isNvl(format) ? "yyyy-MM-dd HH:mm:ss" : format;
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }


    /**
     * warlock.deng 2016-03-22
     * 是否职位扩展编号
     *
     * @param positionNumber CZ268262636J90250000000 职位扩展编号
     * @return true 表示为有效的职位扩展编号
     */
    public static boolean isPositionNumber(String positionNumber) {
        boolean b = false;
        final String regEx = "^CZ[0-9]{9}J[0-9]{11}$";
        if (!isNvl(positionNumber)) {
            b = Pattern.matches(regEx, positionNumber);
        }
        return b;
    }

    /**
     * warlock.deng 2016-03-22
     * 判断职位扩展编号是否都有效，多个用","分开
     *
     * @param positionNumbers
     * @return
     */
    public static boolean isPositionNumberList(String positionNumbers) {
        boolean b = false;
        if (!isNvl(positionNumbers)) {
            for (String p : positionNumbers.split(",")) {
                b = isPositionNumber(p);
                if (!b)
                    break;
            }
        }
        return b;
    }

    /**
     * 删除字符串前后字符
     *
     * @param strOld 需要处理的字符串
     * @param c      检查的字符
     * @return
     */
    public static String trim(String strOld, char c) {
        if (!isNvl(strOld) && strOld.trim().length() > 0) {
            strOld = strOld.trim();
            String first = strOld.substring(0, 1);
            if (first.equals(String.valueOf(c))) {
                strOld = strOld.substring(1, strOld.length());
            }
            if (strOld.length() > 0) {
                String last = strOld.substring(strOld.length() - 1, strOld.length());
                if (last.equals(String.valueOf(c))) {
                    strOld = strOld.substring(0, strOld.length() - 1);
                }
            }
        }
        return strOld;
    }

}
