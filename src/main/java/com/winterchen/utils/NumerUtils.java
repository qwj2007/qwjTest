package com.winterchen.utils;

import java.text.DecimalFormat;

public class NumerUtils {
	/**
	 * @Title: formatDouble2String @Description: 对double数值保留两位 @param @param
	 * obj @param @return @return String @throws
	 */
	public static String formatDouble2String(double obj) {
		String pattern = "#0.00";
		DecimalFormat formatter = new DecimalFormat();
		formatter.applyPattern(pattern);
		return formatter.format(obj);
	}
	/**
	 * @Title: formatDouble2String @Description: 对double数值保留两位 @param @param
	 * obj @param @return @return String @throws
	 */
	public static String formatDouble1String(double obj) {
		String pattern = "#0.0";
		DecimalFormat formatter = new DecimalFormat();
		formatter.applyPattern(pattern);
		return formatter.format(obj);
	}
}
