package com.winterchen.utils;
import java.security.MessageDigest;
import java.util.Arrays;

public class SHA1 {

	   /**
	    * 用SHA1算法生成安全签名
	    * @return 安全签名
	    */
	   public static String getSHA1(String[] array)
	           {
	      try {
	         StringBuffer sb = new StringBuffer();
	         // 字符串排序
	         Arrays.sort(array);
	         for (int i = 0; i < array.length; i++) {
	            sb.append(array[i]);
	         }
	         String str = sb.toString();
	         // SHA1签名生成
	         MessageDigest md = MessageDigest.getInstance("SHA-1");
	         md.update(str.getBytes());
	         byte[] digest = md.digest();

	         StringBuffer hexstr = new StringBuffer();
	         String shaHex = "";
	         for (int i = 0; i < digest.length; i++) {
	            shaHex = Integer.toHexString(digest[i] & 0xFF);
	            if (shaHex.length() < 2) {
	               hexstr.append(0);
	            }
	            hexstr.append(shaHex);
	         }
	         return hexstr.toString();
	      } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	      }
	   }

//	   public static void main(String[] args) {
//	      String appKey = "xy_a";
//	      String appSecret = "123.abc";
//	      String timestamp = "1526543354321";
//	      String dealerId = "100221881";
//	      String clientIp = "192.168.3.137";
//	      String opUserId = "12";
//	      String opUserName = "张三";
//	      String[] params = new String[]{appKey,appSecret,timestamp,dealerId,clientIp,opUserId,opUserName};
//	      String sign=getSHA1(params);
//	      System.out.println(sign);
//	   }
	}

