package com.winterchen.utils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

/*
 '============================================================================
 'api说明：
 'createSHA1Sign创建签名SHA1
 'getSha1()Sha1签名
 '============================================================================
 '*/

/**
 * sha1工具类
 * @author zhangweidong
 *
 */
public class Sha1Util {
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	// 创建签名SHA1
	public static String createSHA1Sign(SortedMap<String, String> signParams)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = signParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(v);
			// 要采用URLENCODER的原始值！
		}
		String params = sb.toString();
		return getSha1(params);
	}
	// Sha1签名
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			byte[] plainText = str.getBytes("UTF-8");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.update(plainText);
			byte[] digest = messageDigest.digest();
			Base64.Encoder encoder = Base64.getEncoder();
			byte[] bytes = encoder.encode(digest);
			String base64Str = new String(bytes,"UTF-8");
			return URLEncoder.encode(base64Str, "UTF-8");
//			return base64Str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main1(String[] args) {
		SortedMap<String, String> signParams = new TreeMap<>();
		signParams.put("appkey","ActivityCoupon");
		signParams.put("appsecret","AEAE70D9-8510-4BB4-9BD6-276D34FFF276");
//		signParams.put("activityId","1");
		signParams.put("timestamp","201702261146");
		try {
			String sign = createSHA1Sign(signParams);
			System.out.println(sign);
		}catch (Exception e){
			e.printStackTrace();
		}



//		SortedMap<String, String> signParams = new TreeMap<>();
//		signParams.put("appkey","testkey");
//		signParams.put("appsecret","C581F7FC-1639-4298-9C3C-570BCC4D9A11");
//		signParams.put("carid","1007");
//		signParams.put("cityid","201");
//		signParams.put("timestamp","201702261146");
//		try {
//			String sign = createSHA1Sign(signParams);
//			System.out.println(sign);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}
}