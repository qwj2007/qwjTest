package com.winterchen.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mafei on 2015/8/19.
 */
public class Utils {
    public  static boolean IsNullOrEmpty(String str){
        return  str == null || str.length() <= 0;
    }

    public static int GetInt(Object obj)
    {
        int def=0;
        if (obj != null)
        {
            try
            {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            }
            catch(Exception ex )
            { }

        }
        return def;
    }
    public static int GetInt(Object obj, int def)
    {
        if (obj != null)
        {
            try
            {
                def = Integer.parseInt(obj == null ? "" : obj.toString());
            }
            catch(Exception ex )
            { }

        }
        return def;
    }
    public static long GetLong(Object obj, long def)
    {
        if (obj != null)
        {
            try
            {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            }
            catch(Exception ex )
            { }

        }
        return def;
    }
    public static long GetLong(Object obj)
    {
       long def=0;
        if (obj != null)
        {
            try
            {
                def = Long.parseLong(obj == null ? "" : obj.toString());
            }
            catch(Exception ex )
            { }

        }
        return def;
    }
    public static double GetDouble(Object obj, double def)
    {
        if (obj != null)
        {
            try
            {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            }
            catch(Exception ex )
            { }

        }
        return def;
    }
    public static double GetDouble(Object obj)
    {
        double def=0.0;
        if (obj != null)
        {
            try
            {
                def = Double.parseDouble(obj == null ? "" : obj.toString());
            }
            catch(Exception ex )
            { }

        }
        return def;
    }
    public static String GetString(Object obj)
    {
        String def="";
        if (obj != null)
        {
            def = obj.toString();

        }
        return def;

    }
    public static String GetString(Object obj, String def)
    {
        if (obj != null)
        {
            def = obj.toString();

        }
        return def;

    }

    /**
     *  判断请求是否来自于移动端，手机和pad。
     * @param userAgent
     * @return
     */
    public static boolean IsFromMoblie(String userAgent)
    {
        boolean bRes = false;
        if (!Utils.IsNullOrEmpty(userAgent))
        {
            userAgent = userAgent.toLowerCase();
            if (userAgent.indexOf("mobile") != -1 ||
                    userAgent.indexOf("mobi") != -1 ||
                    userAgent.indexOf("android") != -1 ||
                    userAgent.indexOf("iphone") != -1 ||
                    userAgent.indexOf("nokia") != -1 ||
                    userAgent.indexOf("samsung") != -1 ||
                    userAgent.indexOf("sonyericsson") != -1 ||
                    userAgent.indexOf("mot") != -1 ||
                    userAgent.indexOf("blackberry") != -1 ||
                    userAgent.indexOf("lg") != -1 ||
                    userAgent.indexOf("htc") != -1 ||
                    userAgent.indexOf("j2me") != -1 ||
                    userAgent.indexOf("ucweb") != -1 ||
                    userAgent.indexOf("opera mini") != -1)
            {
                bRes = true;
            }
        }
        return bRes;
    }

    public static String padLeft(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }
    public static String padRight(String oriStr, int len, char alexin) {
        String str = "";
        int strlen = oriStr.length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }
    /**
     * long转日期格式
     * @param  mins 时间戳
     * @param formater 日期格式
     */
    public static String GetFormatDate(long mins,String formater) {
        if(Utils.IsNullOrEmpty(formater))
            formater="yyyy-MM-dd";
        SimpleDateFormat format=new SimpleDateFormat(formater);
        Date date=new Date(mins);
        return format.format(date);
    }
    /**
     * 日期添加
     */
    public static Date GetDateAfterDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
    /**
     * 获取以某分隔符分开后的项的个数
     * @param value 指定的字符串
     * @param splitChar 分隔符
     * @return list
     */
    public static List<String> GetSplitList(String value,String splitChar) {
        List<String> valueList=new ArrayList<String>();
        if(!Utils.IsNullOrEmpty(value)) {
            int valueLength = 0;
            String[] values = value.split(splitChar);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != "") {
                    valueList.add(values[i]);
                }
            }
        }
        return valueList;
    }
    /**
     * 获取以某分隔符分开后的项的个数
     * @param value 指定的字符串
     * @param splitChar 分隔符
     * @return list
     */
    public static List<Long> GetSplitLongList(String value,String splitChar) {
        List<Long> valueList=new ArrayList<Long>();
        if(!Utils.IsNullOrEmpty(value)) {
            int valueLength = 0;
            String[] values = value.split(splitChar);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != "") {
                    valueList.add(Utils.GetLong(values[i]));
                }
            }
        }
        return valueList;
    }
    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int StringLength(String value) {
        if(Utils.IsNullOrEmpty(value))
            return  0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
    /**
     * 去除字符窜尾字符
     * @param   str 字符串
     * @param   charStr 尾字符
     * @return  string
     */
    public  static  String TrimEnd(String str,String charStr)
    {
        if(!Utils.IsNullOrEmpty((str))&&!Utils.IsNullOrEmpty(charStr))
        {
            if(str.substring(str.length()-1).equals(charStr)) {
                return str.substring(0, str.length() - 1);
            }
        }
        return  str;
    }
    /**
     * 去除字符
     * @param   str 字符串
     * @param   charStr 尾字符
     * @return  string
     */
    public  static  String ReplaceChar(String str,String charStr)
    {
        String result="";
        if(!Utils.IsNullOrEmpty((str))&&!Utils.IsNullOrEmpty(charStr))
        {
            for(String item:str.split(charStr)){
                if(!item.equals("")){
                    result+=item+",";
                }
            }
            result=Utils.TrimEnd(result,charStr);
        }
        return  result;
    }
    /**
     * hashmap转url拼接地址
     * @param url  接口地址
     * @param data  字符串
     * @return  string
     */
    public static String QueryString(String url,Map<String ,Object> data) {
        if(data!=null&&data.size()>0) {
            for (Map.Entry<String, Object> item : data.entrySet()) {
                url += item.getKey() + "=" + item.getValue() + "&";
            }
            url = Utils.TrimEnd(url, "&");
        }
        return url;
    }

    /**
     * 将特殊字符转义
     * @return
     */
    public static String HtmlEiscode(String str)
    {
        if (!Utils.IsNullOrEmpty(str))
        {
            str=str.replace(">","&gt;");
            str=str.replace("<","&lt;");
            str=str.replace(" ","&nbsp;");
            str=str.replace("\\","&quot;");
            str=str.replace("|","&brvbar;");
            str=str.replace("'","\\'");
            str=str.replace("\"", "\\");
        }
        return str;
    }
    /**
     * 将特殊字符转义
     * @return
     */
    public static String Htmlencode(String str)
    {
        if (!Utils.IsNullOrEmpty(str))
        {
            str=str.replace(">","&gt;");
            str=str.replace("<","&lt;");
            str=str.replace(" ","&nbsp;");
        }
        return str;
    }
    /**
     * 将特殊字符转义 反
     * @return
     */
    public static String Htmldecode(String str)
    {
        if (!Utils.IsNullOrEmpty(str))
        {
            str=str.replace("&gt;",">");
            str=str.replace("&lt;","<");
            str=str.replace("&nbsp;"," ");
        }
        return str;
    }
    /**
     * 过滤sql等非法关键字
     * @param str
     * @return
     */
    public static String GetSafeQuery(String str)
    {
        if (!Utils.IsNullOrEmpty(str))
        {
            str=str.replace(">","").replace("<", "");
            str=str.replaceAll("(?i)insert", "");
            str=str.replaceAll("(?i)update", "");
            str=str.replaceAll("(?i)delete", "");
            str=str.replaceAll("(?i)select", "");
            //str=str.replaceAll("(?i)and", "");
            str=str.replaceAll("(?i)where", "");
            //str=str.replaceAll("(?i)or", "");
            //str=str.replaceAll("(?i)limit", "");
        }
        return str;
    }
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    /**
     * 删除字符串中的html标记
     * @param htmlStr
     * @return
     */
    public static String ClearHtml(String htmlStr)
    {
        if (!Utils.IsNullOrEmpty(htmlStr))
        {
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            return htmlStr; // 返回文本字符串
        }
        return htmlStr;
    }

    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static  String ChangeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
    /**
     * 将字符编码转换成UTF-8码
     */
    public static String toUTF_8(String str){
        try
        {
            return Utils.ChangeCharset(str, "UTF_8");
        }
        catch (UnsupportedEncodingException ex) {}
        return  str;
    }
    /**
     * 将字符编码转换成gb2312码
     */
    public static String toGb2312(String str){
        try
        {
            return Utils.ChangeCharset(str, "gb2312");
        }
        catch (UnsupportedEncodingException ex) {}
        return  str;
    }
    /**
     * 去除特殊字符
     * @param htmlStr
     * @return
     */
    public  static  String ClearSpecialChar(String htmlStr,String regEx)
    {
        if(!Utils.IsNullOrEmpty(htmlStr)&&!Utils.IsNullOrEmpty(regEx)) {
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(htmlStr);
            return m.replaceAll("");
        }
        return  htmlStr;
    }
    /**
     * 正则验证
     * @param htmlStr
     * @return
     */
    public  static  boolean ValidateCustomer(String htmlStr,String regEx)
    {
        if(!Utils.IsNullOrEmpty(htmlStr)&&!Utils.IsNullOrEmpty(regEx)) {
            return   Pattern.matches(regEx, htmlStr);
        }
        return  false;
    }
    /**
     * 验证数据格式
     * @param data  字符串
     * @param datatype 验证类型
     * @return true验证成功,false失败
     */
    public static Boolean Validate(String data, ValidateEnum datatype)
    {
        boolean ok = false;//返回结果

        int type= Utils.GetInt(datatype.ordinal(), -1);
        if (type == -1)
            return ok;
        String regex = "";
        switch (type)
        {
            case 0:  //email格式
                regex = "^([\\w.\\-]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
                break;
            case 1://域名格式
                regex = "^\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
                break;
            case 2://座机电话格式
                regex ="^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";
                break;
            case 3://手机格式
                regex ="0?(13|14|15|18)[0-9]{9}";
                break;
            case 4://货币
                regex ="^\\d+(\\.\\d+)?$";
                break;
            case 5://正整数
                regex = "^\\d+$";
                break;
            case 6://邮编
                regex ="^\\d{6}$";
                break;
            case 7://IP地址
                regex = "^[\\d\\.]{7,15}$";
                break;
            case 8://QQ号
                regex = "^[1-9]\\d{4,11}$";
                break;
            case 9://整型
                regex = "^[-\\+]?\\d+$";
                break;
            case 10://浮点数
                regex = "^[-\\+]?\\d+(\\.\\d+)?$";
                break;
            case 11://英文
                regex = "^[A-Za-z]+$";
                break;
            case 12://中文
                regex = "^[\u0391-\uFFE5]+$";
                break;
            case 13://数字（整数或小数）2位小数
                regex = "^\\d*\\.{0,1}\\d{1,2}$";
                break;
            case 14://身份证
                regex = "\\d{17}[\\d|x]|\\d{15}";
                break;
        }
        if (!Utils.IsNullOrEmpty(regex) && !data.isEmpty())
        {
            ok =  Pattern.matches(regex, data);
        }
        return ok;
    }
    /**
     * 验证枚举
     */
    public static  enum ValidateEnum
    {
        /// <summary>
        /// 邮箱
        /// </summary>
        Email,
        /// <summary>
        /// 域名格式
        /// </summary>
        Domain,
        /// <summary>
        /// 座机电话格式
        /// </summary>
        Phone,
        /// <summary>
        /// 手机号
        /// </summary>
        Mobile,
        /// <summary>
        /// 货币
        /// </summary>
        Money ,
        /// <summary>
        /// 正整数
        /// </summary>
        Number,
        /// <summary>
        /// 邮编
        /// </summary>
        Zip,
        /// <summary>
        /// IP地址
        /// </summary>
        Ip,
        /// <summary>
        /// QQ号
        /// </summary>
        QQ ,
        /// <summary>
        /// 整型
        /// </summary>
        Integer,
        /// <summary>
        /// 浮点数
        /// </summary>
        Double,
        /// <summary>
        /// 英文
        /// </summary>
        English,
        /// <summary>
        /// 中文
        /// </summary>
        Chinese,
        /// <summary>
        ///数字（整数或小数）2位小数
        /// </summary>
        IntFloat,
        /// <summary>
        /// 身份证
        /// </summary>
        IDCard
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str,String formateType) {
        SimpleDateFormat format = new SimpleDateFormat(formateType);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    //region----------------获取uuid
    /**
     *获取uuid
     * @return
     */
    public static String getTaskId(){
        return  UUID.randomUUID().toString().replace("-","");
    }

    public static String urlEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }
    public static String urlDncode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, "UTF-8");
    }
    //endregion
}
