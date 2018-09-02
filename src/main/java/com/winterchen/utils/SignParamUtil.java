package com.winterchen.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Utf8;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class SignParamUtil {
    final static Map<String, String> appKeySecretMap = new HashMap<>();
    private static Logger log = LoggerFactory.getLogger(SignParamUtil.class);

    public static String getParameter(HttpServletRequest request,String name){
        return (String) request.getParameter(name);
    }
    public static  Map<String, String[]> getParameterMap(HttpServletRequest request){
        return request.getParameterMap();
    }
    public SignParamUtil() {
        appKeySecretMap.put("cbcec66ecce08de560d3623d5371ed01", "1d16d49d9adc806d101d096b31bb20da");
        appKeySecretMap.put("90a011f987be9107386d7f91c4e1a342", "2ff7955ab91f380142d1930fb99e6d12");
        appKeySecretMap.put("035a20e92066fd2017467f13c6382abc", "551d9f1295c9d5ca9aee85558e717d25");
        appKeySecretMap.put("e19eb877bbe742ed03d6d4a335bacc0a", "78f5bf1cbbe0e9f0bad87793e65fb8ec");
        appKeySecretMap.put("6fa6c81295937f7a7078ddeee4bd5c2a", "245999aabd7e01fd8013271ef3869bf1");
        appKeySecretMap.put("e20697179fd94fde2ac847588c19c466", "4d110a389d69e74c495ecfa6547ece81");

    }

    public  static String signParam(HttpServletRequest request) {
        String signCode = getParameter(request, "sign");
        String appId = getParameter(request,"app_id");
        Map<String, String[]> paramsMap = new HashMap<>(getParameterMap(request));
        List paramLisStr = new ArrayList<>();
        if (paramsMap.containsKey("sign")) {
            paramsMap.remove("sign");
        }
        paramsMap.forEach((k, v) -> {
            paramLisStr.add(k + v[0]);
        });
        paramLisStr.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        String str = String.join("", paramLisStr) + appKeySecretMap.get(appId).toString();
        byte[] encryptSource = Utf8.encode(str);
        String sign = MD5Tools.MD5(encryptSource);

        log.info(String.format("input sign:%s,our sign:%s", signCode, sign));
        return sign;
    }
}
